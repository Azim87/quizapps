package com.kubatov.quizapp;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }
}
