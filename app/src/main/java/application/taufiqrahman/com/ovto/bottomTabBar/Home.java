package application.taufiqrahman.com.ovto.bottomTabBar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import application.taufiqrahman.com.ovto.AuthActivity;
import application.taufiqrahman.com.ovto.MainActivity;
import application.taufiqrahman.com.ovto.R;
import application.taufiqrahman.com.ovto.adapters.RestaurantsRecyclerAdapter;
import application.taufiqrahman.com.ovto.api.ApiClient;
import application.taufiqrahman.com.ovto.api.ApiInterface;
import application.taufiqrahman.com.ovto.models.RestaurantsModel;
import application.taufiqrahman.com.ovto.utils.ListSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {

    View rootView;

    private ListSharedPreference.Get getSharedPreference;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private GridLayoutManager layoutManager;
    private RestaurantsRecyclerAdapter adapter;

    private ApiInterface apiInterface;

    private int page_num = 1;
    private int per_page = 3;

    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previousTotal = 0;
    private int view_threshold = 12;

    RestaurantsModel restaurantList;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        MainActivity.mToolbarText.setText(R.string.home_title);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = null;

        getSharedPreference = new ListSharedPreference.Get(getContext());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        // Call<DataResponse> call = apiInterface.getUsers(page_num, per_page);

        Call<RestaurantsModel> call = apiInterface.getRestaurants(getSharedPreference.getToken());
        call.enqueue(new Callback<RestaurantsModel>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantsModel> call, @NonNull Response<RestaurantsModel> response) {
//                if (response.body().getData() != null){
//                    List<Data> data = response.body().getData();
//                    adapter = new RecyclerAdapter(data, getContext());
//                    recyclerView.setAdapter(adapter);
//                    progressBar.setVisibility(View.GONE);
//                }
                restaurantList = response.body();
                adapter = new RestaurantsRecyclerAdapter(getContext(), restaurantList, getSharedPreference.getToken());
                Log.d("TAG", "===================  Resturants list  ================= " + (restaurantList != null ? restaurantList.getData().getCurrentPage() : 0));
                //Log.d("TAG", "===================  Resturants list  ================= " + restaurantList.getData().getData().get(0).getName());
                adapter.setRestaurantList(restaurantList);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantsModel> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Request failed", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "@Request onFailure " + t.toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Session timed out");
                builder.setMessage("Login?");

                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Intent intent = new Intent(getActivity(), AuthActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                if(dy > 0){
                    if(isLoading){
                        if(totalItemCount > previousTotal){
                            isLoading = false;
                            previousTotal = totalItemCount;
                        }
                    }
                    if(!isLoading && (totalItemCount-visibleItemCount) <= (pastVisibleItems+view_threshold)){
                        page_num++;
                        performPagination();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void performPagination(){
        progressBar.setVisibility(View.VISIBLE);
        Call<RestaurantsModel> call = apiInterface.getRestaurants(getSharedPreference.getToken());
        call.enqueue(new Callback<RestaurantsModel>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantsModel> call, @NonNull Response<RestaurantsModel> response) {
//                if(true){
//                    List<RestaurantsModel> data = response.body();
//                    adapter.addData(data);
//                }else {
//                    Toast.makeText(getContext(), "No more available", Toast.LENGTH_SHORT).show();
//                }
                restaurantList = response.body();
                Log.d("TAG", "*****************" + restaurantList.getData().toString());
                // adapter.setRestaurantList(restaurantList);
            }
            @Override
            public void onFailure(Call<RestaurantsModel> call, @NonNull Throwable t) {
                Log.d("TAG", "*****************" + t.toString());
                Toast.makeText(getContext(), "Bad request", Toast.LENGTH_SHORT).show();
            }
        });
        progressBar.setVisibility(View.GONE);
    }

}//end Home
