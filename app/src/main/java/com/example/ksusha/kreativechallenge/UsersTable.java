package com.example.ksusha.kreativechallenge;


import android.provider.BaseColumns;

public final class UsersTable {
    //Table "UsersTable"
    public static final String TABLE_NAME = "users";

    public static final String _ID = "_ID";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_SURNAME = "surname";

    public int userID;
    public String userName;
    public String userSurname;

    public int getId() {
        return userID;
    }

    public void setId(int id) {
        userID = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        userName = name;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String surname) {
        userSurname = surname;
    }

}
