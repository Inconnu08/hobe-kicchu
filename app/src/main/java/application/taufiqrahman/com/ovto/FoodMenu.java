package application.taufiqrahman.com.ovto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.util.ArrayList;

import application.taufiqrahman.com.ovto.adapters.CategoryRecyclerAdapter;
import application.taufiqrahman.com.ovto.adapters.FoodRecyclerAdapter;
import application.taufiqrahman.com.ovto.api.ApiClient;
import application.taufiqrahman.com.ovto.api.ApiInterface;
import application.taufiqrahman.com.ovto.models.CartModel;
import application.taufiqrahman.com.ovto.models.RestaurantModel;
import application.taufiqrahman.com.ovto.utils.IdListener;
import application.taufiqrahman.com.ovto.utils.ListSharedPreference;
import application.taufiqrahman.com.ovto.utils.PriceListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodMenu extends Activity implements IdListener, PriceListener{

    String identifier;
    Context context;
    private ListSharedPreference.Get getSharedPreference;
    private ListSharedPreference.Set setSharedPreference;
    private ApiInterface apiInterface;
    RestaurantModel restaurant;
    private CategoryRecyclerAdapter adapter;
    private FoodRecyclerAdapter adapter2;
    private GridLayoutManager layoutManager;
    RecyclerView recyclerView2;
    LinearLayout yourTriggers;
    LinearLayout wheezeRate;
    LinearLayout peakFlow;
    LayoutParams params;
    int viewWidth;
    GestureDetector gestureDetector = null;
    Button b;
    HorizontalScrollView horizontalScrollView;
    ArrayList<LinearLayout> layouts;
    int parentLeft, parentRight;
    int mWidth;
    int currPosition, prevPosition;
    private TextView name;
    private ImageView dp;
    private int totalPrice;
    private TextView totalAmount;
    Gson gson;
    ArrayList<CartModel.Cart> carts = new ArrayList<>();
    ArrayList<RestaurantModel.Menu> menus = new ArrayList<>();
    public static ArrayList<RestaurantModel.Menu> selecteditems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);
        context = this;

        identifier = getIntent().getStringExtra("IDENTIFIER");
        adapter = null;
        adapter2 = null;
        selecteditems=new ArrayList<>();
        totalPrice = 0;

        Log.d("xxxxxxx FOOD MENU  FOR ", identifier);
        name = (TextView) findViewById(R.id.name);
        dp = (ImageView) findViewById(R.id.dp);
        totalAmount = (TextView) findViewById(R.id.toolbar_purchase_amount);

        getSharedPreference = new ListSharedPreference.Get(getBaseContext());
        setSharedPreference = new ListSharedPreference.Set(getBaseContext());
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(linearLayoutManager);
        layoutManager = new GridLayoutManager(getBaseContext(), 1);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(layoutManager);

        Call<RestaurantModel> call = apiInterface.getRestaurant(getSharedPreference.getToken(), identifier);
        call.enqueue(new Callback<RestaurantModel>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantModel> call, @NonNull Response<RestaurantModel> response) {
                restaurant = response.body();
                Log.d("TAG", "===================  Menu for Resturant  ================= " + (restaurant.getData().getName()));
                Log.d("TAG", "===================\n  toString ================= " + (restaurant.getData().toString()));
                gson = new Gson();
                String json = gson.toJson(restaurant.getData());
                Log.d("TAG", "===================\n  tojson ================= " + json);
                adapter = new CategoryRecyclerAdapter(FoodMenu.this, restaurant);
                adapter.setCategoryList(restaurant);
                recyclerView.setAdapter(adapter);
                name.setText(restaurant.getData().getName());
                Glide.with(getBaseContext()).load(restaurant.getData().getLogo()).apply(RequestOptions.centerCropTransform().placeholder(R.drawable.default_image)).into(dp);
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantModel> call, @NonNull Throwable t) {
                Toast.makeText(getBaseContext(), "Request failed", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "*****************" + t.toString());
            }
        });

        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth(); // deprecated
        viewWidth = mWidth / 3;
        layouts = new ArrayList<LinearLayout>();
        params = new LayoutParams(viewWidth, LayoutParams.WRAP_CONTENT);
        layouts.add(yourTriggers);
        layouts.add(wheezeRate);
        layouts.add(peakFlow);
    }

    @Override
    protected void onPause() {
        super.onPause();
        setSharedPreference.setMenuHistory(String.valueOf(restaurant.getData().getIdentifier()), gson.toJson(menus));

    }

    @Override
    public void onItemClick(int id) {
        Log.d("TAG", "***********catergory id: " + id);
        ArrayList<RestaurantModel.Menu> menuItem = new ArrayList<>();
        for(RestaurantModel.Menu item : restaurant.getData().getMenus()){
            Log.d("TAG", "@loop- id: " + item.getCategory_id() + " name " + item.getFoodtitle() );
            if (item.getCategory_id() == id){
                menuItem.add(item);
            }
        }
        adapter2 = new FoodRecyclerAdapter(FoodMenu.this, menuItem);
        adapter2.setItemList(menuItem);
        recyclerView2.setAdapter(adapter2);
    }

    @Override
    public void onButtonClick(int price, RestaurantModel.Menu menu) {
        Log.d("TAG", "***********price: " + price);
        if(selecteditems.contains(menu)){
            Toast.makeText(getBaseContext(), "Item already added.", Toast.LENGTH_SHORT).show();
        }else{
            totalPrice += price;
            totalAmount.setText(String.format("%d BDT", totalPrice));
            setSharedPreference.setIsNotifications(true);
            setSharedPreference.setNotificationFrom(restaurant.getData().getIdentifier());
            selecteditems.add(menu);
            menus.add(menu); // saved on onPause
        }
    }

    class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            if (e1.getX() < e2.getX()) {
                currPosition = getVisibleViews("left");
            } else {
                currPosition = getVisibleViews("right");
            }

            horizontalScrollView.smoothScrollTo(layouts.get(currPosition)
                    .getLeft(), 0);
            return true;
        }
    }

    public int getVisibleViews(String direction) {
        Rect hitRect = new Rect();
        int position = 0;
        int rightCounter = 0;
        for (int i = 0; i < layouts.size(); i++) {
            if (layouts.get(i).getLocalVisibleRect(hitRect)) {
                if (direction.equals("left")) {
                    position = i;
                    break;
                } else if (direction.equals("right")) {
                    rightCounter++;
                    position = i;
                    if (rightCounter == 2)
                        break;
                }
            }
        }
        return position;
    }

    void message(){
        Toast.makeText(FoodMenu.this,
                "Thank you for your feedback!", Toast.LENGTH_LONG).show();
    }

    public void openCart(View view) {
        if(totalPrice > 0){
            Intent intent = new Intent(this, CartActivity.class);
            intent.putExtra("IDENTIFIER", restaurant.getData().getIdentifier());
            startActivity(intent);
        }else {
            Toast.makeText(FoodMenu.this,
                    "You do not have any items in cart!", Toast.LENGTH_LONG).show();
        }
    }
//
}
