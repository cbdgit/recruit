package com.example.asus.recruit.application;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static Context sContext;
    private static String cookie;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        cookie = "";
    }

    public static String getCookie() {
        return cookie;
    }

    public static void setCookie(String cookie) {
        MyApplication.cookie = cookie;
    }

    public static Context getContext(){
        return sContext;
    }
}
