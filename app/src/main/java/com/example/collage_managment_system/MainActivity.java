package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button login_btn;
EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_btn=findViewById(R.id.button);
        email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(email.getText().toString().equals("admin")&&password.getText().toString().equals("admin")){
               //explicit intent:-maually acitivity  ko explicit karte h
                 Intent intent =new Intent(MainActivity.this,dashboard.class);
                 startActivity(intent);
             }else{
                 Toast.makeText(MainActivity.this, password.getText(), Toast.LENGTH_SHORT).show();
             }
            }
        });
    }
}