package com.example.superstudent.Materials;

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


import com.example.MaterialsDB.Material_DB_Helper;
import com.example.MaterialsDB.Material_Database;
import com.example.superstudent.R;

public class AddMaterialLink extends AppCompatActivity {

    EditText addlinkname;
    EditText addlinkurl;
    Button save;
    Material_DB_Helper newLink;
    String linkurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material_link);
        ActionBar logo = getSupportActionBar();
        logo.setTitle("Add Link");

        addlinkname=(EditText) findViewById(R.id.add_link_name);
        addlinkurl=(EditText)findViewById(R.id.addlinkurl);
        save=(Button) findViewById(R.id.save_btn);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!addlinkname.getText().toString().isEmpty())&& (!addlinkurl.getText().toString().isEmpty())) {
                    String linkname = addlinkname.getText().toString();
                    linkurl=addlinkurl.getText().toString();

                    newLink = new Material_DB_Helper(getApplicationContext());
                    SQLiteDatabase db1 = newLink.getWritableDatabase();
                    //new ContentValues , put in row (ContentValues)
                    ContentValues row = new ContentValues();
                    row.put(Material_Database.columns.mat_name, linkname);

                    row.put(Material_Database.columns.mat_link, linkurl);

                    ///write DB , insert DB, close DB
                    db1.insert(Material_Database.Mat_Table, null, row); ///table,null,ContentValues
                    newLink.close();

                    Toast.makeText(getApplicationContext(), "Link Added", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplication(), Year1.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Please Add Your Material's Link !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
