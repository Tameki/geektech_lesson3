package com.geektech.storagelesson.storage;

import android.content.Context;
import android.content.SharedPreferences;

// Created by askar on 10/12/18.
public class SharedService {
    private static String PREFERENCE_FILE_NAME = "storagelesson";

    private static SharedPreferences getShared(Context context){
        return context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static Boolean setString(
            Context context,
            String key,
            String value
    ){
        return getShared(context).edit()
                .putString(key, value)
                .commit();
    }

    public static String getString(
            Context context,
            String key,
            String defaultValue
    ){
        return getShared(context).getString(key, defaultValue);
    }

    public static Boolean deleteShared(
            Context context,
            String key
    ){
        return getShared(context).edit()
                .remove(key)
                .commit();
    }

    public static Boolean contains(
            Context context,
            String key
    ){
        return getShared(context).contains(key);
    }
}
