package com.geektech.storagelesson.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geektech.storagelesson.R;
import com.geektech.storagelesson.login.LoginService;

// Created by askar on 10/12/18.
public class LoginActivity extends AppCompatActivity
    implements View.OnClickListener {

    public static void start(Context context){
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    private EditText mNameInput;
    private EditText mPasswordInput;
    private Button mSignUpBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init(){
        mNameInput = findViewById(R.id.activity_login_name_input);
        mPasswordInput = findViewById(R.id.activity_login_password_input);
        mSignUpBtn = findViewById(R.id.activity_login_confirm);

        mSignUpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_login_confirm:
                trySignUp();
                break;
        }
    }

    private void trySignUp(){
        String name = mNameInput.getText().toString();
        String password = mPasswordInput.getText().toString();

        if (name.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Input is empty", Toast.LENGTH_SHORT).show();
        } else {
            LoginService.setUser(this, name, password);
            MainActivity.start(this);
            finish();
        }
    }
}
