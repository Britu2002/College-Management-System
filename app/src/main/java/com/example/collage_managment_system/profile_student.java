package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;

public class profile_student extends AppCompatActivity {
Button logout_btn;
sqlsever sql=new sqlsever();
TextView profile_id,profile_name,profile_course,profile_pass,profile,profileEmail;
LinearLayout student_msg_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student);

        logout_btn =findViewById(R.id.logout_student_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref =getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor  editor= pref.edit();

                editor.putInt("s_id",0);
                editor.putString("user_type","");
                editor.apply();
                Intent i= new Intent(profile_student.this, login.class);
                startActivity(i);
               finishAffinity();
            }
        });


        student_msg_btn=findViewById(R.id.student_msg_btn);
        student_msg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(profile_student.this,attendance_sheet_course.class);
                startActivity(i);
            }
        });

        String name = null;
        int c_id=0,roll=0;
        profile_id =findViewById(R.id.Student_profile_name);
        profile_name=findViewById(R.id.Student_profile_email);
        profile_course=findViewById(R.id.Student_profile_dob);
        profile_pass=findViewById(R.id.Student_profile_pass);
        profile=findViewById(R.id.txtPSName);
        profileEmail=findViewById(R.id.txtPSEmail);
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
     int login_id=  pref.getInt("s_id",0);
     String user_type =pref.getString("user_type","");
//        Toast.makeText(this, user_type, Toast.LENGTH_SHORT).show();

        if(sql.getCon()!=null)
        {
            try {
                ResultSet res=null;
if(user_type.equals("Student")) {
    res = sql.getProfile(login_id);
}else if(user_type.equals("Teacher"))
{
    res=sql.getTeacherProfile(login_id);
}

                while (res.next())
                {

                 profile_id.setText(String.valueOf(res.getInt(1)));
                 profile_name.setText(res.getString(2));

                 profile_course.setText(res.getString(3));
                 profile_pass.setText(res.getString(4));

                 profile.setText(res.getString(2));

                 profileEmail.setText(res.getString(3));
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }

    }
}