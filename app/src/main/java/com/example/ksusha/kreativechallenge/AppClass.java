package com.example.ksusha.kreativechallenge;


import android.app.Application;

public class AppClass extends Application {

    private static DBHelper dbHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DBHelper(this);
    }

    public static DBHelper getDbHelper() {
        return dbHelper;
    }
}
