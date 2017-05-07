package com.example.superstudent;

import android.content.Intent;
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

    }

    public void Login(View view) {
        Intent login = new Intent(this, Login_Activity.class);
        startActivity(login);
    }

    public void Sign_Up(View view) {
        Intent sign_up = new Intent(this, SignUp_Activity.class);
        startActivity(sign_up);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }*/
}
