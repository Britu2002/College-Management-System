package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class signup extends AppCompatActivity {
Button s_signupbtn,s_loginbtn;
TextInputEditText sign_text_id,sign_text_pass,sign_text_re_pass;
TextInputLayout sign_text_id_layout,sign_text_pass_layout,sign_text_re_pass_layout;
    int    signup_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sqlsever sql =new sqlsever();
      s_signupbtn=  findViewById(R.id.s_signup_btn);
   s_loginbtn=  findViewById(R.id.s_login_btn);
    sign_text_id=findViewById(R.id.sign_text_id);
        sign_text_pass=findViewById(R.id.sign_text_pass);
        sign_text_re_pass=findViewById(R.id.sign_text_re_pass);
        sign_text_id_layout=findViewById(R.id.sign_text_id_layout);
        sign_text_pass_layout=findViewById(R.id.sign_text_pass_layout);
        sign_text_re_pass_layout=findViewById(R.id.sign_text_re_pass_layout);
        s_loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(signup.this,login.class);
                startActivity(i);
            }
        });
    s_signupbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (TextUtils.isEmpty(sign_text_id.getText().toString())) {sign_text_id_layout.setError("*");return;} else {sign_text_id_layout.setError("");}
            if (TextUtils.isEmpty(sign_text_pass.getText().toString())) {sign_text_pass_layout.setError("*");return;} else {sign_text_pass_layout.setError("");}
            if (TextUtils.isEmpty(sign_text_re_pass.getText().toString())) {sign_text_re_pass_layout.setError("*");return;} else {sign_text_re_pass_layout.setError("");}
if(sql.getCon()!=null) {

    try {

        signup_id = Integer.parseInt(sign_text_id.getText().toString());
        String signup_pass = sign_text_pass.getText().toString();
        String signup_re_pass = sign_text_re_pass.getText().toString();

        if (signup_pass.equals(signup_re_pass)==false) {
            Toast.makeText(signup.this, "password and re-password not match", Toast.LENGTH_SHORT).show();
            return;
        }


        if(sql.already_reg(signup_id)){
            Toast.makeText(signup.this, "already register", Toast.LENGTH_SHORT).show();
            return;
        }
        if(sql.IS_student_scholor_id_check(signup_id)){

      } else if(sql.IS_teacher_scholor_id_check(signup_id)){


        }else{
            Toast.makeText(signup.this, "id is not vaild", Toast.LENGTH_SHORT).show();
            return;
        }
       int result= sql.insert_reg(signup_id,signup_pass);
        if(result!=0){
            Toast.makeText(signup.this, "register successfully", Toast.LENGTH_SHORT).show();
            Intent i =new Intent(signup.this,login.class);
            startActivity(i);
           finishAffinity();
        }else{
            Toast.makeText(signup.this, "there are some error", Toast.LENGTH_SHORT).show();
        }

    } catch (Exception e) {
        Toast.makeText(signup.this, "enter only integer value in scholor id", Toast.LENGTH_SHORT).show();
    }

//
//
}else{
   Toast.makeText(signup.this, "CHECK THE INTRENT CONNECTION", Toast.LENGTH_SHORT).show();
}
        }});


    }

}