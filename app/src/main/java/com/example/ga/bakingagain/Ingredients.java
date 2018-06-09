package com.example.ga.bakingagain;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredients implements Parcelable{
    private String quantity;
    private String measure;
    private String ingredient;

    public  Ingredients(){super();}
    public Ingredients(Parcel parcel){
        this.quantity = parcel.readString();
        this.measure = parcel.readString();
        this.ingredient = parcel.readString();

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.quantity);
        parcel.writeString(this.measure);
        parcel.writeString(this.ingredient);

    }
    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel source) {
            return new Ingredients(source);
        }

        @Override
        public Ingredients[] newArray(int i) {
            return new Ingredients[i];
        }
    };



    public Ingredients(String quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }


}
