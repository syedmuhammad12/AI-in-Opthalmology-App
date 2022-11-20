package com.hivefive.aiopthomology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class PatientInfo extends AppCompatActivity {

    float result_confidence;

    TextInputLayout age_text_layout, gender_text_layout, type_text_layout, patient_name, patient_mr;
    AutoCompleteTextView age_auto_comp, gender_auto_comp, type_auto_comp;

    ArrayList<Integer> arrayList_age;
    ArrayList<String> arrayList_gender, arrayList_type;
    ArrayAdapter arrayAdapter_age, arrayAdapter_gender, arrayAdapter_type;

    Button submit_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);
        getSupportActionBar().setTitle("Patient's Info");


        result_confidence = getIntent().getFloatExtra("result", 0.000000000f);

        age_text_layout = findViewById(R.id.menu_age);
        age_auto_comp = findViewById(R.id.age_auto);
        gender_text_layout = findViewById(R.id.menu_gender);
        gender_auto_comp = findViewById(R.id.gender_auto);
        type_text_layout = findViewById(R.id.menu_type);
        type_auto_comp = findViewById(R.id.type_auto);
        submit_but = findViewById(R.id.submit_but);
        patient_name = findViewById(R.id.name);


//        ****************************************************
        arrayList_age = new ArrayList<>();

        for(int i=1; i<=80; i++){
            arrayList_age.add(i);
        }

        arrayAdapter_age = new ArrayAdapter(getApplicationContext(), R.layout.age_dropdown_text, arrayList_age);
        age_auto_comp.setAdapter(arrayAdapter_age);
        age_auto_comp.setThreshold(1);

//       ******************************************************

        arrayList_gender = new ArrayList<>();
        arrayList_gender.add("Male");
        arrayList_gender.add("Female");


        arrayAdapter_gender = new ArrayAdapter(getApplicationContext(), R.layout.age_dropdown_text, arrayList_gender);
        gender_auto_comp.setAdapter(arrayAdapter_gender);
        gender_auto_comp.setThreshold(1);

//        *****************************************

        arrayList_type = new ArrayList<>();
        arrayList_type.add("I");
        arrayList_type.add("II");


        arrayAdapter_type = new ArrayAdapter(getApplicationContext(), R.layout.age_dropdown_text, arrayList_type);
        type_auto_comp.setAdapter(arrayAdapter_type);
        type_auto_comp.setThreshold(1);




        submit_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PatientInfo.this, ResultPage.class);
                Log.d("TAG", patient_name.getEditText().getText().toString());
                intent.putExtra("patient name", patient_name.getEditText().getText().toString());
                intent.putExtra("result", result_confidence);

                startActivity(intent);


            }
        });







    }
}