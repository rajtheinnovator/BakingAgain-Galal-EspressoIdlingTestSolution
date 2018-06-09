package com.example.ga.bakingagain;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DishDetailsFragment extends Fragment {
    private Dish dish ;
    private List<Ingredients> mListOfIngredients;
    private List<Steps> mListOfSteps ;
    private RecyclerView StepListView;
    private StepsAdapter sAdapter;
    private Context c ;
    private boolean land;



    public DishDetailsFragment() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dish_details_fragment, container, false);
        Bundle bundle =this.getArguments();
        dish = bundle.getParcelable("DISH");
        String DishName=dish.getName();
        TextView dishName =rootView.findViewById(R.id.recipeName);
        dishName.setText(DishName);

        mListOfIngredients = dish.getIngredients();
        TextView textView =  rootView.findViewById(R.id.ingre);

        StringBuffer stringBuffer = new StringBuffer();
        for (int h = 0 ;h < mListOfIngredients.size();h++) {
            stringBuffer.append(mListOfIngredients.get(h).getQuantity()+" "+mListOfIngredients.get(h).getMeasure()+" "+mListOfIngredients.get(h).getIngredient() + "\n");

        }
        textView.setText(stringBuffer);
        c= getContext();

        mListOfSteps = dish.getSteps();
        StepListView =  rootView.findViewById(R.id.StepsRecycle);
        StepListView.setLayoutManager(new LinearLayoutManager(c));

        sAdapter = new StepsAdapter( dish,mListOfSteps,c,land);

        StepListView.setAdapter(sAdapter);


        return rootView;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void setLand(boolean land) {
        this.land = land;
    }
}
