package com.example.collage_managment_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class mydatabase extends SQLiteOpenHelper {
    private static final String database_name="mydb";
    private static final String table_name="student";
    private static final String timetable="timetable";



    public mydatabase( Context context) {
        super(context, database_name, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+table_name+"( id integer primary key autoincrement ,name Text,course text)";
        db.execSQL(query);

        String timetable_query="Create table "+timetable+"(t_id integer primary key autoincrement,course Text,desp_data Text,teacher Text,day Text,start_time text,end_time)";
        db.execSQL(timetable_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
db.execSQL("drop table if exists "+table_name);
onCreate(db);
        db.execSQL("drop table if exists "+timetable);
        onCreate(db);

    }
    public boolean insert_data(String name,String course){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value =new ContentValues();
        value.put("name",name);
        value.put("course",course);
      long result =db.insert(table_name,null,value);
      if(result==-1){return false;}
      else {return true;}


    }
}
