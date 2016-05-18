package com.example.superstudent.Materials;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.superstudent.R;

public class Year3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year3);
        ActionBar logo = getSupportActionBar();
        logo.setTitle("Year Three");

    }
}
