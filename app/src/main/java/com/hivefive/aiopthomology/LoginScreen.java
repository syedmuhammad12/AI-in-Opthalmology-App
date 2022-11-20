package com.hivefive.aiopthomology;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginScreen extends AppCompatActivity {

    TextInputLayout email, pass;
    Button login_but;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getSupportActionBar().hide();
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        email = findViewById(R.id.textInputEmail);
        pass = findViewById(R.id.textInputPassword);
        login_but = findViewById(R.id.login_but);

        login_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = email.getEditText().getText().toString();
                String pas = pass.getEditText().getText().toString();

                if (mail.equals("ncl@gmail.com") && pas.equals("ncl")){

                    Intent intent = new Intent(LoginScreen.this, ImageOptionsMenu.class);
                    startActivity(intent);
                    finishAffinity();

                }else{
                    Toast.makeText(LoginScreen.this, "Credential not found", Toast.LENGTH_SHORT).show();
                }



            }
        });





    }

    public void onLoginClick(View View){


    }

}