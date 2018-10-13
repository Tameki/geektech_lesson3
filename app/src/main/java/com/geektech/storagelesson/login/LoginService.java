package com.geektech.storagelesson.login;

import android.content.Context;

import com.geektech.storagelesson.storage.SharedService;

// Created by askar on 10/12/18.
public class LoginService {
    public static void setUser(
            Context context,
            String name,
            String password
    ){
        SharedService.setString(context, UserConstants.USER_NAME_KEY, name);
        SharedService.setString(context, UserConstants.USER_PASSWORD_KEY, password);
    }

    public static String getUserName(Context context){
        return SharedService.getString(
                context,
                UserConstants.USER_NAME_KEY,
                ""
        );
    }

    public static Boolean contains(Context context){
        return SharedService.contains(context, UserConstants.USER_NAME_KEY);
    }

    public static String getPassword(Context context){
        return SharedService.getString(
                context,
                UserConstants.USER_PASSWORD_KEY,
                ""
        );
    }

    public static void deleteUser(Context context){
        SharedService.deleteShared(context, UserConstants.USER_NAME_KEY);
        SharedService.deleteShared(context, UserConstants.USER_PASSWORD_KEY);
    }
}
