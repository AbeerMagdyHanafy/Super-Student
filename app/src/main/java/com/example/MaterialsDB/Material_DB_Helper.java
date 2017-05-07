package com.example.MaterialsDB;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by horus on 5/3/2016.
 */
public class Material_DB_Helper extends SQLiteOpenHelper {

    SQLiteDatabase materials;

    public Material_DB_Helper(Context context) {
        super(context, Material_Database.DATABASE_NAME,null,Material_Database.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqldb) {

        sqldb.execSQL("CREATE TABLE " + Material_Database.Material_Table
                + "(" + Material_Database.columns.ID + " integer primary key autoincrement,"
                + " " + Material_Database.columns.material_name + " Text not null unique," +
                Material_Database.columns.material_link + " Text,"
                + Material_Database.columns.year+" Integar );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqldb, int i, int i1) {

        sqldb.execSQL("drop table if exists " + Material_Database.Material_Table );
        onCreate(sqldb);

    }
}
