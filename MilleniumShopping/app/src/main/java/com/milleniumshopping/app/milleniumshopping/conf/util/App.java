package com.milleniumshopping.app.milleniumshopping.conf.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Chad on 5/8/2016.
 */
public class App extends Application {

    private static Context context;


    private static App singleton;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        singleton = this;
    }

    public static Context getAppContext() {
        return App.context;
    }


}

