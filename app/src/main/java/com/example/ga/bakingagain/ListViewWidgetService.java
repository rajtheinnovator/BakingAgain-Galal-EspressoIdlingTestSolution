package com.example.ga.bakingagain;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

public class ListViewWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        Dish dish =intent.getParcelableExtra("dish");
        List<Ingredients> ingredients = dish.getIngredients();
        AppWidgetListView appWidgetListView = new AppWidgetListView(this.getApplicationContext(),ingredients);


        return appWidgetListView;
    }
}
    class AppWidgetListView implements RemoteViewsService.RemoteViewsFactory{
        private List<Ingredients> ingredients;
        private Context context;

        public AppWidgetListView( Context context,List<Ingredients> ingredients) {
            this.ingredients = ingredients;
            this.context = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return ingredients.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views =new RemoteViews(context.getPackageName(),R.layout.ingredients_list);
            Ingredients ingredients1 = ingredients.get(position);
            String s = ingredients1.getQuantity()+""+ingredients1.getMeasure()+""+ingredients1.getIngredient();
            views.setTextViewText(R.id.ingredient_item,s);
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }

