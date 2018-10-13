package com.geektech.storagelesson.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.geektech.storagelesson.R;
import com.geektech.storagelesson.login.LoginService;
import com.geektech.storagelesson.storage.StorageService;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    private final int EXTERNAL_STORAGE_PERMISSION_CODE = 12345;

    public static void start(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private TextView mMessageTV;
    private Button mLogout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        mMessageTV = findViewById(R.id.activity_main_message);
        mLogout = findViewById(R.id.activity_main_logout);

        mLogout.setOnClickListener(this);
        mMessageTV.setText("Hello " + LoginService.getUserName(this));

        checkPermission();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_logout:
                logout();
                break;
        }
    }

    private void logout(){
        LoginService.deleteUser(this);
        LoginActivity.start(this);
        finish();
    }

    //region Permission

    private void checkPermission(){
        //Смотрим есть ли у нас разрешение
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){
            //Разрешение уже есть
            saveData();
        } else {
            //Необходимо запросить
            requestPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case EXTERNAL_STORAGE_PERMISSION_CODE: {
                //Если разрешение дано
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    saveData();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void requestPermission(){
        //shouldShowRequestPermissionRationale возвращает true, если пользователь уже один раз отказался
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Please grant access to storage", Toast.LENGTH_SHORT).show();
            startRequest();
        } else {
            startRequest();
        }
    }

    private void startRequest(){
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                EXTERNAL_STORAGE_PERMISSION_CODE);
    }

    private void saveData(){
        StorageService.saveToExternalPublic(
                LoginService.getUserName(this).getBytes(),
                "Hello.txt"
        );

        StorageService.saveToExternalPrivate(
                getApplicationContext(),
                LoginService.getUserName(this).getBytes(),
                "Hello.txt"
        );
    }

    //endregion
}
