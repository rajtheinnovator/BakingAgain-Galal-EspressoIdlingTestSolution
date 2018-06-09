package com.example.ga.bakingagain;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DishUtils {
    private static final String LOG_TAG = DishUtils.class.getSimpleName();

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the Dish JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder outPut = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                outPut.append(line);
                line = reader.readLine();
            }
        }
        return outPut.toString();
    }

    private static List<Dish> extractFeatureFromJson(String DishJSON) {
        if (TextUtils.isEmpty(DishJSON)) {
            return null;
        }
        List<Dish> Dishes = new ArrayList<>();


        try {
            JSONArray baseJsonResponse = new JSONArray(DishJSON);
            for (int i = 0; i < baseJsonResponse.length(); i++) {


                JSONObject currentDish = baseJsonResponse.getJSONObject(i);
                String id = currentDish.getString("id");
                String name =currentDish.getString("name");
                String serving = currentDish.getString("servings");
                String image =currentDish.getString("image");
                List<Ingredients> ingredients = new ArrayList<>();




                for (int j = 0; j < currentDish.getJSONArray("ingredients").length(); j++) {

                    String quantity = currentDish.getJSONArray("ingredients").getJSONObject(j).getString("quantity");
                    String measure = currentDish.getJSONArray("ingredients").getJSONObject(j).getString("measure");
                    String ingredient = currentDish.getJSONArray("ingredients").getJSONObject(j).getString("ingredient");
                    Ingredients ingredients1 = new Ingredients(quantity,measure,ingredient);
                    ingredients.add(ingredients1);
                }
                List<Steps> steps = new ArrayList<>();

                for (int t = 0; t< currentDish.getJSONArray("steps").length(); t++) {
                    String idd = currentDish.getJSONArray("steps").getJSONObject(t).getString("id");
                    String shortDescription = currentDish.getJSONArray("steps").getJSONObject(t).getString("shortDescription");
                    String description = currentDish.getJSONArray("steps").getJSONObject(t).getString("description");
                    String videoURL = currentDish.getJSONArray("steps").getJSONObject(t).optString("videoURL");
                    String thumbnailURL = currentDish.getJSONArray("steps").getJSONObject(t).getString("thumbnailURL");
                    Steps steps1 = new Steps(idd,shortDescription,description,videoURL,thumbnailURL);
                    steps.add(steps1);
                }


                Dish dishes = new Dish( id,name,ingredients, steps ,serving,image);
                Dishes.add(dishes);

            }
        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the Dish JSON results", e);
        }
        return Dishes;
    }
    public static List<Dish> fetchDishData(String requestUrl) {
        URL url=createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse=makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        List<Dish> Dishes =extractFeatureFromJson(jsonResponse);
        return Dishes;
    }
}
