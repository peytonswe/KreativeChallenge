package com.example.ksusha.kreativechallenge;


public class Challenge {

    private double mRating;
    private String mName;
    private String mDescription;
    private long mDate;
    private double mLatitude;
    private double mLongitude;
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

    public void setLongitude(double longtitude) {
        mLongitude = longtitude;
    }

    public int getID () {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }
 }
