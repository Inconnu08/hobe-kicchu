package application.taufiqrahman.com.ovto.bottomTabBar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.DefaultSliderView;
import com.glide.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import application.taufiqrahman.com.ovto.MainActivity;
import application.taufiqrahman.com.ovto.PromotionalOfferDetails;
import application.taufiqrahman.com.ovto.R;
import application.taufiqrahman.com.ovto.api.ApiClient;
import application.taufiqrahman.com.ovto.api.ApiInterface;
import application.taufiqrahman.com.ovto.models.PromotionalOffersModel;
import application.taufiqrahman.com.ovto.utils.ListSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Profile extends Fragment implements View.OnClickListener, BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {

    private Context mContext;
    private Activity mActivity;

    private TextView userName, score, leagueText, motto, invite;
    private ImageView dp;

    private RelativeLayout mRelativeLayout;
    private Button mButton;
    private SliderLayout mSlider;

    private PopupWindow mPopupWindow;

    private String R_BLACK= "OpenSans-ExtraBold.ttf";
    private String R_REGULAR= "OpenSans-Regular.ttf";
    private String R_BOLD= "OpenSans-Bold.ttf";
    private String R_LIGHT= "OpenSans-Light.ttf";

    private ApiInterface apiInterface;
    private PromotionalOffersModel offers;

    View rootView;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_profile, container, false);
        MainActivity.mToolbarText.setText(R.string.profile_title);

        leagueText = rootView.findViewById(R.id.league);
        userName = rootView.findViewById(R.id.cardView_name);
        score = rootView.findViewById(R.id.score);
        motto = rootView.findViewById(R.id.motto);
        invite = rootView.findViewById(R.id.textView5);
        mSlider = (SliderLayout) rootView.findViewById(R.id.slider);
        dp = (ImageView) rootView.findViewById(R.id.img);
        leagueText.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListSharedPreference.Get getSharedPreference = new ListSharedPreference.Get(getContext().getApplicationContext());

        userName.setText(getSharedPreference.getUName());
        score.setText(String.valueOf(getSharedPreference.getScore()));
        leagueText.setText(getSharedPreference.getStatus());
        Log.d("TAG", "===================  dp  ================= " + getSharedPreference.getDp());
        Glide.with(getContext()).load(getSharedPreference.getDp())
                .apply(RequestOptions.centerCropTransform()
                        .placeholder(R.drawable.default_dp)).into(dp);

        userName.setTypeface(Typeface.createFromAsset(getContext().getAssets(), R_BOLD));
        score.setTypeface(Typeface.createFromAsset(getContext().getAssets(), R_REGULAR));
        leagueText.setTypeface(Typeface.createFromAsset(getContext().getAssets(), R_REGULAR));
        motto.setTypeface(Typeface.createFromAsset(getContext().getAssets(), R_BLACK));
        invite.setTypeface(Typeface.createFromAsset(getContext().getAssets(), R_LIGHT));

        // Toast.makeText(getContext().getApplicationContext(), getSharedPreference.getUName(), Toast.LENGTH_LONG).show();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<PromotionalOffersModel> call = apiInterface.getPromoOffers(getSharedPreference.getToken());
        call.enqueue(new Callback<PromotionalOffersModel>() {
            @Override
            public void onResponse(@NonNull Call<PromotionalOffersModel> call, @NonNull Response<PromotionalOffersModel> response) {
                offers = response.body();
//                Log.d("TAG", "===================  offers  ================= " + offers.getData());
                initSlider();
            }

            @Override
            public void onFailure(@NonNull Call<PromotionalOffersModel> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), R.string.connection_error, Toast.LENGTH_SHORT).show();
                Log.d("REQUEST ERROR", "*****************" + t.toString());
            }
        });

    }

    public void initSlider() {
        RequestOptions requestOptions = new RequestOptions();
        RequestOptions requestOptions1 = requestOptions
                .centerCrop();
        for (int i = 0; i < offers.getData().size(); i++) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());
            sliderView
                    .image(offers.getData().get(i).getOffer_pic())
                    .description(String.valueOf(i))
                    .setRequestOption(requestOptions1)
                    .setBackgroundColor(Color.WHITE)
                    .setProgressBarVisible(true)
                    .setOnSliderClickListener(this);

            mSlider.addSlider(sliderView);
        }

        mSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setDuration(4000);
        mSlider.addOnPageChangeListener(this);
    }

    public void onClick(View view) {
        // Initialize a new instance of LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.user_status_info, null);
                /*
                    public PopupWindow (View contentView, int width, int height)
                        Create a new non focusable popup window which can display the contentView.
                        The dimension of the window must be passed to this constructor.

                        The popup does not provide any background. This should be handled by
                        the content view.

                    Parameters
                        contentView : the popup's content
                        width : the popup's width
                        height : the popup's height
                */
        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(
                customView,
                RelativeLayout.LayoutParams.MATCH_PARENT,
                500
        );
        // Set an elevation value for popup window
        // Call requires API level 21
        if (Build.VERSION.SDK_INT >= 21) {
            mPopupWindow.setElevation(5.0f);
        }

        // Get a reference for the custom view close button
        ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

        // Set a click listener for the popup window close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                mPopupWindow.dismiss();
            }
        });

        /*
                    public void showAtLocation (View parent, int gravity, int x, int y)
                        Display the content view in a popup window at the specified location. If the
                        popup window cannot fit on screen, it will be clipped.
                        Learn WindowManager.LayoutParams for more information on how gravity and the x
                        and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
                        to specifying Gravity.LEFT | Gravity.TOP.

                    Parameters
                        parent : a parent view to get the getWindowToken() token from
                        gravity : the gravity which controls the placement of the popup window
                        x : the popup's x location offset
                        y : the popup's y location offset
         */
        // Finally, show the popup window at the center location of root relative layout
        mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Log.v("xxxxxxxxxxxxxxxxxxxxxxx", offers.getData().get(Integer.parseInt(slider.getDescription())).getDescription());
        Toast.makeText(getContext(), offers.getData().get(Integer.parseInt(slider.getDescription())).getDescription(), Toast.LENGTH_LONG).show();
        Gson gson = new Gson();
        Type type = new TypeToken<PromotionalOffersModel.Datum>() {}.getType();
        String json = gson.toJson(offers.getData().get(Integer.parseInt(slider.getDescription())), type);
        Intent intent = new Intent(getContext(), PromotionalOfferDetails.class);
        intent.putExtra("OFFER", json);
        startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
