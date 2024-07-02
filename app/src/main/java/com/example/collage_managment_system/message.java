package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class message extends AppCompatActivity {
    Toolbar toolbar;
    EditText txtDate, txtMessage;
    ImageButton btnInsert;
    sqlsever sql = new sqlsever();
    // sqlsever sql =new sqlsever();
    RecyclerView recyclerView;
    ArrayList<String> coursename, subjectname,id,time;
    CardView cardView1;
    MyApdapterAtt adapter;
//   RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
//        txtDate=findViewById(R.id.txtDate);
        txtMessage = findViewById(R.id.txtmessage);

        btnInsert = findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String txtdate=txtDate.getText().toString();

                String txtmsg = txtMessage.getText().toString();
                if(txtmsg.equals("")){
                    Toast.makeText(message.this, "msg not empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
                SimpleDateFormat Timeformat = new SimpleDateFormat("HH:mm");
                String TDate = format.format(date);
                String Time = Timeformat.format(date);

                try {
                    if (sql.getCon() != null) {
//                        int i = sql.insert_message(TDate, txtmsg, Time);
                        addClass(TDate, txtmsg, Time);

//                        if (i != 0) {
//                            txtMessage.setText("");
//
//                            Toast.makeText(message.this, "Message Delivered", Toast.LENGTH_SHORT).show();

//                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            private void addClass(String tDate, String txtmsg, String Dtime) throws SQLException {
                int i=sql.insert_message(tDate,txtmsg,Dtime);
                if (i != 0) {
                            txtMessage.setText("");
//
                            Toast.makeText(message.this, "Message Delivered", Toast.LENGTH_SHORT).show();

                        }
                coursename.add(tDate);
                subjectname.add(txtmsg);
                time.add(Dtime);
//                recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount()-1);


                recyclerView.scrollToPosition(coursename.size()-1);
//                recyclerView.smoothScrollToPosition(coursename.size()-1);

                adapter.notifyDataSetChanged();
            }
        });


        coursename = new ArrayList<>();
        subjectname = new ArrayList<>();
        id = new ArrayList<>();
        time=new ArrayList<>();
//        cardView1 = findViewById(R.id.cardview);
        recyclerView = findViewById(R.id.recyclerViewM);
        adapter = new MyApdapterAtt(this, coursename, subjectname,time);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager =new LinearLayoutManager(message.this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        setToolbar();
//        LinearLayoutManager lm =new LinearLayoutManager(this);
//        lm.setSmoothScrollbarEnabled(true);
//    recyclerView.setLayoutManager(lm);
        //adapter.setOnItemClickListener(position -> gotoItemActivity(position));
        try {
            displayData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setToolbar() {
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
        if (sql.getCon() != null) {
            ResultSet res = sql.getAttendanceData();
            while (res.next()) {
                id.add(String.valueOf(res.getInt(1)));
                coursename.add(res.getString(2));
                subjectname.add(res.getString(3));
            time.add(res.getString(4));
                //Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

            }

            res.close();

        }

//        private void addClass(String tdate,String tmsg,String tTime) throws SQLException {
//            int i= sql.insert_message(tdate,);
//            classItem.add(new classItem(cid,className,subjectName));
//            classAdap.notifyDataSetChanged();
//        }


    }
}