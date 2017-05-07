package com.example.MaterialsDB;

import android.provider.BaseColumns;

/**
 * Created by Heba on 5/3/2016.
 */
public class Material_Database {

    public static final String DATABASE_NAME = "Materials";
    public static final int DATABASE_VERSION = 1;
    public static final String Material_Table = "Mat";


    public class columns {

        public static final String ID = BaseColumns._ID;
        public static final String material_name = "Mat_Name";
        public static final String material_link = "Mat_Link";
        public static final String year = "Year";

    }
}

