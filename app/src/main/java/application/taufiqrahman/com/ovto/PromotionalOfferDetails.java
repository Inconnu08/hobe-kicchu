package application.taufiqrahman.com.ovto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;

import application.taufiqrahman.com.ovto.api.ApiClient;
import application.taufiqrahman.com.ovto.api.ApiInterface;
import application.taufiqrahman.com.ovto.models.MessageModel;
import application.taufiqrahman.com.ovto.models.PromotionalOffersModel;
import application.taufiqrahman.com.ovto.utils.ListSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromotionalOfferDetails extends AppCompatActivity {

    private PromotionalOffersModel.Datum offer;
    private TextView scores;
    private TextView title;
    private TextView validity;
    private TextView requiredScore;
    private TextView details;
    private TextView tnc;
    private TextView howTo;
    private ImageView image;

    private ListSharedPreference.Get getSharedPreference;
    private ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotional_offer_details);

        Gson gson = new Gson();
        String stringLocation = getIntent().getStringExtra("OFFER");
        if(stringLocation != null) {
            Type type = new TypeToken<PromotionalOffersModel.Datum>() {
            }.getType();
            offer = gson.fromJson(stringLocation, type);
            Log.d("====== offer ======", String.valueOf(offer));
        }
        else{
            Log.d("Offer", "failed");
        }

        getSharedPreference = new ListSharedPreference.Get(getBaseContext());

        scores = (TextView) findViewById(R.id.toolbar_pts);
        title = (TextView) findViewById(R.id.title);
        validity = (TextView) findViewById(R.id.validity);
        requiredScore = (TextView) findViewById(R.id.required_score);
        image = (ImageView) findViewById(R.id.offer_image);
        details = (TextView) findViewById(R.id.header_text_details);
        howTo = (TextView) findViewById(R.id.how_to_content);
        tnc = (TextView) findViewById(R.id.tnc_content);

        Glide.with(getBaseContext()).load(offer.getOffer_pic())
                .apply(RequestOptions.centerCropTransform()
                        .placeholder(R.drawable.default_image)).into(image);

        scores.setText(String.format("%.2f SCORES", getSharedPreference.getScore()));
        title.setText(offer.getName());
        requiredScore.setText(String.format("Score: %d", offer.getRequired_score()));
        details.setText(offer.getDescription());
        howTo.setText(offer.getHow_to_use());
        tnc.setText(offer.getTerms_and_conditions());

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void buyClick(View view) {

        Call<MessageModel> call = apiInterface.buy(getSharedPreference.getToken(),
                getSharedPreference.getSlug(), offer.getId());

        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(@NonNull Call<MessageModel> call, @NonNull Response<MessageModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "success", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getBaseContext(), jObjError.getString("message"), Toast.LENGTH_LONG).show();
                        Log.d("TAG", "*****************failed @ " + jObjError.getString("message"));
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MessageModel> call, @NonNull Throwable t) {
                Toast.makeText(getBaseContext(), "Request failed", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "*****************" + t.toString());
            }
        });
    }
}
