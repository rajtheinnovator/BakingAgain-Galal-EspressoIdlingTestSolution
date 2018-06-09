package com.example.ga.bakingagain;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;


public class DishWidgetService extends IntentService {
    public static final String ACTION_WIDGET = "com.example.ga.BakingAgain.action.Dish";
    public DishWidgetService() {
        super("DishWidgetService");
    }
    public static void startAction (Context context , Dish dish){
        Intent intent = new Intent(context,DishWidgetService.class);
        intent.setAction(ACTION_WIDGET);
        intent.putExtra("dish",dish);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent( Intent intent) {
        if (intent!=null){
            final String action = intent.getAction();
            if (ACTION_WIDGET.equals(action)){
                Dish dish = intent.getParcelableExtra("dish");
                handleUpdateWidget(dish);
            }
        }
    }
    public void handleUpdateWidget(Dish dish){

        AppWidgetManager appWidgetManager  = AppWidgetManager.getInstance(this);
        int [] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,
                DishWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.appwidget_Ingredients);
        DishWidgetProvider.updateDishWidgetProvider(this, appWidgetManager, appWidgetIds, dish);
    }
}
