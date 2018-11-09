package application.taufiqrahman.com.ovto;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import java.util.List;

import application.taufiqrahman.com.ovto.api.ApiClient;
import application.taufiqrahman.com.ovto.api.ApiInterface;
import application.taufiqrahman.com.ovto.models.RestaurantModel;
import application.taufiqrahman.com.ovto.utils.ListSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyDetails extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {

    TextView rName;
    TextView address;
    TextView addressShort;
    TextView ratingtxt;
    TextView timing;
    TextView phone;
    TextView numOfRaters;
    RatingBar ratingBar;
    ImageView image;
    Button menu;

    private SliderLayout mSlider;
    private SliderLayout mSlider2;
    private ImageView cover;

    private ListSharedPreference.Get getSharedPreference;
    private ApiInterface apiInterface;
    RestaurantModel restaurant;
    private String R_BOLD = "OpenSans-Bold.ttf";
    private String R_REGULAR = "OpenSans-Regular.ttf";
    private String R_LIGHT = "OpenSans-Light.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        String identifier = getIntent().getStringExtra("IDENTIFIER");
        Log.d("TAG", "============== @ company details: " + identifier);

        getSharedPreference = new ListSharedPreference.Get(getBaseContext());
        mSlider = (SliderLayout) findViewById(R.id.promo_slider);
        mSlider2 = (SliderLayout) findViewById(R.id.gallery_slider);
        rName = (TextView) findViewById(R.id.name);
        addressShort = (TextView) findViewById(R.id.generic_name);
        address = (TextView) findViewById(R.id.address_middle);
        ratingtxt = (TextView) findViewById(R.id.ratingtext);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        numOfRaters = (TextView) findViewById(R.id.cardView_raters);
        timing = (TextView) findViewById(R.id.cardView_status);
        phone = (TextView) findViewById(R.id.phone);
        menu = (Button) findViewById(R.id.menubutton);
        cover = (ImageView) findViewById(R.id.cover);

        rName.setTypeface(Typeface.createFromAsset(getAssets(), R_REGULAR));
        addressShort.setTypeface(Typeface.createFromAsset(getAssets(), R_REGULAR));
        address.setTypeface(Typeface.createFromAsset(getAssets(), R_LIGHT));
        timing.setTypeface(Typeface.createFromAsset(getAssets(), R_REGULAR));
        phone.setTypeface(Typeface.createFromAsset(getAssets(), R_LIGHT));
        numOfRaters.setTypeface(Typeface.createFromAsset(getAssets(), R_BOLD));
        menu.setTypeface(Typeface.createFromAsset(getAssets(), R_REGULAR));

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<RestaurantModel> call = apiInterface.getRestaurant(getSharedPreference.getToken(), identifier);
        call.enqueue(new Callback<RestaurantModel>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantModel> call, @NonNull Response<RestaurantModel> response) {
                restaurant = response.body();
                Log.d("TAG", "===================  Resturant  ================= " + (restaurant.getData().getName()));
                rName.setText(restaurant.getData().getName());
                addressShort.setText(String.format("%s, %s",
                        restaurant.getData().getLocation().getAreaName(),
                        restaurant.getData().getLocation().getCityName()));
                if (restaurant.getData().getIsOpen() == 1) {
                    timing.setText("Open");
                    timing.setTextColor(Color.parseColor("#42da72"));
                }
                ratingtxt.setText(String.format("%s★", restaurant.getData().getAvgRatingCount()));
                address.setText(String.format("%s, %s\n%s, %s\n%s",
                        restaurant.getData().getLocation().getHouse(),
                        restaurant.getData().getLocation().getRoad(),
                        restaurant.getData().getLocation().getAreaName(),
                        restaurant.getData().getLocation().getCityName(),
                        restaurant.getData().getLocation().getCountry()));
                phone.setText(restaurant.getData().getPhonenumber());
                if (restaurant.getData().getReviews_count() == null) {
                    numOfRaters.setText("No reviews yet");
                } else {
                    numOfRaters.setText(String.format("%d review(s)", restaurant.getData().getReviews_count()));
                    numOfRaters.setTextColor(Color.parseColor("#3bc266"));
                }
                Glide.with(getBaseContext()).load(restaurant.getData().getCover())
                        .apply(RequestOptions.centerCropTransform()
                                .placeholder(R.drawable.default_image)).into(cover);
                // Log.d("TAG", "====================== image: "+restaurant.getData().getPromotionalimages().get(1).getPromotionalPicture());
                initSlider();
                initSlider2();
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantModel> call, @NonNull Throwable t) {
                Toast.makeText(getBaseContext(), "Request failed", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "*****************" + t.toString());
            }
        });
    }

    public void initSlider() {
        RequestOptions requestOptions = new RequestOptions();
        RequestOptions requestOptions1 = requestOptions
                .centerCrop();
        for (int i = 0; i < restaurant.getData().getPromotionalimages().size(); i++) {
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView
                    .image(restaurant.getData().getPromotionalimages().get(i).getPromotionalPicture())
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

    public void initSlider2() {
        RequestOptions requestOptions = new RequestOptions();
        RequestOptions requestOptions1 = requestOptions
                .centerCrop();
        for (int i = 0; i < restaurant.getData().getGalleryimages().size(); i++) {
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView
                    .image(restaurant.getData().getGalleryimages().get(i).getGallery_picture())
                    .setRequestOption(requestOptions1)
                    .setBackgroundColor(Color.WHITE)
                    .setProgressBarVisible(true)
                    .setOnSliderClickListener(this);

            mSlider2.addSlider(sliderView);
        }

        mSlider2.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mSlider2.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider2.setDuration(4000);
        mSlider2.addOnPageChangeListener(this);
    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Log.v("xxxxxxxxxxxxxxxxxxxxxxx", restaurant.getData().getPromotionalimages().get(0).getPromotionalPicture());
        Toast.makeText(this, restaurant.getData().getPromotionalimages().get(0).getPromotionalPicture(), Toast.LENGTH_LONG).show();
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

    public void openMenu(View view) {
        Intent intent = new Intent(this, FoodMenu.class);
        intent.putExtra("IDENTIFIER", restaurant.getData().getIdentifier());
        startActivity(intent);
    }

    public void openReviews(View view) {
        Toast.makeText(getBaseContext(), "Clicked!", Toast.LENGTH_LONG).show();
        Gson gson = new Gson();
        Type type = new TypeToken<List<RestaurantModel.Data.Reviews>>() {}.getType();
        String json = gson.toJson(restaurant.getData().getReviews(), type);
        Intent intent = new Intent(this, ReviewActivity.class);
        intent.putExtra("REVIEWS", json);
        startActivity(intent);
    }
}
