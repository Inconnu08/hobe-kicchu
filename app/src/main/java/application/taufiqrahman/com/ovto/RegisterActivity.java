package application.taufiqrahman.com.ovto;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import application.taufiqrahman.com.ovto.api.ApiClient;
import application.taufiqrahman.com.ovto.api.ApiInterface;
import application.taufiqrahman.com.ovto.models.LoginModel;
import application.taufiqrahman.com.ovto.models.RegisterModel;
import application.taufiqrahman.com.ovto.utils.ListSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText confirmPasswordEditText;
    private TextInputEditText emailEditText;
    private TextInputEditText nameEditText;
    private TextInputEditText passwordEditText;
    private TextInputLayout confirmPasswordTextInput;
    private TextInputLayout emailTextInput;
    private TextInputLayout nameTextInput;
    private TextInputLayout passwordTextInput;
    private ListSharedPreference.Set setSharedPreference;
    private AlertDialog.Builder alertDialogBuilder;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setSharedPreference = new ListSharedPreference.Set(this);
        initUI();
        setAlertDialog();
    }

    private void setAlertDialog() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setNegativeButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
    }

    public void register(View v) {
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Registering...");
        progressDialog.setMessage("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);
        ApiInterface retrofit = ApiClient.getApiClient().create(ApiInterface.class);
        Call<RegisterModel> call = retrofit.register(nameEditText.getText().toString(), emailEditText.getText().toString(),
                passwordEditText.getText().toString(), passwordEditText.getText().toString());

        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(@NonNull Call<RegisterModel> call, @NonNull Response<RegisterModel> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Registered successfully" , Toast.LENGTH_SHORT).show();
                        progressDialog.setTitle("Logging in...");
                        progressDialog.setMessage("Please wait");
                        serverLogin();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    progressDialog.dismiss();
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialogBuilder.setMessage("Error: " + e);
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterModel> call, @NonNull Throwable t) {
                t.printStackTrace();
                Toast.makeText(RegisterActivity.this, "No network", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initUI() {
        confirmPasswordEditText = findViewById(R.id.password_confirm_edittext);
        emailEditText = findViewById(R.id.email_edittext);
        nameEditText = findViewById(R.id.name_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        confirmPasswordTextInput = findViewById(R.id.password_confirm_text_input);
        emailTextInput = findViewById(R.id.email_text_input);
        nameTextInput = findViewById(R.id.name_text_input);
        passwordTextInput = findViewById(R.id.password_text_input);

        nameEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isNameValid(nameEditText.getText())) {
                    nameTextInput.setError(null);
                } else {
                    nameTextInput.setError("Required");
                }
                return false;
            }
        });

        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(null);
                } else {
                    passwordTextInput.setError("Invalid password");
                }
                return false;
            }
        });

        emailEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isEmailValid(emailEditText.getText().toString())) {
                    emailTextInput.setError(null);
                } else {
                    emailTextInput.setError("Invalid email");
                }
                return false;
            }
        });

        confirmPasswordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isIdentical(passwordEditText.getText().toString(), confirmPasswordEditText.getText().toString())) {
                    confirmPasswordTextInput.setError(null);
                } else {
                    confirmPasswordTextInput.setError("Passwords do not match");
                }
                return false;
            }
        });

        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("loading...");
        progressDialog.setMessage("please wait");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(@Nullable Editable password) {
        return password != null && password.length() > 7;
    }

    private boolean isNameValid(@Nullable Editable name) {
        return name != null && name.length() > 1;
    }

    private boolean isIdentical(String password1, String password2) {
        return password1.equals(password2);
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void serverLogin() {

        ApiInterface retrofit = ApiClient.getApiClient().create(ApiInterface.class);
        Call<LoginModel> call = retrofit.login(emailEditText.getText().toString(),
                passwordEditText.getText().toString());

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getData() != null) {

                            LoginModel.DataBean user = response.body().getData();
                            setSharedPreference.setUId(user.getId());
                            setSharedPreference.setUName(user.getName());
                            setSharedPreference.setEmail(user.getMail());
                            setSharedPreference.setStatus(user.getProfile().getStatus());
                            setSharedPreference.setMobile(user.getProfile().getPhonenumber());
                            setSharedPreference.setAddress(user.getProfile().getAddress());
                            setSharedPreference.setToken(response.body().getToken().getToken());
                            setSharedPreference.setScore(user.getProfile().getScore());
                            setSharedPreference.setSlug(user.getSlug());
                            setSharedPreference.setDp(user.getProfile().getAvatar());

                            Toast.makeText(RegisterActivity.this, "logged in successfully", Toast.LENGTH_LONG).show();

                            setSharedPreference.setLoginStatus(true);

                            progressDialog.dismiss();

                            goMainScreen();

                            finish();
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT).show();
                        passwordTextInput.setError("Invalid password");
                        YoYo.with(Techniques.Shake)
                                .duration(900)
                                .playOn(passwordEditText);
                    }
                } catch (Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "No network", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }
}
