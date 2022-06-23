package com.example.dairaapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

// singleton class.
public class CurrentUser extends Application {

    private static final CurrentUser ourInstance = new CurrentUser();
    public static CurrentUser getInstance() {
        return ourInstance;
    }
    private CurrentUser() {}

    public String getUserName(Context context) {
        SharedPreferences sharedPreferences;
        sharedPreferences = context.getApplicationContext().getSharedPreferences("auth", 0);
        return sharedPreferences.getString("usrename", "");
    }
}
