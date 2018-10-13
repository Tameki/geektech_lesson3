package com.geektech.storagelesson.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.geektech.storagelesson.R;
import com.geektech.storagelesson.login.LoginService;

// Created by askar on 10/12/18.
public class LaunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        init();
    }

    private void init(){
        //Проверяем есть ли какие либо данные о юзере
        if (LoginService.contains(this)){
            PasswordActivity.start(this);
        } else {
            LoginActivity.start(this);
        }
        finish();
    }
}
