package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class dashboard_teacher extends AppCompatActivity {
//    TextView logout_btn;
    CardView Attendance_btn,Chat_btn,teacher_profile_btn;
    TextView teacher_profile_name;
    sqlsever sql =new sqlsever();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_teacher);
if(sql.getCon()!=null){



        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        int login_id=  pref.getInt("s_id",0);
        teacher_profile_name=  findViewById(R.id.teacher_profile_name);

        if(sql.getCon()!=null){
            try {
                ResultSet res = sql.getTeacherProfile(login_id);
                while (res.next()){
                    teacher_profile_name.setText(res.getString(2));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else{
           teacher_profile_name.setText("Teacher Name");
        }



        teacher_profile_btn=   findViewById(R.id.teacher_profile_btn);
        teacher_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(dashboard_teacher.this,profile_student.class);
                startActivity(i);
            }
        });

        Attendance_btn =findViewById(R.id.Attendance_teacher_btn);
        Attendance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dashboard_teacher.this,TeacherActivity.class);
                startActivity(i);
            }
        });

        Chat_btn=findViewById(R.id.CardChatTeacher);
        Chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dashboard_teacher.this,attendance_sheet_course.class);
                startActivity(i);
            }
        });


//        logout_btn =findViewById(R.id.logout_teacher_btn);
//        logout_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences pref =getSharedPreferences("login",MODE_PRIVATE);
//                SharedPreferences.Editor  editor= pref.edit();
//
//                editor.putInt("s_id",0);
//                editor.putString("user_type","");
//                editor.apply();
//                Intent i= new Intent(dashboard_teacher.this, login.class);
//                startActivity(i);
//                finish();
//            }
//        });
}else{
    Intent i= new Intent(dashboard_teacher.this,oops_page.class);
    startActivity(i);
}
    }
}