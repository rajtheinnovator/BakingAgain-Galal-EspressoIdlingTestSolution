package com.example.ga.bakingagain;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class DishWidgetProvider extends AppWidgetProvider {
    private static Dish selectedDish = null;
    private static List<Ingredients> ingredientsList;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId ,Dish dish) {
        selectedDish = dish;
        CharSequence DishName;
        CharSequence s;
        if (selectedDish== null){
            DishName = "no recipe yet";
            s="plz select recipe";

        }else {
            ingredientsList = selectedDish.getIngredients();
            DishName = selectedDish.getName();

            StringBuffer stringBuffer = new StringBuffer();
            for (int h = 0; h < ingredientsList.size(); h++) {
                stringBuffer.append(ingredientsList.get(h).getQuantity() + " " + ingredientsList.get(h).getMeasure() + " " + ingredientsList.get(h).getIngredient() + "\n");

            }
            s=stringBuffer.toString();
        }



        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.dish_widget_provider);
        views.setTextViewText(R.id.appwidget_text,DishName);
        views.setTextViewText(R.id.appwidget_Ingredients,s);


        Intent intent1 = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
        views.setOnClickPendingIntent(R.id.appwidget_Ingredients, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        DishWidgetService.startAction(context,selectedDish);

    }
    public static void updateDishWidgetProvider(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds,Dish dish){
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId,dish);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        selectedDish = intent.getParcelableExtra("dish");
        DishWidgetService.startAction(context,selectedDish);
    }

}

