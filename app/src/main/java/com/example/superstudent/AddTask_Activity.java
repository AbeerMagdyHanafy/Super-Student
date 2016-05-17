package com.example.superstudent;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.DataBase.DataBase;
import com.example.DataBase.DataBaseHelper;


public class AddTask_Activity extends AppCompatActivity {

    EditText ed_Task_Name;
    EditText ed_Task_Description;
    Button bt_save;
    DataBaseHelper newTask ;

    // public String taskName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        ActionBar logo = getSupportActionBar();
        logo.setTitle("Add Task");

        ed_Task_Name = (EditText) findViewById(R.id.ed_Task_Name);
        ed_Task_Description = (EditText) findViewById(R.id.ed_Task_Description);
        bt_save = (Button) findViewById(R.id.bt_save);


        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!ed_Task_Name.getText().toString().isEmpty()) && (!ed_Task_Description.getText().toString().isEmpty())) {
                    String taskName = ed_Task_Name.getText().toString();
                    String taskDescription = ed_Task_Description.getText().toString();

                    newTask = new DataBaseHelper(getApplicationContext());
                    SQLiteDatabase db = newTask.getWritableDatabase();
                    //new ContentValues , put in row (ContentValues)
                    ContentValues row = new ContentValues();
                    row.put(DataBase.columns.Task_Name, taskName);
                    row.put(DataBase.columns.Task_Description, taskDescription);

                    ///write DB , insert DB, close DB
                    db.insert(DataBase.Table, null, row); ///table,null,ContentValues
                    newTask.close();

                    Toast.makeText(getApplicationContext(), "Task Added", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplication(), ToDo_Activity.class);
                    intent.putExtra("Desc", taskName);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please Write Your Task!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
