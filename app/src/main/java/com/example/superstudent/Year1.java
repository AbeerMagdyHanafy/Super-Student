package com.example.superstudent;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

import com.example.MaterialsDB.Material_DB_Helper;
import com.example.MaterialsDB.Material_Database;

public class Year1 extends AppCompatActivity {

    GridView grid_view;
    Material_DB_Helper link;
    SimpleCursorAdapter list_Adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year1);
        ActionBar logo = getSupportActionBar();
        logo.setTitle("Year One");
        ;

        grid_view = (GridView) findViewById(R.id.grid_year1);
        link = new Material_DB_Helper(this); ///obj from class DB

        Show();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddMaterialLink.class);
                startActivity(intent);

            }
        });
        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = (Cursor) grid_view.getItemAtPosition(position);

                String materiallink = cursor.getString(cursor.getColumnIndexOrThrow("Mat_Link"));
                Intent my_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(materiallink));
                startActivity(my_intent);


            }
        });
    }

    public void Show() {
        link = new Material_DB_Helper(Year1.this);
        SQLiteDatabase sqldb1 = link.getReadableDatabase();
        Cursor cursor1 = sqldb1.query(Material_Database.Mat_Table, new String[]{Material_Database.columns.ID2,
                Material_Database.columns.mat_name,
                Material_Database.columns.mat_link}, null, null, null, null, null);

        list_Adapter1 = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor1, new String[]{
                Material_Database.columns.mat_name}, new int[]{android.R.id.text1}, 0);
        grid_view.setAdapter(list_Adapter1);
    }


}

