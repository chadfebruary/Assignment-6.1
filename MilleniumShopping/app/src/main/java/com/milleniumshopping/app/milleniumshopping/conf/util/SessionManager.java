package com.milleniumshopping.app.milleniumshopping.conf.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * Created by Chad on 4/21/2016.
 */
public class SessionManager {

    //LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    //Shared preferences
    SharedPreferences preferences;
    Editor editor;
    Context context;

    //Shared preferences mode
    int PRIVATE_MODE = 0;

    //Shared preferences file name
    private static final String PREFERENCE_NAME = "AndroidHiveLogin";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context)
    {
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void setLogin(boolean isLoggedIn)
    {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
        Log.d(TAG, "User login session modified");
    }

    public boolean isLoggedIn()
    {
        return preferences.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}
