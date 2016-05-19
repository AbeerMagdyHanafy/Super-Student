package com.example.superstudent.ToDoList;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.BTChat.BTChat;
import com.example.ToDoDB.DataBase;
import com.example.ToDoDB.DataBaseHelper;
import com.example.superstudent.Map.Map_Activity;
import com.example.superstudent.Materials.Materials_Activity;
import com.example.superstudent.Profile.Profile_Activity;
import com.example.superstudent.Quotes.Home_Activity;
import com.example.superstudent.R;

public class ToDo_Activity extends AppCompatActivity {

    ListView list_view;
    DataBaseHelper task;
    SimpleCursorAdapter list_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);


       getSupportActionBar();
        getActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo, menu);
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
            return true;
        } else if (id == R.id.materials) {
            intent = new Intent(getApplicationContext(), Materials_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.map) {
            intent = new Intent(getApplicationContext(), Map_Activity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.chat) {
            intent = new Intent(getApplicationContext(), BTChat.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
