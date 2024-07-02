package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class curd_operations extends AppCompatActivity {
EditText s_name,s_course;
Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curd_operations);
        mydatabase mydatabase = new mydatabase(this);
//     SQLiteDatabase sql= mydatabase.getReadableDatabase();

        s_name = findViewById(R.id.s_name);
        s_course = findViewById(R.id.s_course);
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = s_name.getText().toString();
                String course = s_course.getText().toString();
                if (name.equals("") || course.equals("")) {
                    Toast.makeText(curd_operations.this, "enter some values", Toast.LENGTH_SHORT).show();
                } else {
                    boolean b = mydatabase.insert_data(name, course);

                    if (b == true) {
                        Toast.makeText(curd_operations.this, "inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(curd_operations.this, "error", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });
    }
}