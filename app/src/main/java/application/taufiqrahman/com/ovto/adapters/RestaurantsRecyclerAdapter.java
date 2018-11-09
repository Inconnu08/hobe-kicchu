package application.taufiqrahman.com.ovto.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Random;

import application.taufiqrahman.com.ovto.CompanyDetails;
import application.taufiqrahman.com.ovto.R;
import application.taufiqrahman.com.ovto.models.RestaurantsModel;

/**
 *
 * Created by Taufiq on 7/19/2018.
 *
 */

public class RestaurantsRecyclerAdapter extends RecyclerView.Adapter<RestaurantsRecyclerAdapter.MyViewHolder> {
    Context context;
    RestaurantsModel restaurantList;
    private String AUTHORIZATION;
    private final Random r = new Random();
    private String R_BLACK= "Roboto-Black.ttf";
    private String R_BOLD= "Roboto-Bold.ttf";
    private String R_REGULAR= "OpenSans-Regular.ttf";
    private String R_LIGHT= "OpenSans-Light.ttf";

    public RestaurantsRecyclerAdapter(Context context, RestaurantsModel restaurantList, String AUTHORIZATION) {
        this.context = context;
        this.restaurantList = restaurantList;
        this.AUTHORIZATION = AUTHORIZATION;
    }

    public void setRestaurantList(RestaurantsModel restaurantList) {
        this.restaurantList = restaurantList;
        notifyDataSetChanged();
    }

    @Override
    public RestaurantsRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantsRecyclerAdapter.MyViewHolder holder, int position) {
        holder.rName.setTypeface(Typeface.createFromAsset(context.getAssets(), R_REGULAR));
        holder.address.setTypeface(Typeface.createFromAsset(context.getAssets(), R_LIGHT));
        Glide.with(context).load(restaurantList.getData().getData().get(position).getLogo()).apply(RequestOptions.centerCropTransform().placeholder(R.drawable.brand_logo)).into(holder.rLogo);
        holder.rName.setText(restaurantList.getData().getData().get(position).getName());
        holder.address.setText(String.format("%s, %s",
                restaurantList.getData().getData().get(position).getLocation().getAreaName(),
                restaurantList.getData().getData().get(position).getLocation().getCityName()));
        holder.ratingtxt.setText(String.valueOf(restaurantList.getData().getData().get(position).getAvgRatingCount()));
        holder.ratingBar.setRating(restaurantList.getData().getData().get(position).getAvgRatingCount());
        Log.d("TAG", "============ @size: "+restaurantList.getData().getData().get(position).getPromotionalimages().size() + " @ position:"+position);
        if(restaurantList.getData().getData().get(position).getPromotionalimages().size() != 0){
//            GlideUrl imgUrl = new GlideUrl(restaurantList.getData().getData().get(position).getPromotionalimages().get(r.nextInt(6)+1).getPromotionalPicture(),
//                    new LazyHeaders.Builder()
//                            .addHeader("Authorization", AUTHORIZATION)
//                            .build());
            Log.d("TAG", "============ @size: "+restaurantList.getData().getData().get(position).getPromotionalimages().size());
            Log.d("TAG", "============ @name: "+restaurantList.getData().getData().get(position).getPromotionalimages().get(0).getPromotionalPicture());
            Glide.with(context).load(restaurantList.getData().getData().get(position).getPromotionalimages().get(r.nextInt(restaurantList.getData().getData().get(position).getPromotionalimages().size())).getPromotionalPicture()).apply(RequestOptions.centerCropTransform().placeholder(R.drawable.default_image)).into(holder.image);
        }

//        if(restaurantList.getData().getData().get(position).getGalleryimages() != null)
//            Log.d("TAG", restaurantList.getData().getData().get(position).getGalleryimages().get(0).getGallery_picture());
//        holder.rName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Log.d("TAG", restaurantList.getData().getData().get(position).getIdentifier());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(restaurantList != null){
            return restaurantList.getData().getData().size();
        }
        return 0;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView rName;
        TextView address;
        TextView ratingtxt;
        RatingBar ratingBar;
        ImageView image;
        ImageView rLogo;

        public MyViewHolder(View itemView) {
            super(itemView);
            rName = (TextView)itemView.findViewById(R.id.restaurant_name);
            address = (TextView)itemView.findViewById(R.id.generic_name);
            ratingtxt = (TextView)itemView.findViewById(R.id.rating);
            ratingBar = (RatingBar)itemView.findViewById(R.id.ratingBar);
            image = (ImageView)itemView.findViewById(R.id.cardView_image);
            rLogo = (ImageView)itemView.findViewById(R.id.rlogo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the item clicked
            // assuming data source is of type `List<MyObject>`
            RestaurantsModel.Data.Datum restaurants = restaurantList.getData().getData().get(getAdapterPosition());
            Log.d("TAG", "============ @adapter: "+restaurants.getIdentifier());
            Intent intent = new Intent(context, CompanyDetails.class);
            intent.putExtra("IDENTIFIER", restaurants.getIdentifier());
            context.startActivity(intent);
        }
    }
}
