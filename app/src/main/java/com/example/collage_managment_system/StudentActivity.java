package com.example.collage_managment_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class StudentActivity<Resultset> extends AppCompatActivity {
Toolbar toolbar;
private String className,subjectName;
private int position;
private RecyclerView recyclerView;
private StudentAdapter adapter;
private RecyclerView.LayoutManager layoutManager;
private ArrayList<StudentItem> studentItems =new ArrayList<>();
public int cid;
    sqlsever sql =new sqlsever();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Intent intent =getIntent();
        className =intent.getStringExtra("className");
        subjectName= intent.getStringExtra("subjectName");
        position =intent.getIntExtra("position",-1);
        cid=intent.getIntExtra("cid",-1);
//        int s_id=intent.getIntExtra("sid",-1);
//        Toast.makeText(this, s_id+"", Toast.LENGTH_SHORT).show();
        setToolbar();

        recyclerView=findViewById(R.id.student_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter =new StudentAdapter(this,studentItems);
        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(position->changeStatus(position));
        if(sql.getCon()!=null){
          //  Toast.makeText(this, "connection success", Toast.LENGTH_SHORT).show();
            try {
                ResultSet res=  sql.getStudentTable(cid);
                studentItems.clear();
               // Toast.makeText(this,cid,Toast.LENGTH_SHORT).show();
                while (res.next()){
//                    Toast.makeText(this,res.getInt("C_ID"),Toast.LENGTH_SHORT).show();
                    int sid=res.getInt(1);
                    String name=res.getString(2);
                    int roll=res.getInt(3);

                    studentItems.add(new StudentItem(sid,roll,name));


                }

                res.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }

    }




    private void changeStatus(int position) {
//        String status=studentItems.get(position).getStatus();
//        if(status.equals("P")) status ="A";
//        else status ="P";
//
//
//        studentItems.get(position).setStatus(status);
//        adapter.notifyDataSetChanged();
        Toast.makeText(this,"Long Pressed for Update and Delete the Data",Toast.LENGTH_SHORT).show();
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.toolbar);
        TextView title=toolbar.findViewById(R.id.title_toolbar);
        TextView subtitle=findViewById(R.id.subtitle_toolbar);
        ImageButton back= toolbar.findViewById(R.id.back);
        ImageButton save=toolbar.findViewById(R.id.save);

        title.setText(className);
        subtitle.setText(subjectName);
        back.setOnClickListener(v-> onBackPressed());
    toolbar.inflateMenu(R.menu.student_menu);
    toolbar.setOnMenuItemClickListener(menuItem-> onMenuItemClick(menuItem));
    }

    private boolean onMenuItemClick(MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.add_student)
        {
            showAddStudentDialog();
        }
//        else if(menuItem.getItemId()==R.id.show_attendance_sheet)
//
//        {
////            Toast.makeText(this, "ttttt", Toast.LENGTH_SHORT).show();
//            openSheetList();
//        }
        else if(menuItem.getItemId()==R.id.add_teacher)
        {
            showTeacherAddDialog();
        }
        return true;
    }

    private void showTeacherAddDialog() {
        Intent i= new Intent(this,Add_teacher.class);
        startActivity(i);
    }

//    private void openSheetList() {
//        String[] id_array=new String[studentItems.size()];
//        String[] roll_array =new String[studentItems.size()];
//        String [] name_array=new String[studentItems.size()];
//
//        for(int i=0;i<id_array.length;i++)
//        {
//            id_array[i]= String.valueOf(studentItems.get(i).getSid());
//        }
//        for(int i=0;i<roll_array.length;i++)
//        {
//            roll_array[i]= String.valueOf(studentItems.get(i).getRoll());
//        }
//
//        for(int i=0;i<name_array.length;i++)
//        {
//            name_array[i]=studentItems.get(i).getName();
//        }
//        Intent i=new Intent(this,SheetListActivity.class);
//        i.putExtra("cid",cid);
//        i.putExtra("id_array",id_array);
//        i.putExtra("roll_array",roll_array);
//        i.putExtra("name_array",name_array);
//        startActivity(i);
//    }

    private void showAddStudentDialog() {
        MyDialog dialog =new MyDialog();
        dialog.show(getSupportFragmentManager(),MyDialog.STUDENT_ADD_DIALOG);
        dialog.setListener((sid,name)-> {
            try {
                addStudent(sid,name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });
    }

    private void addStudent(String roll_string, String name) throws SQLException {

        int roll=Integer.parseInt(roll_string);
       int sid= sql.addStudent(cid,roll,name);
//        adapter.notifyDataSetChanged();
        int s;
        if(sql.getCon()!=null){
           s=sql.getlastStudentId();

        }else{
            s=0;
        }
       StudentItem studentItem=new StudentItem(s,roll,name);
        studentItems.add(studentItem);
//        adapter.notifyItemChanged(studentItems.size()-1);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case 0:
                showUpdateStudentDialog(item.getGroupId());
                break;
            case 1:
                try {
                    deleteStudent(item.getGroupId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
        return super.onContextItemSelected(item);
    }

    private void showUpdateStudentDialog(int position) {
        MyDialog dialog=new MyDialog(studentItems.get(position).getSid(),studentItems.get(position).getName());
        dialog.show(getSupportFragmentManager(),MyDialog.STUDENT_UPDATE_DIALOG);
        dialog.setListener((sid,name)-> {
            updateStudent(position,name);
        });
    }

    private void updateStudent(int position, String name) throws SQLException {

        sql.updateStudent(studentItems.get(position).getSid(),name);
        studentItems.get(position).setName(name);
        adapter.notifyItemChanged(position);
    }

    private void deleteStudent(int position) throws SQLException {
        sql.deleteStudent(studentItems.get(position).getSid());
        studentItems.remove(position);
        adapter.notifyItemRemoved(position);
    }
}