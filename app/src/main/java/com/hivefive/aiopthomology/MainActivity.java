package com.hivefive.aiopthomology;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    static int splash_scr = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                sp = getSharedPreferences("datafile", MODE_PRIVATE);
//                if(sp.contains("Username")){
//
//                    Intent intent = new Intent(MainActivity.this, MainScreen.class);
//                    intent.putExtra("user_fname", sp.getString("Full Name", ""));
//                    intent.putExtra("username", sp.getString("Username", ""));
//                    intent.putExtra("user_email", sp.getString("Email", ""));
//                    intent.putExtra("user_password", sp.getString("Password", ""));
//                    startActivity(intent);
//                    finish();
//                }
//
//                else {
//                    Intent intent = new Intent(MainActivity.this, LoginScreen.class);
//                    startActivity(intent);
//                    finish();
//                }

                Intent intent = new Intent(MainActivity.this, LoginScreen.class);
                startActivity(intent);
                finishAffinity();

            }
        }, splash_scr);




    }
}