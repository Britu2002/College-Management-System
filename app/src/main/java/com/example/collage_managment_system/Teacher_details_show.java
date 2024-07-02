package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teacher_details_show extends AppCompatActivity {
GridView gridViewTeacher;
FloatingActionButton teacherfabAdd;
SimpleAdapter adapter;
sqlsever sql =new sqlsever();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details_show);

        teacherfabAdd=findViewById(R.id.teacherfabAdd);
        teacherfabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Teacher_details_show.this,Add_teacher.class);
                startActivity(i);
            }
        });

        gridViewTeacher=findViewById(R.id.gridViewTeacher);
        List<Map<String,String>> data=new ArrayList<Map<String,String>>();

        if(sql.getCon()!=null)
        {
            try {
//
              ResultSet res = sql.getTeacherDetailsData();
                while(res.next())
                {
                    Map<String,String> tab=new HashMap<String,String>();
                    tab.put("TeacherID", String.valueOf(res.getInt(1)));
                    tab.put("TeacherName", res.getString(2).toString());
                    tab.put("TeacherEmail", res.getString(3).toString());
                    data.add(tab);
//                    String s= res.getString(2);
//                    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                }
                String[] from={"TeacherID","TeacherName","TeacherEmail"};
                int[] to={R.id.Teacher_id,R.id.Teacher_name,R.id.Teacher_email};

                adapter=new SimpleAdapter(Teacher_details_show.this,data,R.layout.gridviewlayout,from,to);
                gridViewTeacher.setAdapter(adapter);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }
}