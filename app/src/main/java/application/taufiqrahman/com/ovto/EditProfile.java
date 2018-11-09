package application.taufiqrahman.com.ovto;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import application.taufiqrahman.com.ovto.api.ApiClient;
import application.taufiqrahman.com.ovto.api.ApiInterface;
import application.taufiqrahman.com.ovto.models.LoginModel;
import application.taufiqrahman.com.ovto.utils.ListSharedPreference;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {

    public static final int PICK_IMAGE = 100;
    FloatingActionButton btn;

    private ListSharedPreference.Get getSharedPreference;
    private ListSharedPreference.Set setSharedPreference;
    private ApiInterface apiInterface;
    private ImageView dp;
    private TextView name, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            finish();
            startActivity(intent);
            return;
        }

        dp = (ImageView) findViewById(R.id.img);
        btn = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        name = (TextView) findViewById(R.id.editText);
        phone = (TextView) findViewById(R.id.editText2);
        address = (TextView) findViewById(R.id.editText3);

        getSharedPreference = new ListSharedPreference.Get(getBaseContext());
        setSharedPreference = new ListSharedPreference.Set(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        name.setText(getSharedPreference.getUName());
        phone.setText(getSharedPreference.getMobile());
        address.setText(getSharedPreference.getAddress());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });

        Glide.with(this).load(getSharedPreference.getDp())
                .apply(RequestOptions.centerCropTransform()
                        .placeholder(R.drawable.default_dp)).into(dp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            CursorLoader loader = new CursorLoader(this, selectedImage, filePathColumn, null, null, null);
            Cursor cursor = loader.loadInBackground();
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String filePath = cursor.getString(column_index);
            cursor.close();

            File file = new File(filePath);


            RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", file.getName(), reqFile);
            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "upload_test");

//            Log.d("THIS", data.getData().getPath());

            Call<LoginModel> req = apiInterface.postImage(getSharedPreference.getToken(),
                    getSharedPreference.getSlug(), body);
            req.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    Toast.makeText(getBaseContext(), "Profile picture updated!", Toast.LENGTH_SHORT).show();
                    LoginModel user = response.body();
                    setSharedPreference.setDp(user.getData().getProfile().getAvatar());
                    Glide.with(getBaseContext()).load(getSharedPreference.getDp())
                            .apply(RequestOptions.centerCropTransform()
                                    .placeholder(R.drawable.default_dp)).into(dp);
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    public void onSubmit(View v){
        Call<LoginModel> req = apiInterface.editProfile(getSharedPreference.getToken(),
                getSharedPreference.getSlug(), phone.getText().toString(), address.getText().toString());
        req.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Toast.makeText(getBaseContext(), "Profile info updated!", Toast.LENGTH_SHORT).show();
                LoginModel user = response.body();
                setSharedPreference.setMobile(user.getData().getProfile().getPhonenumber());
                setSharedPreference.setAddress(user.getData().getProfile().getAddress());
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
