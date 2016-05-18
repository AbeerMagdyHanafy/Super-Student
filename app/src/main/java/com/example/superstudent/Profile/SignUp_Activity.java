package com.example.superstudent.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.superstudent.Quotes.Home_Activity;
import com.example.superstudent.R;


public class SignUp_Activity extends AppCompatActivity {

    EditText ed_username, ed_password, ed_email;
    Spinner spinner_position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        getSupportActionBar().setTitle(" Sign Up");

        ed_username = (EditText) findViewById(R.id.ed_username);
        ed_password = (EditText) findViewById(R.id.ed_password);
        ed_email = (EditText) findViewById(R.id.ed_email);

        spinner_position = (Spinner) findViewById(R.id.spinner_position);

    }

    public void submit(View view) {
        Intent submit = new Intent(this, Home_Activity.class);
        startActivity(submit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign__up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}
