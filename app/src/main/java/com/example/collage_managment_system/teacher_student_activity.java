package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class teacher_student_activity <ResultSet>extends AppCompatActivity {
    Toolbar toolbar;
    private String className, subjectName;
    private int position;
    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<StudentItem> teacherstudentItems = new ArrayList<>();
    public int cid;
    sqlsever sql = new sqlsever();
  private  MyCalendar calendar ;
   private TextView subtitle ;
   int sid,roll;
   String name;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_student);
        calendar =new MyCalendar();
        Intent intent = getIntent();
        className = intent.getStringExtra("className");
        subjectName = intent.getStringExtra("subjectName");
        position = intent.getIntExtra("position", -1);
        cid = intent.getIntExtra("cid", -1);

        setToolbar();
        try {
            loadStatusData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        recyclerView = findViewById(R.id.t_student_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new StudentAdapter(this, teacherstudentItems);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(position->changeStatus(position));
        if (sql.getCon() != null) {
            //Toast.makeText(this, "connection success", Toast.LENGTH_SHORT).show();
            try {
                java.sql.ResultSet res = sql.getStudentTable(cid);
                teacherstudentItems.clear();
                // Toast.makeText(this,cid,Toast.LENGTH_SHORT).show();
                while (res.next()) {
//                    Toast.makeText(this,res.getInt("C_ID"),Toast.LENGTH_SHORT).show();
//                     sid = res.getInt(1);
//                     roll = res.getInt(8);
//                    name = res.getString(2);
                    sid=res.getInt(1);
                   name=res.getString(2);
                     roll=res.getInt(3);
                    teacherstudentItems.add(new StudentItem(sid, roll, name));


                }
                res.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }


    }
    private void changeStatus(int position) {
        String status=teacherstudentItems.get(position).getStatus();
        if(status.equals("P")) status ="A";
        else status ="P";


        teacherstudentItems.get(position).setStatus(status);
        adapter.notifyItemChanged(position);
    }


    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        TextView title = toolbar.findViewById(R.id.title_toolbar);
        subtitle = findViewById(R.id.subtitle_toolbar);
        ImageButton back = toolbar.findViewById(R.id.back);
        ImageButton save = toolbar.findViewById(R.id.save);
save.setOnClickListener(v-> {
    try {
        saveStatus();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
});
        title.setText(className);
        subtitle.setText( subjectName+" || "+ calendar.getDate());
        back.setOnClickListener(v -> onBackPressed());
       // toolbar.inflateMenu(R.menu.teacher_menu);
       // toolbar.setOnMenuItemClickListener(menuItem-> onMenuItemClick(menuItem));
    }

    @SuppressLint("WrongConstant")
    private void saveStatus() throws SQLException {
         //final double Short_delay=0.0001;
//        Toast to = new Toast(this);
        for(StudentItem studentItem :teacherstudentItems ){
            String status=studentItem.getStatus();
            if(status!="P")status="A";
            int value= sql.addStatus(studentItem.getSid(),cid,calendar.getDate(),status);

            if(value==-1)
                sql.updateStatus(studentItem.getSid(), calendar.getDate(), status);

          Toast.makeText(this, "Record Saved",Toast.LENGTH_SHORT).show();
//            to.cancel();

             //  to.setDuration(1000);

            Intent i=new Intent(this,TeacherActivity.class);
            startActivity(i);
            finish();



        }
    }
    private void loadStatusData() throws SQLException {
        for(StudentItem studentItem :teacherstudentItems){
            String status=sql.getStatus(studentItem.getSid(), calendar.getDate());
//            String status="";
//             java.sql.ResultSet res= sql.getStatus(studentItem.getSid(), calendar.getDate());
//            while (res.next()){
//                status= res.getString(4);
//
//            }
////            String status=sql.getStatus("2:04", calendar.getDate());
            if(status!=null){
                studentItem.setStatus(status);
            }
            adapter.notifyDataSetChanged();
        }
    }

//    private boolean onMenuItemClick(MenuItem menuItem) {
//        if(menuItem.getItemId()==R.id.show_Calendar)
//        {
//            showCalendar();
//        }
//        return true;
//    }

//    private  void showCalendar() {
//
//        calendar.show(getSupportFragmentManager(),"");
//        calendar.setOnCalendarOkClickListener(this:: onCalendarOkClicked);
//    }

//    private void onCalendarOkClicked(int year, int month, int day) {
//        calendar.setDate(year,month,day);
//        subtitle.setText(subjectName+" || "+calendar.getDate());
//    }

}