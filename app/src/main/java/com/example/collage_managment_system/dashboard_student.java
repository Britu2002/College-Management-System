package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;

public class dashboard_student extends AppCompatActivity {
CardView profile_student_btn,chating_student_btn,Attendance_student_btn;
TextView student_name_display;
sqlsever sql =new sqlsever();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_student);
//
        if(sql.getCon()!=null){

        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        int login_id=  pref.getInt("s_id",0);
        student_name_display=findViewById(R.id.student_name_display);
        if(sql.getCon()!=null){
            try {
                ResultSet res = sql.getProfile(login_id);
                while (res.next()){
                    student_name_display.setText(res.getString(2));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else{
            student_name_display.setText("Student");
        }

profile_student_btn=findViewById(R.id.profile_student_btn);
profile_student_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i= new Intent(dashboard_student.this,profile_student.class);

        startActivity(i);

    }
});
        chating_student_btn=findViewById(R.id.chating_student_btn);
        chating_student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(dashboard_student.this,attendance_sheet_course.class);
                startActivity(i);
            }
        });

        Attendance_student_btn=findViewById(R.id.Attendance_student_btn);
        Attendance_student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent i =new Intent(dashboard_student.this,Student_Attendance_details_show.class);
startActivity(i);
            }
        });

        }else{
            Intent i= new Intent(dashboard_student.this,oops_page.class);
            startActivity(i);
        }
    }
}