package com.example.ga.bakingagain;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class DishLoader extends AsyncTaskLoader {
    private static final String  LOG_TAG = DishLoader.class.getName();
    private  String mUrl;

    public DishLoader(Context context, String url) {
        super(context);
        mUrl=url;
    }
    protected void onStartLoading(){forceLoad();}

    @Override
    public List<Dish> loadInBackground() {

        if (mUrl==null) {

            return null;

        }

        List<Dish> dishes= DishUtils.fetchDishData(mUrl);

        return dishes;

    }
}