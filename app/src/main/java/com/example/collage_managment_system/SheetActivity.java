package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class SheetActivity extends AppCompatActivity {
//    sqlsever sql = new sqlsever();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);
setToolBar();
        try {
            showTable();
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

        title.setText("Attendance Sheet");
//        subtitle.setText(Calendar.DATE);
        subtitle.setVisibility(View.INVISIBLE);
        back.setOnClickListener(v-> onBackPressed());
    }

    private void showTable() throws SQLException {
        sqlsever sql = new sqlsever();

        if (sql.getCon() != null) {


            TableLayout tableLayout = findViewById(R.id.tableLayout);
            String[] id_array = getIntent().getStringArrayExtra("id_array");
            String[] roll_array = getIntent().getStringArrayExtra("roll_array");
            String[] name_array = getIntent().getStringArrayExtra("name_array");
            String month = getIntent().getStringExtra("month");

//
            int DAY_IN_MONTH = getDayInMonth(month);

//        //row setup
            int rowSize = id_array.length + 1;
//            Toast.makeText(this, rowSize+"", Toast.LENGTH_SHORT).show();
//
            TableRow[] rows = new TableRow[rowSize];
            TextView[] roll_tvs = new TextView[rowSize];
            TextView[] name_tvs = new TextView[rowSize];
            TextView[][] status_tvs = new TextView[rowSize][DAY_IN_MONTH + 1];
            for (int i = 0; i < rowSize; i++) {
                roll_tvs[i] = new TextView(this);
//                Toast.makeText(this, "roll_tvs"+roll_tvs[i]+i, Toast.LENGTH_SHORT).show();
                name_tvs[i] = new TextView(this);
                for (int j = 1; j <= DAY_IN_MONTH; j++) {
                    status_tvs[i][j] = new TextView(this);
//                    Toast.makeText(this, "roll_tvs"+status_tvs[i][j], Toast.LENGTH_SHORT).show();
                }
            }
            //header
//
            roll_tvs[0].setText("Roll");

            roll_tvs[0].setTypeface(roll_tvs[0].getTypeface(), Typeface.BOLD);
            name_tvs[0].setText("Name");
            name_tvs[0].setTypeface(name_tvs[0].getTypeface(), Typeface.BOLD);
            for (int i = 1; i <= DAY_IN_MONTH; i++) {
                status_tvs[0][i].setText(String.valueOf(i));
                status_tvs[0][i].setTypeface(status_tvs[0][i].getTypeface(), Typeface.BOLD);
            }
            for (int i = 1; i < rowSize; i++) {
                roll_tvs[i].setText(roll_array[i - 1]);
//                Toast.makeText(this, (CharSequence) roll_tvs[i], Toast.LENGTH_SHORT).show();
                name_tvs[i].setText(name_array[i - 1]);


                for (int j = 1; j <= DAY_IN_MONTH; j++) {
                    String day = String.valueOf(j);
                    if (day.length() == 1) day = "0" + day;
//
                    String date = day + "." + month;
                    // Toast.makeText(this, day+j, Toast.LENGTH_SHORT).show();
                    String status = sql.getStatus(Integer.parseInt(id_array[i - 1]), date);

                    status_tvs[i][j].setText(status);
//                    ResultSet res=sql.getStatus(Integer.parseInt(id_array[i - 1]), date);
//                    while (res.next()){
//                        String status= res.getString(4);
//
//                        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
////                        break;
//
//                    }

//                    String status = sql.getStatus("2:04", date);
                   // Toast.makeText(this,sql.getStatus(Integer.parseInt(id_array[i - 1]), date), Toast.LENGTH_SHORT).show();

                }
            }

            for (int i = 0; i < rowSize; i++) {
                rows[i] = new TableRow(this);
            if (i % 2 == 0) {
                rows[i].setBackgroundColor(Color.parseColor("#EEEEEE"));
            } else {
                rows[i].setBackgroundColor(Color.parseColor("#E4E4E4"));

            }
            roll_tvs[i].setPadding(16, 16, 16, 16);
            name_tvs[i].setPadding(16, 16, 16, 16);


                rows[i].addView(roll_tvs[i]);
                rows[i].addView(name_tvs[i]);

                for (int j = 1; j <= DAY_IN_MONTH; j++) {
                status_tvs[i][j].setPadding(16,16,16,16);
                    rows[i].addView(status_tvs[i][j]);
                }
                tableLayout.addView(rows[i]);
        }
                tableLayout.setShowDividers(TableLayout.SHOW_DIVIDER_MIDDLE);
            }
        }

    private int getDayInMonth(String month) {
        int monthIndex=Integer.valueOf(month.substring(0,1));
        int year =Integer.valueOf(month.substring(4));

        Calendar calendar= Calendar.getInstance();
        calendar.set(Calendar.MONTH,monthIndex);
        calendar.set(Calendar.YEAR,year);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}