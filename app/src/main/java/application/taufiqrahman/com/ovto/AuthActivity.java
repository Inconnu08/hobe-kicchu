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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Collections;

import application.taufiqrahman.com.ovto.api.ApiClient;
import application.taufiqrahman.com.ovto.api.ApiInterface;
import application.taufiqrahman.com.ovto.models.LoginModel;
import application.taufiqrahman.com.ovto.utils.ListSharedPreference;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthActivity extends AppCompatActivity {

    private AlertDialog.Builder alertDialogBuilder;
    private TextInputEditText mEmailEditText;
    private TextInputEditText mPasswordEditText;
    private TextInputLayout passwordTextInput;
    private TextInputLayout emailTextInput;
    private TextView mRegisterLink;
    private ListSharedPreference.Set setSharedPreference;
    private ProgressDialog progressDialog;
    private CallbackManager mFacebookCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        mFacebookCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mFacebookCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        setSharedPreference.setLoginStatus(true);
                        goMainScreen();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "Facebook sign-in cancelled.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(AuthActivity.class.getCanonicalName(), error.getMessage());
                        Toast.makeText(getApplicationContext(), "Sign-in failed,", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        setContentView(R.layout.activity_auth);
        setSharedPreference = new ListSharedPreference.Set(AuthActivity.this.getApplicationContext());
        FancyButton mFacebookSignInButton = findViewById(R.id.facebook_sign_in_button);
        mFacebookSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(AuthActivity.this, Collections.singleton("public_profile"));
            }
        });

        initUI();
        setAlertDialog();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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

    public void openHome(View view) {
        attemptLogin();
    }

    private void serverLogin() {

        ApiInterface retrofit = ApiClient.getApiClient().create(ApiInterface.class);
        Call<LoginModel> call = retrofit.login(mEmailEditText.getText().toString(),
                mPasswordEditText.getText().toString());

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

                            Toast.makeText(AuthActivity.this, "logged in successfully", Toast.LENGTH_LONG).show();

                            setSharedPreference.setLoginStatus(true);

                            progressDialog.dismiss();

                            goMainScreen();

                            finish();
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Invalid password!", Toast.LENGTH_SHORT).show();
                        passwordTextInput.setError("Invalid password!");
                        YoYo.with(Techniques.Shake)
                                .duration(900)
                                .playOn(mPasswordEditText);
                    }
                } catch (Exception e) {
                    progressDialog.dismiss();
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialogBuilder.setMessage("Error: " + e);
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }

    private void initUI() {
        mEmailEditText    = findViewById(R.id.email_edittext);
        emailTextInput    = findViewById(R.id.email_text_input);
        mPasswordEditText = findViewById(R.id.password_edittext);
        passwordTextInput = findViewById(R.id.password_text_input);
        mRegisterLink     = findViewById(R.id.registration_text);

        progressDialog = new ProgressDialog(AuthActivity.this);
        progressDialog.setTitle("Logging in...");
        progressDialog.setMessage("please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        mPasswordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(mPasswordEditText.getText())) {
                    passwordTextInput.setError(null);
                }else{
                    passwordTextInput.setError("Invalid password");
                }
                return false;
            }
        });

        mEmailEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isEmailValid(mEmailEditText.getText().toString())) {
                    emailTextInput.setError(null);
                }else{
                    emailTextInput.setError("Invalid email");
                }
                return false;
            }
        });

        mRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void attemptLogin() {
        if (isEmailValid(mEmailEditText.getText().toString()) &&
                isPasswordValid(mPasswordEditText.getText())) {
            progressDialog.show();
            serverLogin();
        }else{
            passwordTextInput.setError("Invalid password");
            emailTextInput.setError("Invalid email");
            YoYo.with(Techniques.Shake)
                    .duration(900)
                    .playOn(mPasswordEditText);
            YoYo.with(Techniques.Shake)
                    .duration(900)
                    .playOn(mEmailEditText);
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(@Nullable Editable password) {
        return password != null && password.length() > 7;
    }
}
