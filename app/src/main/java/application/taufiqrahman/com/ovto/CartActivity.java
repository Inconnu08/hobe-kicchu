package application.taufiqrahman.com.ovto;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import application.taufiqrahman.com.ovto.adapters.CartRecyclerAdapter;
import application.taufiqrahman.com.ovto.api.ApiClient;
import application.taufiqrahman.com.ovto.api.ApiInterface;
import application.taufiqrahman.com.ovto.models.CartModel;
import application.taufiqrahman.com.ovto.models.MessageModel;
import application.taufiqrahman.com.ovto.utils.ListSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity  implements CartRecyclerAdapter.ListAdapterListener, View.OnClickListener {

    ListSharedPreference.Set setSharedPreference;
    ListSharedPreference.Get getSharedPreference;
    private CartRecyclerAdapter adapter;
    private GridLayoutManager layoutManager;
    RecyclerView recyclerView;
    Button confirm;
    TextView tvTotal;
    EditText tableCode;
    public static int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        MainActivity.mToolbarText.setText(R.string.notifications_title);
        confirm = (Button) findViewById(R.id.confirm);
        tvTotal = (TextView) findViewById(R.id.textView2);
        tableCode = (EditText) findViewById(R.id.editText);

        confirm.setOnClickListener(this);

        setSharedPreference = new ListSharedPreference.Set(this);
        getSharedPreference = new ListSharedPreference.Get(this);

        adapter = null;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        if (getSharedPreference.getIsNotifications()) {
            String cartItems = getSharedPreference.getMenuHistory(getSharedPreference.getNotificationFrom());
            Log.d("TAG", "==========cart from pref: " + cartItems);
            adapter = new CartRecyclerAdapter(this, cartItems, this);
            adapter.setItemList(cartItems);
            recyclerView.setAdapter(adapter);
            initDefaultAdapter();
            calculateTotal();
        }
    }

    public void initDefaultAdapter() {
        int i = 0;
        while (i < adapter.getItemCount()) {
            adapter.getItemList().get(i).setQuantity(1);
            adapter.getItemList().get(i).setCostPerItem(adapter.getItemList().get(i).getPrice());
            adapter.notifyItemChanged(i);
            i++;
        }
    }

    public void calculateTotal() {
        int i = 0;
        total = 0;
        while (i < adapter.getItemCount()) {
            total += (adapter.getItemList().get(i).getCostPerItem());
            i++;
        }
        tvTotal.setText(String.format("Total bill: %d BDT", total));
    }

    @Override
    public void onButtonClick(int id) {
        calculateTotal();
    }

    @Override
    public void onClick(View view) {
        List<CartModel.Cart> carts = new LinkedList<>();
        int i = 0;
        while (i < adapter.getItemCount()) {
            carts.add(new CartModel.Cart(adapter.getItemList().get(i).getId(),
                    adapter.getItemList().get(i).getGenericname(),
                    adapter.getItemList().get(i).getFoodtitle(),
                    adapter.getItemList().get(i).getDefaultMenuPicture(),
                    adapter.getItemList().get(i).getPrice(),
                    adapter.getItemList().get(i).getQuantity()));
            i++;
        }

        Toast.makeText(getBaseContext(), "Thank you!", Toast.LENGTH_SHORT).show();

        ApiInterface retrofit = ApiClient.getApiClient().create(ApiInterface.class);
        Call<MessageModel> call = retrofit.checkout(getSharedPreference.getToken(),
                getSharedPreference.getNotificationFrom(), getSharedPreference.getUId(),
                tableCode.getText().toString(), carts);

        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(@NonNull Call<MessageModel> call, @NonNull Response<MessageModel> response) {
                try {
                    if (response.body().getMessage() != null) {
                        Log.d("TAG", "==========checkout message: " + response.body().getMessage());
                        Toast.makeText(getBaseContext(), "" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    Log.d("TAG", "==========checkout exception message: " + e);
                    Toast.makeText(getBaseContext(), "" + e, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MessageModel> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
        setSharedPreference.setIsNotifications(false);
    }
}
