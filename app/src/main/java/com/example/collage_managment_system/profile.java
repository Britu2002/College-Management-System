package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class profile extends AppCompatActivity {
Button logoutbtn;
LinearLayout admin_msg_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logoutbtn =findViewById(R.id.logout_btn);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref =getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor  editor= pref.edit();

                editor.putInt("s_id",0);
                editor.putString("user_type","");
                editor.apply();
                Intent i= new Intent(profile.this, login.class);
                startActivity(i);
           finishAffinity();
            }
        });

        admin_msg_btn=findViewById(R.id.admin_msg_btn);
        admin_msg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(profile.this,message.class);
                startActivity(i);

            }
        });
    }
}