package com.example.MaterialsDB;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.MaterialsDB.Material_Database;

/**
 * Created by horus on 5/3/2016.
 */
public class Material_DB_Helper extends SQLiteOpenHelper {

    SQLiteDatabase materials;

    public Material_DB_Helper(Context context) {
        super(context, Material_Database.database_Name2,null,Material_Database.database_Version2);
    }
    @Override
    public void onCreate(SQLiteDatabase sqldb) {

        sqldb.execSQL("CREATE TABLE " + Material_Database.Mat_Table
                + "(" + Material_Database.columns.ID2 + " integer primary key autoincrement,"
                + " " + Material_Database.columns.mat_name + " Text not null unique," +
                Material_Database.columns.mat_link + " Text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqldb, int i, int i1) {

        sqldb.execSQL("drop table if exists " + Material_Database.Mat_Table );
        onCreate(sqldb);

    }
}
