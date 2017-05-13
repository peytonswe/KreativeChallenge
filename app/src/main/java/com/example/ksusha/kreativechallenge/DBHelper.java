package com.example.ksusha.kreativechallenge;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "challenge.db";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USER_TABLE = "CREATE_TABLE" + UsersTable.TABLE_NAME + " ("
                + UsersTable._ID  + " INTEGER PRIMARY KEY,"
                + UsersTable.COLUMN_USER_NAME + "TEXT,"
                + UsersTable.COLUMN_USER_SURNAME + "TEXT)";

        String SQL_CREATE_CHALLENGE_TABLE = "CREATE_TABLE" + ChallengesTable.TABLE_NAME + " ("
                + ChallengesTable._ID + " INTEGER PRIMARY KEY,"
                + ChallengesTable.COLUMN_CHALLENGE_NAME + "TEXT,"
                + ChallengesTable.COLUMN_CHALLENGE_DESCRIPTION + "TEXT,"
                + ChallengesTable.COLUMN_CHALLENGE_RATING + "DOUBLE,"
                + ChallengesTable.COLUMN_CHALLENGE_DATE + "LONG,"
                + ChallengesTable.COLUMN_CHALLENGE_LATITUDE + "DOUBLE,"
                + ChallengesTable.COLUMN_CHALLENGE_LONGITUDE + "DOUBLE)";

        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(SQL_CREATE_CHALLENGE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
