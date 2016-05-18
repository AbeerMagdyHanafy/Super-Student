package com.example.superstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.BTChat.BTChat;


public class Materials_Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);

        ActionBar logo = getSupportActionBar();
        logo.setTitle("Materials");
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_materials, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.home) {
            intent = new Intent(getApplicationContext(), Home_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.profile) {
            intent = new Intent(getApplicationContext(), Profile_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.to_do_list) {
            intent = new Intent(getApplicationContext(), ToDo_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.materials) {
            return true;
        } else if (id == R.id.map) {
            intent = new Intent(getApplicationContext(), Map_Activity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.chat) {
            intent = new Intent(getApplicationContext(), BTChat.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Choose(View view)
    {
        if(view.getId()==R.id.year1btn)
        {
            Intent done = new Intent(this, Year1.class);
            startActivity(done);
        }
        if(view.getId()==R.id.year2btn)
        {
            Intent done = new Intent(this, Year2.class);
            startActivity(done);
        }
        if(view.getId()==R.id.year3btn)
        {
            Intent done = new Intent(this, Year3.class);
            startActivity(done);
        }
        if(view.getId()==R.id.year4btn)
        {
            Intent done = new Intent(this, Year4.class);
            startActivity(done);
        }
    }


}
