package com.example.ga.bakingagain;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private  Dish dish;
    private  List<Steps> stepsList;
    private Context context;
    private boolean land;


    public StepsAdapter(Dish dish, List<Steps> stepsList, Context context, boolean land) {
        this.dish = dish;
        this.stepsList = stepsList;
        this.context = context;
        this.land = land;
    }

    public StepsAdapter(Dish selectedDish, Context context, List<Steps> stepsList1) {
        this.dish = selectedDish;
        this.context = context;
        this.stepsList = stepsList1;
    }

    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_list, parent, false);
        ViewHolder viewHolder = new StepsAdapter.ViewHolder(context,v);
        return  viewHolder ;
    }

    @Override
    public void onBindViewHolder(final StepsAdapter.ViewHolder holder, final int position) {
        Steps step = stepsList.get(position);
        String dName = step.getShortDescription();
        String tryName = dish.getName();
        holder.StepName.setText(dName);




    }

    @Override
    public int getItemCount() {
        if (stepsList != null) {
            return stepsList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView StepName;
        public LinearLayout StepLinearLayout;
        private Context context;

        public ViewHolder(Context context, View itemView) {
            super(itemView);

            StepName = (TextView) itemView.findViewById(R.id.Step);
            StepLinearLayout = (LinearLayout) itemView.findViewById(R.id.Step_linearLayout);
            this.context = context;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int i = getAdapterPosition();
            Bundle bundle = new Bundle();
            bundle.putParcelable("DISH", dish);
            bundle.putInt("i",i);
            VideoFragment videoFragment = new VideoFragment();
            videoFragment.setArguments(bundle);
            if (land){
                Toast.makeText(context,"clicked "+i,Toast.LENGTH_LONG).show();
                ((MainActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.video_container, videoFragment)
                        .commit();

            }else {

                ((DishDetails) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.video_container, videoFragment)
                        .commit();
            }
        }
    }
}
