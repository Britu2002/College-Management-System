package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.sql.SQLException;

public class Add_teacher extends AppCompatActivity {
EditText editText,edtEmailTeacher;
ImageButton btnTeacher,T_CLOSE_BTN;
sqlsever sql =new sqlsever();
int cid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
        editText=findViewById(R.id.edtTeacher);
        edtEmailTeacher=findViewById(R.id.edtEmailTeacher);
        btnTeacher=findViewById(R.id.btn_Teacher_add);
//        Intent intent=getIntent();
//        cid=intent.getIntExtra("cid",-1);
        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(sql.getCon()!=null);
                {
                    String edtTName=editText.getText().toString();
               String edtETeacher=     edtEmailTeacher.getText().toString();
if(edtTName.equals("")){
    editText.setError("required");
    return;
}
                    if(edtETeacher.equals("")){
                        edtEmailTeacher.setError("required");
                    return;
                }
if(emailValidator(edtETeacher)==false){
    edtEmailTeacher.setError("end with @gmail.com");
    return;
}
                    try {
                        if(sql.check_email(edtETeacher)){
                            edtEmailTeacher.setError("email already exit");

                            return;
                        }
                        sql.addTeacher(edtTName,edtETeacher);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(Add_teacher.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }
                Intent i=new Intent(Add_teacher.this,dashboard.class);
                startActivity(i);
                finishAffinity();
            }
        });

        T_CLOSE_BTN=findViewById(R.id.T_CLOSE_BTN);
        T_CLOSE_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Add_teacher.this,dashboard.class);
                startActivity(i);
                finishAffinity();
            }
        });
    }
    public Boolean emailValidator(String emailToText) {
boolean b;
   if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
           b=true;
        } else {
            b=false;
        }
        return b;
    }
}