package com.example.superstudent;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ToDoDB.DataBase;
import com.example.ToDoDB.DataBaseHelper;


public class Show_Activity extends AppCompatActivity {

    TextView name;
    TextView Description;
    String tempName;
    String tempDesc;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        ActionBar logo = getSupportActionBar();
        logo.setTitle("Edit Task");

        tempName = getIntent().getExtras().getString("TaskName");
        tempDesc = getIntent().getExtras().getString("TaskDescription");


        name = (TextView) findViewById(R.id.tv_text_name);
        Description = (TextView) findViewById(R.id.tv_text_desc);

        name.setText(tempName);
        Description.setText(tempDesc);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(Show_Activity.this);
                dialog.setContentView(R.layout.activity_add_task);
                dialog.setTitle("Editing " + tempName);
                final TextView updatedName = (TextView) dialog.findViewById(R.id.ed_Task_Name);
                final TextView updatedDes = (TextView) dialog.findViewById(R.id.ed_Task_Description);

                Button save = (Button) dialog.findViewById(R.id.bt_save);
                updatedName.setText(tempName);
                updatedName.setTextColor(Color.BLUE);
                updatedDes.setText(tempDesc);
                updatedDes.setTextColor(Color.BLUE);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String Name = updatedName.getText().toString();
                        String Description = updatedDes.getText().toString();

                        dataBaseHelper = new DataBaseHelper(Show_Activity.this);
                        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(DataBase.columns.Task_Name, Name);
                        values.put(DataBase.columns.Task_Description, Description);

                        db.update(DataBase.Table, values, "task_Name=? and task_Description=?", new String[]{tempName,
                                tempDesc});

                        dialog.dismiss();
                        Toast.makeText(Show_Activity.this, Name, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                dialog.show();

            }
        });

}
    }