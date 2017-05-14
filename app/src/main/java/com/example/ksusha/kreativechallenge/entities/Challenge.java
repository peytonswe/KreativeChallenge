package com.example.ksusha.kreativechallenge.entities;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Challenge implements Parcelable {

    @SerializedName("averageRating")
    private double mRating;
    @SerializedName("title")
    private String mName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("created")
    private long mDate;
    @SerializedName("longitude")
    private double mLatitude;
    @SerializedName("latitude")
    private double mLongitude;
    @SerializedName("id")
    private int mID;

    public Challenge() {
    }

    public Challenge(double rating, String name, String description, long date, double latitude, double longitude, int ID) {
        mRating = rating;
        mName = name;
        mDescription = description;
        mDate = date;
        mLatitude = latitude;
        mLongitude = longitude;
        mID = ID;
    }

    public double getRating(){
        return mRating;
    }

    public void setRating(double rating) {
        mRating = rating;
    }

    public String getName(){
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription(){
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(long date) {
        mDate = date;
    }

    public double getLatitude(){
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude(){
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public int getID () {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.mRating);
        dest.writeString(this.mName);
        dest.writeString(this.mDescription);
        dest.writeLong(this.mDate);
        dest.writeDouble(this.mLatitude);
        dest.writeDouble(this.mLongitude);
        dest.writeInt(this.mID);
    }

    protected Challenge(Parcel in) {
        this.mRating = in.readDouble();
        this.mName = in.readString();
        this.mDescription = in.readString();
        this.mDate = in.readLong();
        this.mLatitude = in.readDouble();
        this.mLongitude = in.readDouble();
        this.mID = in.readInt();
    }

    public static final Parcelable.Creator<Challenge> CREATOR = new Parcelable.Creator<Challenge>() {
        @Override
        public Challenge createFromParcel(Parcel source) {
            return new Challenge(source);
        }

        @Override
        public Challenge[] newArray(int size) {
            return new Challenge[size];
        }
    };
}
