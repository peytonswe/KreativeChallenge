package com.example.ksusha.kreativechallenge;

public class ChallengesTable {

    public static final String TABLE_NAME = "challenges";

    public static final String _ID = "_ID";
    public static final String COLUMN_CHALLENGE_NAME = "name";
    public static final String COLUMN_CHALLENGE_DESCRIPTION = "description";
    public static final String COLUMN_CHALLENGE_RATING = "rating";
    public static final String COLUMN_CHALLENGE_DATE = "date";
    public static final String COLUMN_CHALLENGE_LATITUDE = "latitude";
    public static final String COLUMN_CHALLENGE_LONGITUDE = "longitude";

    private double challengeRating;
    private String challengeName;
    private String challengeDescription;
    private long challengeDate;
    private double challengeLatitude;
    private double challengeLongitude;
    private int challengeID;

    public double getRating(){
        return challengeRating;
    }

    public void setRating(double rating) {
        challengeRating = rating;
    }

    public String getDescription(){
        return challengeDescription;
    }

    public void setDescription(String description) {
        challengeDescription = description;
    }

    public String getName(){
        return challengeName;
    }

    public void setName(String name) {
        challengeName = name;
    }

    public long getDate(){
        return challengeDate;
    }

    public void setDate(long date) {
        challengeDate = date;
    }

    public double getLatitude(){
        return challengeLatitude;
    }

    public void setLatitude(double latitude) {
        challengeLatitude = latitude;
    }

    public double getLongitude(){
        return challengeLongitude;
    }

    public void setLongitude(double longtitude) {
        challengeLongitude = longtitude;
    }

    public int getId(){
        return challengeID;
    }

    public void setId(int id) {
        challengeID = id;
    }

}
