package com.example.MaterialsDB;

import android.provider.BaseColumns;

/**
 * Created by Heba on 5/3/2016.
 */
public class Material_Database {

        public static final String database_Name2 = "Materials";
        public static final int database_Version2 = 1;
        public static final String Mat_Table = "Mat";


        public class columns {

            public static final String ID2 = BaseColumns._ID;
            public static final String mat_name = "Mat_Name";
            public static final String mat_link = "Mat_Link";

        }
}

