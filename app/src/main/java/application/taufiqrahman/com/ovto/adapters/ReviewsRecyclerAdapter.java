package application.taufiqrahman.com.ovto.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import application.taufiqrahman.com.ovto.R;
import application.taufiqrahman.com.ovto.models.RestaurantModel;

/**
 *
 * Created by Taufiq on 7/19/2018.
 *
 */

public class ReviewsRecyclerAdapter extends RecyclerView.Adapter<ReviewsRecyclerAdapter.MyViewHolder> {

    Context context;
    List<RestaurantModel.Data.Reviews> reviews;
    private String R_REGULAR= "OpenSans-Regular.ttf";
    private String R_LIGHT= "OpenSans-Light.ttf";

    public ReviewsRecyclerAdapter(Context context, List<RestaurantModel.Data.Reviews> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    public void setReviews(List<RestaurantModel.Data.Reviews> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @Override
    public ReviewsRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewsRecyclerAdapter.MyViewHolder holder, int position) {
        holder.name.setTypeface(Typeface.createFromAsset(context.getAssets(), R_REGULAR));
        holder.review.setTypeface(Typeface.createFromAsset(context.getAssets(), R_LIGHT));
        holder.name.setText(reviews.get(position).getUser().getName());
        holder.review.setText(reviews.get(position).getReview());
        Glide.with(context).load(reviews.get(position).getUser().getProfile().getAvatar()).apply(RequestOptions.centerCropTransform().placeholder(R.drawable.brand_logo)).into(holder.image);
        holder.ratingBar.setRating(reviews.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        if(reviews != null){
            return reviews.size();
        }
        return 0;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView review;
        RatingBar ratingBar;
        ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.cardView_name);
            review = (TextView)itemView.findViewById(R.id.comment);
            ratingBar = (RatingBar)itemView.findViewById(R.id.ratingBar);
            image = (ImageView)itemView.findViewById(R.id.img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            // Get the item clicked
//            // assuming data source is of type `List<MyObject>`
//            RestaurantsModel.Data.Datum restaurants = restaurantList.getData().getData().get(getAdapterPosition());
//            Log.d("TAG", "============ @adapter: "+restaurants.getIdentifier());
//            Intent intent = new Intent(context, CompanyDetails.class);
//            intent.putExtra("IDENTIFIER", restaurants.getIdentifier());
//            context.startActivity(intent);
        }
    }
}
