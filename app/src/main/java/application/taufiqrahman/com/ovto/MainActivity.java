package application.taufiqrahman.com.ovto;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import application.taufiqrahman.com.ovto.bottomTabBar.Home;
import application.taufiqrahman.com.ovto.bottomTabBar.Notifications;
import application.taufiqrahman.com.ovto.bottomTabBar.Options;
import application.taufiqrahman.com.ovto.utils.ListSharedPreference;
import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    public boolean mIsLogged = true;
    public static TextView mToolbarText;
    Toolbar mToolBar;
    private ProfileTracker profileTracker;
    ListSharedPreference.Set setSharedPreference;
    ListSharedPreference.Get getSharedPreference;
    NavigationTabBar navigationTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppProductionTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = findViewById(R.id.main_toolbar);
        mToolbarText = findViewById(R.id.toolbar_title);
        mToolbarText.setText(R.string.home_title);
        setSupportActionBar(mToolBar);

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setSharedPreference = new ListSharedPreference.Set(this);
        getSharedPreference = new ListSharedPreference.Get(this);

        bottomTabBar();

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null) {
                    Log.d("FACEBOOK", currentProfile.getProfilePictureUri(100, 100).toString() + "  " + currentProfile.getName());
                    setProfileInfo(currentProfile);
                }
            }
        };

        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        } else {
            requestEmail(AccessToken.getCurrentAccessToken());

            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                setProfileInfo(profile);
            } else {
                Profile.fetchProfileForCurrentAccessToken();
            }
        }
    }

    private void setProfileInfo(Profile profile) {
        setSharedPreference.setDp(profile.getProfilePictureUri(100, 100).toString());
        setSharedPreference.setUName(profile.getName());
        setSharedPreference.setUId(profile.getId());
        Log.d("FACEBOOK", profile.getProfilePictureUri(100, 100).toString() + "  " + profile.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, AuthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void requestEmail(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (response.getError() != null) {
                    Toast.makeText(getApplicationContext(), response.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    String email = object.getString("email");
                    setSharedPreference.setEmail(email);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(getSharedPreference.getIsNotifications()){
            navigationTabBar.getModels().get(1).setBadgeTitle("1");
            navigationTabBar.getModels().get(1).showBadge();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(getSharedPreference.getIsNotifications()){
            navigationTabBar.getModels().get(1).setBadgeTitle("1");
            navigationTabBar.getModels().get(1).showBadge();
        }
    }

    private void bottomTabBar() {

        navigationTabBar = findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.outline_home_24),
                        Color.parseColor("#eeeeee"))
                        .title("Home")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_notifications),
                        Color.parseColor("#eeeeee"))
                        .title("Notifications")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_profile),
                        Color.parseColor("#eeeeee"))
                        .title("Profile")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_settings),
                        Color.parseColor("#eeeeee"))
                        .title("Settings")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setInactiveColor(Color.parseColor("#4a4a4a"));
        navigationTabBar.setIsTitled(true);
        navigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frameLayout, new Home()).commit();

        //IMPORTANT: ENABLE SCROLL BEHAVIOUR IN COORDINATOR LAYOUT
        navigationTabBar.setBehaviorEnabled(true);

        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {
                beginFragmentTransactions(index);
            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
                model.hideBadge();
            }
        });
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                beginFragmentTransactions(position);
            }

            @Override
            public void onPageSelected(final int position) {

            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        if(getSharedPreference.getIsNotifications()){
            navigationTabBar.getModels().get(1).setBadgeTitle("1");
            navigationTabBar.getModels().get(1).showBadge();
        }
        // final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.parent);

//                coordinatorLayout.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        final Snackbar snackbar = Snackbar.make(navigationTabBar, "Coordinator NTB", Snackbar.LENGTH_SHORT);
//                        snackbar.getView().setBackgroundColor(Color.parseColor("#299b4d"));
//                        ((TextView) snackbar.getView().findViewById(R.id.snackbar_text))
//                                .setTextColor(Color.parseColor("#FF454545"));
//                        snackbar.show();
//                    }
//                }, 1000);

    }

 /*   private void setAlertDialog(int key) {
        switch (key) {
            case 1:
                alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage(getString(R.string.change_language_prompt));

                alertDialogBuilder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (getSharedPreference.getLanguage().equals("en")) {
                            setSharedPreference.setLanguage("ar");
                            restartActivity(MainActivity.this);
                        } else {
                            setSharedPreference.setLanguage("en");
                            restartActivity(MainActivity.this);
                        }
                    }
                });

                alertDialogBuilder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                break;
            case 2:
                alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage(getString(R.string.signin_first));

                alertDialogBuilder.setPositiveButton(getString(R.string.action_sign_in), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        MainActivity.this.finish();
                    }
                });

                alertDialogBuilder.setNegativeButton(getString(R.string.prompt_continue), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                break;
        }
    }//end setAlertDialog
    */

    private void beginFragmentTransactions(int index) {
        switch (index) {
            case 0:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_frameLayout, new Home()).commit();
                break;
            case 1:
                if (mIsLogged)
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frameLayout, new Notifications()).commit();
                else {
                   /* setAlertDialog(2);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show(); */
                }
                break;
            case 2:
                if (mIsLogged)
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frameLayout, new application.taufiqrahman.com.ovto
                                    .bottomTabBar.Profile()).commit();
                else {
                    /* setAlertDialog(2);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show(); */
                }
                break;
            case 3:
                if (mIsLogged)
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frameLayout, new Options()).commit();
                else {
                    /* setAlertDialog(2);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show(); */
                }
                break;
        }//end switch
    }
//    public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
//
//        @Override
//        public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
//            final View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_list, parent, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ViewHolder holder, final int position) {
//            holder.txt.setText(String.format("Food Item #%d", position));
//        }
//
//        @Override
//        public int getItemCount() {
//            return 20;
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//
//            public TextView txt;
//
//            public ViewHolder(final View itemView) {
//                super(itemView);
//                txt = (TextView) itemView.findViewById(R.id.txt_vp_item_list);
//            }
//        }
//    }

    public void shareInviteCode(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBodyText = "sharing message";
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(intent, "Choose sharing method"));
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

    public void logout(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                if(LoginManager.getInstance() != null)
                    LoginManager.getInstance().logOut();
                setSharedPreference.setLoginStatus(false);
                setSharedPreference.setUId(null);
                setSharedPreference.setUName(null);
                setSharedPreference.setEmail(null);
                setSharedPreference.setToken(null);
                setSharedPreference.setMobile(null);
                setSharedPreference.setImage(null);
                setSharedPreference.setStatus(null);
                setSharedPreference.setSlug(null);
                setSharedPreference.setScore(0);

                dialog.dismiss();

                Intent intent = new Intent(MainActivity.this, AuthActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

}
