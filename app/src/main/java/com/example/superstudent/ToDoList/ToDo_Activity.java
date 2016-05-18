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
import com.example.superstudent.Show_Activity;

public class ToDo_Activity extends AppCompatActivity {

    ListView list_view;
    DataBaseHelper task;
    SimpleCursorAdapter list_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        ActionBar logo = getSupportActionBar();
        logo.setTitle("To Do");


        getIntent();
        list_view = (ListView) findViewById(R.id.list_view);
        task = new DataBaseHelper(this) ; ///obj from class DB

        Show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(), AddTask_Activity.class);
                startActivity(intent);

            }
        });



        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = (Cursor) list_view.getItemAtPosition(position);
                 String  Name = cursor.getString(cursor.getColumnIndexOrThrow("task_Name"));
                String Des = cursor.getString(cursor.getColumnIndexOrThrow("task_Description"));


                Intent intent = new Intent(getApplicationContext(), Show_Activity.class);
                intent.putExtra("TaskName",Name);
                intent.putExtra("TaskDescription",Des);
                startActivity(intent);

            }
        });


        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ToDo_Activity.this);
                alertDialogBuilder
                        .setMessage("Do You Want To Delete This Task??")
                        .setCancelable(false)
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Cursor cursor = (Cursor) list_view.getItemAtPosition(position);
                                String taskToBedeleted = cursor.getString(cursor.getColumnIndexOrThrow("task_Name"));

                                String sql = String.format("DELETE FROM %s WHERE %s = '%s'",
                                        DataBase.Table,
                                        DataBase.columns.Task_Name,
                                        taskToBedeleted);
                                SQLiteDatabase sqlda = task.getWritableDatabase();
                                sqlda.execSQL(sql);
                                Toast.makeText(ToDo_Activity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                Show();
                            }
                        })
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();

                return true;
            }
        });

    }
    public void Show() {
        task = new DataBaseHelper(ToDo_Activity.this);
        SQLiteDatabase sqldb = task.getReadableDatabase();
        Cursor cursor = sqldb.query(DataBase.Table, new String[]{DataBase.columns.ID, DataBase.columns.Task_Name,
                DataBase.columns.Task_Description}, null, null, null, null, null);

        list_Adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor, new String[]{
                DataBase.columns.Task_Name},new int[]{android.R.id.text1},0);
        list_view.setAdapter(list_Adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Show();
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
