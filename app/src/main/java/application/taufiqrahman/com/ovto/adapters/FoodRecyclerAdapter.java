package application.taufiqrahman.com.ovto.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import application.taufiqrahman.com.ovto.R;
import application.taufiqrahman.com.ovto.models.RestaurantModel;
import application.taufiqrahman.com.ovto.utils.ListSharedPreference;
import application.taufiqrahman.com.ovto.utils.PriceListener;
//import me.chensir.expandabletextview.ExpandableTextView;

/************************************
 * Created by Taufiq on 8/6/2018.  **
 ************************************/

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.MyViewHolder> {

    Context context;
    private ArrayList<RestaurantModel.Menu> itemList = new ArrayList<>();
    private String R_REGULAR = "OpenSans-Regular.ttf";
    private PriceListener listener;
    ListSharedPreference.Get getSharedPreference;

    public FoodRecyclerAdapter(Context context, ArrayList<RestaurantModel.Menu> itemList) {
        this.context = context;
        this.itemList = itemList;
        listener = (PriceListener) context;
        getSharedPreference = new ListSharedPreference.Get(context);
    }

    public void setItemList(ArrayList<RestaurantModel.Menu> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @Override
    public FoodRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new FoodRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodRecyclerAdapter.MyViewHolder holder, int position) {
        holder.title.setTypeface(Typeface.createFromAsset(context.getAssets(), R_REGULAR));
        holder.title.setText(itemList.get(position).getFoodtitle());
        holder.genericName.setText(itemList.get(position).getGenericname());
        holder.price.setText(String.format("%d BDT", itemList.get(position).getPrice()));
        Glide.with(context).load(itemList.get(position).getDefaultMenuPicture())
                .apply(RequestOptions.centerCropTransform()
                        .placeholder(R.drawable.twotone_fastfood_24)).into(holder.image);
//        holder.readMore.setText(String.format(" \n %s", itemList.get(position).getDescription()));
        // }
        //Log.d("TAG", "============ @Title: " + itemList.getData().getCategories().get(position).getName() + " @ position:" + position);
        Log.d("TAG", "============ @getPrefs " + getSharedPreference.getMenuHistory(String.valueOf(itemList.get(position).getRestaurantId())));
    }

    @Override
    public int getItemCount() {
        if (itemList != null) {
            return itemList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView genericName;
        TextView price;
        ImageView image;
//        ExpandableTextView readMore;
        Button order;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            genericName = (TextView) itemView.findViewById(R.id.gname);
            price = (TextView) itemView.findViewById(R.id.item_price);
            image = (ImageView) itemView.findViewById(R.id.image);
//            readMore = (ExpandableTextView) itemView.findViewById(R.id.tv);
//            readMore.setOnClickListener(this);
            order = (Button) itemView.findViewById(R.id.order_button);
            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonClick(itemList.get(getAdapterPosition()).getPrice(), itemList.get(getAdapterPosition()));
                }
            });
        }

        @Override
        public void onClick(View view) {
            // Get the item clicked
            // assuming data source is of type `List<MyObject>`
            //RestaurantModel.Category categories = itemList.getData().getCategories().get(getAdapterPosition());
            //Log.d("TAG", "============ @adapter: " + categories.getName());
//            Intent intent = new Intent(context, CompanyDetails.class);
//            intent.putExtra("IDENTIFIER", restaurants.getIdentifier());
//            context.startActivity(intent);
        }
    }
}
