package com.example.nutfreedom.liveat500pxv2;

import android.app.Application;

import com.example.nutfreedom.liveat500pxv2.manager.Contextor;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
