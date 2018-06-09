package com.example.ga.bakingagain;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Dish>> {
    private static final String REQUEST_URL =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private static final int Movie_LOADER_ID = 1;
    private ArrayList<Dish> dishArrayList;
    private FragmentManager fragmentManager;
    private DishFragment dishFragment;
    private boolean Land;
    private SimpleIdlingResource mIdlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource(){
        if(mIdlingResource == null){
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getIdlingResource();

        if (findViewById(R.id.Land) != null){
            Land =true;

        }else {
            Land = false;
        }

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(Movie_LOADER_ID, null, this);

            dishFragment = new DishFragment();
            dishArrayList = new ArrayList<>();

            fragmentManager = getSupportFragmentManager();

    }

    @Override
    public Loader<List<Dish>> onCreateLoader(int id, Bundle args) {
        DishLoader dishLoader = new DishLoader(this, REQUEST_URL);

        return dishLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Dish>> loader, List<Dish> dishes) {
        mIdlingResource.setIdleState(false);
        dishArrayList.addAll(dishes);
/*        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("d", dishArrayList);
        dishFragment.setArguments(bundle);
        */
        dishFragment.setDishList(dishArrayList);
        dishFragment.setLand(Land);
        fragmentManager.beginTransaction()
                .replace(R.id.DishFragment, dishFragment)
                .commit();
        mIdlingResource.setIdleState(true);

    }

    @Override
    public void onLoaderReset(Loader<List<Dish>> loader) {
        //dishArrayList.clear();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mIdlingResource.setIdleState(false);

    }
}
