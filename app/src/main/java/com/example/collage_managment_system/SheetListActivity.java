package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SheetListActivity extends AppCompatActivity {
private ListView sheetList;
Toolbar toolbar;
private ArrayAdapter adapter;
private ArrayList <String>listItems =new ArrayList();
private int cid;
sqlsever sql=new sqlsever();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_list);
    cid=getIntent().getIntExtra("cid",-1);
setToolBar();
        try {
            loadListItems();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sheetList=findViewById(R.id.sheetList);
        adapter=new ArrayAdapter(this,R.layout.sheet_list,R.id.date_list_item,listItems);
        sheetList.setAdapter(adapter);
        sheetList.setOnItemClickListener((parent,view,position,id)->openSheetActivity(position));
//        sheetList.setOnItemClickListener();
    }

    private void setToolBar() {
        toolbar=findViewById(R.id.toolbar);
        TextView title=toolbar.findViewById(R.id.title_toolbar);
        TextView subtitle=findViewById(R.id.subtitle_toolbar);
        ImageButton back= toolbar.findViewById(R.id.back);
//        ImageButton save=toolbar.findViewById(R.id.save);

        title.setText("Date of Attendance");
//        subtitle.setText(Calendar.DATE);
        subtitle.setVisibility(View.INVISIBLE);
        back.setOnClickListener(v-> onBackPressed());
    }

    private void openSheetActivity(int position) {
//       ("cid",cid);
        String[] id_array=getIntent().getStringArrayExtra("id_array");


        String[] roll_array=getIntent().getStringArrayExtra("roll_array");
        String[] name_array=getIntent().getStringArrayExtra("name_array");
        Intent i=new Intent(this,SheetActivity.class);
        i.putExtra("id_array",id_array);
        i.putExtra("roll_array",roll_array);
        i.putExtra("name_array",name_array);

        i.putExtra("month",listItems.get(position));
        startActivity(i);
//        Toast.makeText(this, roll_array[0], Toast.LENGTH_SHORT).show();
    }

    private void loadListItems() throws SQLException {
//
        if(sql.getCon()!=null){
            // Toast.makeText(this, "connection success", Toast.LENGTH_SHORT).show();
            try {
                ResultSet res=sql.getDistinctMonths(cid);
      while(res.next())
     {
          String date=res.getString("Date");
          listItems.add(date.substring(3));
       }
      res.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
    }
}