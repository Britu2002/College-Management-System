package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class dashboard extends AppCompatActivity {

   CardView timetable_btn,Attendance_btn,profile_btn,Attendance_sheet_btn,add_teacher_admin_btn;
sqlsever sql =new sqlsever();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        if(sql.getCon()!=null){


        profile_btn=  findViewById(R.id.profile_btn);
        add_teacher_admin_btn=findViewById(R.id.add_teacher_admin_btn);
        add_teacher_admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dashboard.this,Teacher_details_show.class);
                startActivity(i);
            }
        });

        Attendance_sheet_btn=findViewById(R.id.Attendance_sheet_btn);
        Attendance_sheet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i=new Intent(dashboard.this,attendance_sheet_course.class);
                Intent i=new Intent(dashboard.this,message.class);
                startActivity(i);
            }
        });

      Attendance_btn =findViewById(R.id.Attendance_student_btn);
      Attendance_btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i=new Intent(dashboard.this,MainActivity2.class);
              startActivity(i);
          }
      });



      profile_btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i= new Intent(dashboard.this,profile.class);
              startActivity(i);

          }
      });
        }else{
            Intent i= new Intent(dashboard.this,oops_page.class);
            startActivity(i);
        }
    }
}