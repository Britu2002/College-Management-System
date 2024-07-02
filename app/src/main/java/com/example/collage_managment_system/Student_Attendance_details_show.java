package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student_Attendance_details_show extends AppCompatActivity {
GridView gridViewStatus;
sqlsever sql =new sqlsever();
SimpleAdapter adap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance_details_show);

        gridViewStatus=findViewById(R.id.gridViewStatus);
        List<Map<String,String>> dat=new ArrayList<Map<String,String>>();

        if(sql.getCon()!=null)
        {
            try {
 SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
     int login_id=  pref.getInt("s_id",0);
                ResultSet res = sql.getStudent_data_details_show(login_id);

                while(res.next())
                {
                    Map<String,String> ta=new HashMap<String,String>();
                    ta.put("StatusDate", res.getString(1).toString());
                    ta.put("Status_CName", res.getString(2).toString());
                    ta.put("Status_SName", res.getString(3).toString());
                    ta.put("Status_Status", res.getString(4).toString());
                    dat.add(ta);
//                    String s= res.getString(2);
//                    Toast.makeText(this, tab.toString(), Toast.LENGTH_SHORT).show();
                }
                String[] fro={"StatusDate","Status_CName","Status_SName","Status_Status"};

                int[] tot={R.id.status_date,R.id.status_course_name,R.id.status_subject_name,R.id.status_status};

                adap=new SimpleAdapter(Student_Attendance_details_show.this,dat,R.layout.grid_student_view,fro,tot);
                gridViewStatus.setAdapter(adap);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
}