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
public class PasswordActivity extends AppCompatActivity
        implements View.OnClickListener {

    public static void start(Context context){
        context.startActivity(new Intent(context, PasswordActivity.class));
    }

    private EditText mPasswordInput;
    private Button mConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        init();
    }

    private void init(){
        mPasswordInput = findViewById(R.id.activity_password_input);
        mConfirm = findViewById(R.id.activity_password_confirm);

        mConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_password_confirm:
                checkPassword();
                break;
        }
    }

    private void checkPassword(){
        String password = mPasswordInput.getText().toString();

        if (!password.isEmpty() &&
                password.equals(LoginService.getPassword(this))){
            MainActivity.start(this);
            finish();
        } else {
            Toast.makeText(this, "Password is incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}
