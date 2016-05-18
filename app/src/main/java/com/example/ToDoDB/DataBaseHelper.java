package com.example.ToDoDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by ABeeR on 17-Mar-16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase Tasks;

    public DataBaseHelper(Context context) {
        super(context,DataBase.database_Name,null,DataBase.database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE " + DataBase.Table
              + "(" + DataBase.columns.ID + " integer primary key autoincrement,"
              + " " + DataBase.columns.Task_Name + " Text not null unique," +
              DataBase.columns.Task_Description + " Text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("drop table if exists " + DataBase.Table );
                  onCreate(db);
    }
}
