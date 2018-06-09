package com.example.ga.bakingagain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Dish implements Parcelable {
    public static final Creator CREATOR = new Creator() {
        @Override
        public Dish createFromParcel(Parcel parcel) {
            return new Dish(parcel);
        }

        @Override
        public Dish[] newArray(int i) {
            return new Dish[i];
        }
    };
    private String id ;
    private String name;
    private List<Ingredients> ingredients;
    private List<Steps> steps;
    private String servings;
    private String image;
    public Dish(){super();}
    public Dish (Parcel parcel){
        this.id = parcel.readString();
        this.name = parcel.readString();
        ingredients =new ArrayList<>();
        parcel.readList(ingredients, Ingredients.class.getClassLoader());
        steps =new ArrayList<>();
        parcel.readList(steps, Steps.class.getClassLoader());
        this.servings = parcel.readString();
        this.image = parcel.readString();

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeList(ingredients);
        parcel.writeList(steps);
        parcel.writeString(servings);
        parcel.writeString(image);

    }



    public Dish(String id, String name, List<Ingredients> ingredients, List<Steps> steps, String servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public String getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }


}
