package com.example.superstudent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.superstudent.Profile.Login_Activity;
import com.example.superstudent.Profile.SignUp_Activity;
import com.facebook.Profile;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    Profile profile;
    FirebaseAuth auth;

    SharedPreferences sharedpreferences;
    boolean First=true;
    String FirstTime ="FirstTime";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        profile=Profile.getCurrentProfile();
//        auth=FirebaseAuth.getInstance();
//        if(profile!=null || auth.getCurrentUser()!=null) {
//            Intent loggedin = new Intent(this, HomeMain.class);
//            startActivity(loggedin);
//            finish();
//        }
        setContentView(R.layout.activity_main);

//        ActionBar logo = getSupportActionBar();
//        logo.setDisplayShowHomeEnabled(true);
//        logo.setLogo(android.R.drawable.btn_star);
//        logo.setDisplayUseLogoEnabled(true);
        //getSupportActionBar().setTitle(" Super Student");


        sharedpreferences = getSharedPreferences(FirstTime, Context.MODE_PRIVATE);

        boolean first =sharedpreferences.getBoolean(FirstTime,true);

        if(!first)
        {
            sharedpreferences = getSharedPreferences(FirstTime, Context.MODE_PRIVATE);

            sharedpreferences.getBoolean(FirstTime,true);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            First =false;
            editor.putBoolean(FirstTime, First);
            editor.commit();
            Intent done = new Intent(this, HomeMain.class);
            startActivity(done);
        }

    }

    public void Login(View view) {
        Intent login = new Intent(this, Login_Activity.class);
        startActivity(login);
    }

    public void Sign_Up(View view) {
        Intent sign_up = new Intent(this, SignUp_Activity.class);
        startActivity(sign_up);
    }

}
