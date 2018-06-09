package com.example.ga.bakingagain;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class DishDetails extends AppCompatActivity {
    private Dish selectedDish;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_details);

        Intent intent = getIntent();
        selectedDish = intent.getParcelableExtra("selectedDish");
        DishDetailsFragment dishDetailsFragment = new DishDetailsFragment();
        Bundle thisBundle = new Bundle();
        thisBundle.putParcelable("DISH", selectedDish);
        dishDetailsFragment.setArguments(thisBundle);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.steps_container, dishDetailsFragment)
                .commit();

    }
}
