package com.example.collage_managment_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Connection;
import java.sql.SQLException;

public class login extends AppCompatActivity {
TextView connection_id;
ConnectivityManager cm;
Boolean isconnected=false;
TextInputEditText s_id,p_id;
Button login_btn;
RadioButton radio_teacher,radio_student,radio_admin;
RadioGroup rg;
TextView reg;
    sqlsever sql =new sqlsever();
   public int st_id;
    String pt_id;
    TextInputLayout text1,text2;
    RadioButton rd;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
reg=findViewById(R.id.reg);
        connection_id =findViewById(R.id.connection_id);
        s_id=findViewById(R.id.s_id);
        p_id =findViewById(R.id.p_id);
        text1 =findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        login_btn =findViewById(R.id.Login_btn);
        rg=findViewById(R.id.rg);
        radio_teacher=findViewById(R.id.r_teacher);
        radio_student=findViewById(R.id.r_student);
        radio_admin=findViewById(R.id.r_admin);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(login.this,signup.class);
                startActivity(i);
            }
        });
 Connection con= sql.getCon();

 if(con!=null){


//     connection_id.setText("success");
    radio_teacher=findViewById(R.id.r_teacher);
    radio_student=findViewById(R.id.r_student);


     login_btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {


             if (TextUtils.isEmpty(s_id.getText().toString())) {
                 text1.setError("*");
                 return;
             } else {
                 text1.setError("");
             }

             if (TextUtils.isEmpty(p_id.getText().toString())) {
                 text2.setError("*");
                 return;
             } else {
                 text2.setError("");
             }
             try {

                 st_id = Integer.parseInt(s_id.getText().toString());
                 pt_id = p_id.getText().toString();
                 int radioId = rg.getCheckedRadioButtonId();
                 rd = findViewById(radioId);


                 if (rd.getText().toString().equals("Teacher")) {

                     if (sql.Is_teacher_Succes(st_id, pt_id)) {
                         SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                         SharedPreferences.Editor editor = pref.edit();

                         editor.putInt("s_id", st_id);
                         editor.putString("user_type", rd.getText().toString());
                         editor.apply();

                         Intent i = new Intent(login.this, dashboard_teacher.class);

                         startActivity(i);
                        finishAffinity();
                     } else {

    Toast.makeText(getApplicationContext(), "Teacher not match", Toast.LENGTH_SHORT).show();
                     }

                 } else if (rd.getText().toString().equals("Student")) {

                     if (sql.Is_login_successed(st_id, pt_id)) {
                         SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                         SharedPreferences.Editor editor = pref.edit();

                         editor.putInt("s_id", st_id);
                         editor.putString("user_type", rd.getText().toString());
                         editor.apply();

                         Intent i = new Intent(login.this, dashboard_student.class);

                         startActivity(i);
                        finishAffinity();
                     } else {
                         Toast.makeText(getApplicationContext(), "student not match", Toast.LENGTH_SHORT).show();
                     }
                 } else if (rd.getText().toString().equals("Admin")) {
                     if (st_id == (1) && pt_id.equals("a")) {
                         SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                         SharedPreferences.Editor editor = pref.edit();

                         editor.putInt("s_id", st_id);
                         editor.putString("user_type", rd.getText().toString());
                         editor.apply();

                         Intent i = new Intent(login.this, dashboard.class);

                         startActivity(i);
                        finishAffinity();
                     } else {
                         Toast.makeText(login.this, "admin not match", Toast.LENGTH_SHORT).show();
                     }
                 } else {

                 }


             } catch (Exception e) {
                 Log.e("error", e.getMessage());
             }


         }

     });

 }else{
     Toast.makeText(this, "Connection failed not connected to netwrok", Toast.LENGTH_SHORT).show();
     Intent i= new Intent(login.this,oops_page.class);
     startActivity(i);
 }
    }
    public void checkedRadio(View view)  {
        int radioId=rg.getCheckedRadioButtonId();
        rd=findViewById(radioId);
boolean checked=((RadioButton)view).isChecked();
        Toast.makeText(this, rd.getText(), Toast.LENGTH_SHORT).show();

    }




}