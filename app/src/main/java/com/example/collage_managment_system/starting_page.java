package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;

public class starting_page extends AppCompatActivity {
sqlsever sql=new sqlsever();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_page);
if(sql.getCon()!=null){
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {

            SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
            int s_id = pref.getInt("s_id", 0);

            String user_type=pref.getString("user_type","");
            Intent i;
            if (s_id == 1 && user_type.equals("Admin")) {
                i = new Intent(starting_page.this, dashboard.class);
            } else if (s_id != 0 && user_type.equals("Student")) {
                i = new Intent(starting_page.this, dashboard_student.class);
            } else if(s_id != 0 && user_type.equals("Teacher")){
                i = new Intent(starting_page.this, dashboard_teacher.class);
            }else {
                i = new Intent(starting_page.this, login.class);
            }

            startActivity(i);
            finish();

        }
    },1000);
}else{
    Intent i= new Intent(starting_page.this,oops_page.class);
    startActivity(i);
}


    }

}