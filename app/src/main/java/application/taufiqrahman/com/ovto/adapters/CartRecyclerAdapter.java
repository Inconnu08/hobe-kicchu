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
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import application.taufiqrahman.com.ovto.R;
import application.taufiqrahman.com.ovto.models.RestaurantModel;
import application.taufiqrahman.com.ovto.utils.ListSharedPreference;

/**
 *  Created by Taufiq on 8/15/2018.
 */

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.MyViewHolder>  {

    Context context;
    String json;
    private String R_REGULAR = "OpenSans-Regular.ttf";
    private ListAdapterListener listener;

    public interface ListAdapterListener { // create an interface
        void onButtonClick(int position); // create callback function
    }

    ListSharedPreference.Get getSharedPreference;
    private List<RestaurantModel.Menu> menus = new ArrayList<>();

    public CartRecyclerAdapter(Context context, String json, ListAdapterListener listener) {
        this.context = context;
        this.json = json;
        menus = stringToArray(json, RestaurantModel.Menu[].class);
        getSharedPreference = new ListSharedPreference.Get(context);
        this.listener = listener;
    }

    public static <T> LinkedList<T> stringToArray(String s, Class<T[]> clazz) {
        return new LinkedList<>(Arrays.asList(new Gson().fromJson(s, clazz)));
    }

    public void setItemList(String json) {
        menus = stringToArray(json, RestaurantModel.Menu[].class);
        notifyDataSetChanged();
    }

    public List<RestaurantModel.Menu> getItemList() {
        return menus;
    }

    @Override
    public CartRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartRecyclerAdapter.MyViewHolder holder, int position) {
        holder.title.setTypeface(Typeface.createFromAsset(context.getAssets(), R_REGULAR));
        holder.title.setText(menus.get(position).getFoodtitle());
        holder.genericName.setText(menus.get(position).getGenericname());
        holder.price.setText(String.format("%d BDT", menus.get(position).getPrice()));
        holder.tPrice.setText(String.format("%d BDT", menus.get(position).getCostPerItem()));
        holder.quantity.setText(String.valueOf(menus.get(position).getQuantity()));
        Glide.with(context).load(menus.get(position).getDefaultMenuPicture())
                .apply(RequestOptions.centerCropTransform()
                        .placeholder(R.drawable.twotone_fastfood_24)).into(holder.image);
        Log.d("TAG", "============ @getPrefs " + menus.get(position).getRestaurantId());
    }

    @Override
    public int getItemCount() {
        if (menus != null) {
            return menus.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView genericName;
        TextView price;
        TextView tPrice;
        TextView quantity;
        ImageView image;
        Button add;
        Button substract;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.cardView_name);
            genericName = (TextView) itemView.findViewById(R.id.generic_name);
            price = (TextView) itemView.findViewById(R.id.item_price);
            tPrice = (TextView) itemView.findViewById(R.id.total_price);
            image = (ImageView) itemView.findViewById(R.id.img);
            add = (Button) itemView.findViewById(R.id.add_button);
            substract = (Button) itemView.findViewById(R.id.reduce_button);
            quantity = (TextView) itemView.findViewById(R.id.title);

            add.setOnClickListener(this);
            substract.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_button:
                    int i = Integer.parseInt(quantity.getText().toString());
                    i++;
                    menus.get(getAdapterPosition()).setQuantity(i);
                    menus.get(getAdapterPosition()).setCostPerItem(costPerItem(i));
                    notifyItemChanged(getAdapterPosition());
                    listener.onButtonClick(getAdapterPosition());
                    break;
                case R.id.reduce_button:
                    int j = Integer.parseInt(quantity.getText().toString());
                    j--;
                    if(j<1){
                        notifyItemRangeChanged(getAdapterPosition(), getItemCount());
                        menus.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                    }else{
                        menus.get(getAdapterPosition()).setQuantity(j);
                        menus.get(getAdapterPosition()).setCostPerItem(costPerItem(j));
                    }
                    listener.onButtonClick(getAdapterPosition());
                    notifyItemChanged(getAdapterPosition());
                    break;
            }
        }

        public int costPerItem(int quantity){
           return menus.get(getAdapterPosition()).getPrice() * quantity;
        }
    }
}
