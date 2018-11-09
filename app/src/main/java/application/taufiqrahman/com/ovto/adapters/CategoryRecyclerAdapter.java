package application.taufiqrahman.com.ovto.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import application.taufiqrahman.com.ovto.R;
import application.taufiqrahman.com.ovto.models.RestaurantModel;
import application.taufiqrahman.com.ovto.utils.IdListener;

/************************************
 * Created by Taufiq on 8/3/2018.  **
 ************************************/

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.MyViewHolder> {

    Context context;
    RestaurantModel categoryList;
    private String R_REGULAR= "OpenSans-Regular.ttf";
    private int id;
    private IdListener listener;

    public CategoryRecyclerAdapter(Context context, RestaurantModel categoryList) {
        this.context = context;
        this.categoryList = categoryList;
        listener = (IdListener) context;
    }

    public void setCategoryList(RestaurantModel categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @Override
    public CategoryRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryRecyclerAdapter.MyViewHolder holder, int position) {
        holder.category.setTypeface(Typeface.createFromAsset(context.getAssets(), R_REGULAR));
        // holder.address.setTypeface(Typeface.createFromAsset(context.getAssets(), R_LIGHT));
        Log.d("TAG", "============ @Category name: " + categoryList.getData().getCategories().get(position).getName() + " @ position:" + position);
        holder.category.setText(categoryList.getData().getCategories().get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (categoryList != null) {
            return categoryList.getData().getCategories().size();
        }
        return 0;

    }

    public int getCategoryID() {
        return id;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView category;

        public MyViewHolder(View itemView) {
            super(itemView);
            category = (TextView) itemView.findViewById(R.id.categorybtn);
            //itemView.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(categoryList.getData().getCategories().get(getAdapterPosition()).getId());
                }
            });
        }

        @Override
        public void onClick(View view) {
            // Get the item clicked
            // assuming data source is of type `List<MyObject>`
            RestaurantModel.Category categories = categoryList.getData().getCategories().get(getAdapterPosition());
            Log.d("TAG", "============ @adapter: " + categories.getName() + " id: " + categories.getId());
            id = categories.getId();
//            Intent intent = new Intent(context, CompanyDetails.class);
//            intent.putExtra("IDENTIFIER", restaurants.getIdentifier());
//            context.startActivity(intent);
        }
    }
}
