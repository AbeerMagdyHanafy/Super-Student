package com.example.ToDoDB;

import android.provider.BaseColumns;

/**
 * Created by ABeeR on 17-Mar-16.
 */
public class DataBase {
    public static final String database_Name = "TODO Task";
    public static final int database_Version = 1;
    public static final String Table = "Tasks";




    public class columns {
        public static final String ID = BaseColumns._ID;
        public static final String Task_Name = "task_Name";
        public static final String Task_Description = "task_Description";



    }
}
