package com.hivefive.aiopthomology;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class PatientInfo extends AppCompatActivity {

    TextInputLayout age_text_layout, gender_text_layout, type_text_layout;
    AutoCompleteTextView age_auto_comp, gender_auto_comp, type_auto_comp;

    ArrayList<Integer> arrayList_age;
    ArrayList<String> arrayList_gender, arrayList_type;
    ArrayAdapter arrayAdapter_age, arrayAdapter_gender, arrayAdapter_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);



        age_text_layout = findViewById(R.id.menu_age);
        age_auto_comp = findViewById(R.id.age_auto);
        gender_text_layout = findViewById(R.id.menu_gender);
        gender_auto_comp = findViewById(R.id.gender_auto);
        type_text_layout = findViewById(R.id.menu_type);
        type_auto_comp = findViewById(R.id.type_auto);



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




    }
}