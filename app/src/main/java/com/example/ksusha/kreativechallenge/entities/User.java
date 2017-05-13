package com.example.ksusha.kreativechallenge.entities;


public class User {

    private String userName;
    private String userSurname;

    public User(){

    }

    public User(String name, String surname) {
        userName = name;
        userSurname = surname;
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
        userName = surname;
    }
}
