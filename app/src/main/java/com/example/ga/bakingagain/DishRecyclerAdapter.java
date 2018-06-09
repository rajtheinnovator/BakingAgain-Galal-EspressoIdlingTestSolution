package com.example.ga.bakingagain;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class DishRecyclerAdapter extends RecyclerView.Adapter<DishRecyclerAdapter.ViewHolder> {
    private List<Dish> dishList;
    private Context context;
    private boolean land;

    public DishRecyclerAdapter(List<Dish> dishList, Context context, boolean land) {
        this.dishList = dishList;
        this.context = context;
        this.land = land;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_list,parent,false);
        ViewHolder viewHolder = new DishRecyclerAdapter.ViewHolder(context,v);
        return  viewHolder ;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Dish dish = dishList.get(position);
        String dName = dish.getName();
        holder.dishName.setText(dName);

    }

    @Override
    public int getItemCount() {

        return dishList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView dishName;
        public LinearLayout linearLayout;
        private Context context;

        public ViewHolder(Context context, View itemView) {
            /*
            Stores the itemView in a public final member variable that can be used
            to access the context from any ViewHolder instance.
            */
            super(itemView);
            dishName = itemView.findViewById(R.id.DishName);
            linearLayout = itemView.findViewById(R.id.LinearLayout);
            this.context = context;
            /* Attach a click listener to the entire row view */
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (land){

                Dish dish;
                int adapterPosition = getAdapterPosition();
                dish = dishList.get(adapterPosition);
                Bundle thisBundle = new Bundle();
                thisBundle.putParcelable("DISH", dish);

                DishDetailsFragment dishDetailsFragment = new DishDetailsFragment();
                dishDetailsFragment.setArguments(thisBundle);
                dishDetailsFragment.setLand(land);
                ((MainActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.steps_container, dishDetailsFragment)
                        .commit();

                Intent intent = new Intent(context, DishWidgetProvider.class);
                intent.setAction("com.example.ga.BakingAgain.action.Dish");
                intent.putExtra("dish", dish);
                context.sendBroadcast(intent);


            }else {
                Dish dish;
                int adapterPosition = getAdapterPosition();
                dish = dishList.get(adapterPosition);
                Intent intent = new Intent(context, DishDetails.class);
                intent.putExtra("selectedDish", dish);
                intent.putExtra("i", adapterPosition);
                context.startActivity(intent);

                Intent intent1 = new Intent(context, DishWidgetProvider.class);
                intent1.setAction("com.example.ga.BakingAgain.action.Dish");
                intent1.putExtra("dish", dish);
                context.sendBroadcast(intent1);
            }

        }
    }
    public void clear() {
        int size = getItemCount();
        this.dishList.clear();
        notifyItemRangeRemoved(0, size);

    }


    public void addAll(List<Dish> dishes) {
        this.dishList.addAll(dishes);
        notifyDataSetChanged();
    }

    public Dish getItem(int position) {
        return dishList.get(position);
    }

}
