package com.bawei.cinema.app;

import android.app.Application;
import android.content.Context;

/**
 * author: 徐涛
 * data: 2019/11/5 19:19:37
 * function:
 */
public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
