package com.hivefive.aiopthomology;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class ResultPage extends AppCompatActivity {


    float result_confidence;
    String patient_name, result_pat;

    TextView name, result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        getSupportActionBar().setTitle("Patient's Report");


        result_confidence = getIntent().getFloatExtra("result", 0.000000000f);
        patient_name = getIntent().getStringExtra("patient name");


        name = findViewById(R.id.patient_name);
        result = findViewById(R.id.result);

        if (result_confidence > 0.5){
            result_pat = "class 1";
        }else{
            result_pat = "class 0";
        }

        name.setText(patient_name);
        result.setText(result_pat);








    }
}