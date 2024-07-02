package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeacherActivity extends AppCompatActivity {
private ArrayList<classItem> teacherclassItems=new ArrayList<>();
//private MyAppAdapter myAppAdapter;
private RecyclerView recycler;
private RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;
ClassAdapter classAdap;
    sqlsever sql =new sqlsever();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        if(sql.getCon()!=null){
           // Toast.makeText(this, "connection success", Toast.LENGTH_SHORT).show();
            try {
                ResultSet res=  sql.get_Course_add();
                while (res.next()){
                    int id=res.getInt(1);
                    String className=res.getString(2);
                    String subjectName=res.getString(3);
                    teacherclassItems.add(new classItem(id,className,subjectName));

                }
                res.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
        recycler=findViewById(R.id.teacher_recycler);
        recycler.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        classAdap=new ClassAdapter(this,teacherclassItems);
        recycler.setAdapter(classAdap);
        classAdap.setOnItemClickListener(position -> gotoItemActivity(position));
        setToolbar();
    }
    public void setToolbar() {
        toolbar=findViewById(R.id.toolbar);
        TextView title=toolbar.findViewById(R.id.title_toolbar);
        TextView subtitle=findViewById(R.id.subtitle_toolbar);
        ImageButton back= toolbar.findViewById(R.id.back);
        ImageButton save=toolbar.findViewById(R.id.save);

        title.setText("Course");
        subtitle.setVisibility(View.GONE);
        back.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);
    }

    private void gotoItemActivity(int position) {
        Intent intent =new Intent(this,teacher_student_activity.class);
        intent.putExtra("className",teacherclassItems.get(position).getClassName());
        intent.putExtra("subjectName",teacherclassItems.get(position).getSubjectName());
        intent.putExtra("position",position);
        intent.putExtra("cid",teacherclassItems.get(position).getCid());
        startActivity(intent);
    }

}