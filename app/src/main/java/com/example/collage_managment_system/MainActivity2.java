package com.example.collage_managment_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
FloatingActionButton fab;
RecyclerView recycler;
ClassAdapter classAdap;
RecyclerView.LayoutManager layoutManager;

ArrayList <classItem> classItem=new ArrayList<>();
sqlsever sql =new sqlsever();
Toolbar toolbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
if(sql.getCon()!=null){

    try {
      ResultSet res=  sql.get_Course_add();
      while (res.next()){
          int id=res.getInt(1);
          String className=res.getString(2);
          String subjectName=res.getString(3);
          classItem.add(new classItem(id,className,subjectName));

      }
//      res.close();
//        Connection con=sql.getCon();
//        if(sql.get_Course_add()){
//
//        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}else{
    Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
}
        fab=findViewById(R.id.fABtnAdd);
        fab.setOnClickListener(v->showDialog());
        recycler=findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        classAdap=new ClassAdapter(this,classItem);
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
        Intent intent =new Intent(this,StudentActivity.class);
        intent.putExtra("className",classItem.get(position).getClassName());
        intent.putExtra("subjectName",classItem.get(position).getSubjectName());
        intent.putExtra("position",position);
        intent.putExtra("cid",classItem.get(position).getCid());
        startActivity(intent);
    }

    @SuppressLint("MissingInflatedId")
    private void showDialog(){

         MyDialog dialog =new MyDialog();
         dialog.show(getSupportFragmentManager(),MyDialog.CLASS_ADD_DIALOG);
         dialog.setListener((className,subjectName)-> {
             try {
                 addClass(className,subjectName);
             } catch (SQLException e) {
                 throw new RuntimeException(e);
             }
         });

    }

    private void addClass(String className,String subjectName) throws SQLException {
        int cid= sql.set_Course_add(className,subjectName);
        classItem.add(new classItem(cid,className,subjectName));
        classAdap.notifyDataSetChanged();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case 0:
                showUpdateDialog(item.getGroupId());
                break;
            case 1:
                try {
                    deleteClass(item.getGroupId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
        return super.onContextItemSelected(item);
    }

    private void showUpdateDialog(int position) {
        MyDialog dialog=new MyDialog();
        dialog.show(getSupportFragmentManager(),MyDialog.CLASS_UPDATE_DIALOG);
        dialog.setListener((className,subjectName)-> {
            try {
                updateClass(position,className,subjectName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void updateClass(int position, String className, String subjectName) throws SQLException {
        sql.updateClass(classItem.get(position).getCid(),className,subjectName);
        classItem.get(position).setClassName(className);
        classItem.get(position).setSubjectName(subjectName);
        classAdap.notifyItemChanged(position);
    }

    private void deleteClass(int position) throws SQLException {
        sql.deleteClass(classItem.get(position).getCid());
        classItem.remove(position);
        classAdap.notifyItemRemoved(position);

    }

}