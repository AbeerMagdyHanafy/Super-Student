package com.example.superstudent;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class Year4 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year4);
        ActionBar logo = getSupportActionBar();
        logo.setTitle("Year Four");

    }
}
