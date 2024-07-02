package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class attendance_sheet_course extends AppCompatActivity {
sqlsever sql =new sqlsever();
    RecyclerView recyclerView;
    ArrayList <String> coursename,subjectname,id,time;
    CardView cardView;
    MyApdapterAtt adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_sheet_course);
        setToolBar();
    coursename=new ArrayList<>();
    subjectname=new ArrayList<>();
       id=new ArrayList<>();
       time=new ArrayList<>();
    cardView=findViewById(R.id.cardview);
    recyclerView =findViewById(R.id.recyclerView1);
    adapter=new MyApdapterAtt(this,coursename,subjectname,time);
    recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager =new LinearLayoutManager(attendance_sheet_course.this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.scrollToPosition(coursename.size()-1);
//                recyclerView.smoothScrollToPosition(coursename.size()-1);

//        adapter.notifyDataSetChanged();
//    recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adapter.setOnItemClickListener(position -> gotoItemActivity(position));
        try {
            displayData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setToolBar() {
        toolbar=findViewById(R.id.toolbar);
        TextView title=toolbar.findViewById(R.id.title_toolbar);
        TextView subtitle=findViewById(R.id.subtitle_toolbar);
        ImageButton back= toolbar.findViewById(R.id.back);
//        ImageButton save=toolbar.findViewById(R.id.save);

        title.setText("Message");
//        subtitle.setText(Calendar.DATE);
        subtitle.setVisibility(View.INVISIBLE);
        back.setOnClickListener(v-> onBackPressed());
    }


    private void displayData() throws SQLException {
        if(sql.getCon()!=null) {
            ResultSet res = sql.getAttendanceData();
            while (res.next()) {
                coursename.add(res.getString(2));
                subjectname.add(res.getString(3));
                time.add(res.getString(4));
            }
            res.close();
        }

    }
}