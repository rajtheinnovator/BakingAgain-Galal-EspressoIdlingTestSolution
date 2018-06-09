package com.example.ga.bakingagain;

import android.content.Context;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DishFragment extends Fragment  {
    private static final String DishKey = "DishList";
    private DishRecyclerAdapter sAdapter;
    private RecyclerView recyclerView;
    private List<Dish> dishList;
    private boolean land;
    private Context c;
    public DishFragment() {
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dish_fragment, container, false);
/*        Bundle bundle =this.getArguments();
        dishList = bundle.getParcelableArrayList("d");*/

            c = getContext();

            recyclerView = rootView.findViewById(R.id.DishR);
            recyclerView.setLayoutManager(new LinearLayoutManager(c));

            sAdapter = new DishRecyclerAdapter(dishList, getContext(),land);

            recyclerView.setAdapter(sAdapter);

            return rootView;

    }

    public void setLand(boolean land) {
        this.land = land;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(DishKey, (ArrayList<Dish>) dishList);
    }
}
