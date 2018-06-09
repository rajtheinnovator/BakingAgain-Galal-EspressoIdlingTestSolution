package com.example.ga.bakingagain;

import android.os.Parcel;
import android.os.Parcelable;

public class Steps implements Parcelable{
    private String stepNumber;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public  Steps(){super();}
    public Steps(Parcel parcel){
        this.stepNumber = parcel.readString();
        this.shortDescription = parcel.readString();
        this.description = parcel.readString();
        this.videoURL = parcel.readString();
        this.thumbnailURL = parcel.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.stepNumber);
        dest.writeString(this.shortDescription);
        dest.writeString(this.description);
        dest.writeString(this.videoURL);
        dest.writeString(this.thumbnailURL);

    }
    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel source) {
            return new Steps(source);
        }

        @Override
        public Steps[] newArray(int i) {
            return new Steps[i];
        }
    };


    public Steps(String stepNumber, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.stepNumber = stepNumber;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public String getStepNumber() {
        return stepNumber;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }


}
