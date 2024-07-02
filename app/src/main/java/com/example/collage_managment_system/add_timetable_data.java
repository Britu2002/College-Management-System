package com.example.collage_managment_system;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class add_timetable_data extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    TextView start,end;
    int uhours,umin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timetable_data);
        Spinner spinner=findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.course, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner2=findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1 =ArrayAdapter.createFromResource(this,R.array.day, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);

        start=findViewById(R.id.start_time);
        end=findViewById(R.id.end_time);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar =Calendar.getInstance();
          int    c_hours    =calendar.get(Calendar.HOUR_OF_DAY);
                int  c_min   =calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog =new TimePickerDialog(add_timetable_data.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hours, int min) {
                        Calendar c =Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hours);
                        c.set(Calendar.MINUTE,min);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format =new SimpleDateFormat("hh:mm:a");
                        String time =format.format(c.getTime());
                        start.setText(time);


                    }
                },c_hours,c_min,false);

              timePickerDialog.show();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar =Calendar.getInstance();
                int    c_hours    =calendar.get(Calendar.HOUR_OF_DAY);
                int  c_min   =calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog =new TimePickerDialog(add_timetable_data.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hours, int min) {
                                Calendar c =Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY,hours);
                                c.set(Calendar.MINUTE,min);
                                c.setTimeZone(TimeZone.getDefault());
                                SimpleDateFormat format =new SimpleDateFormat("hh:mm:a");
                                String time =format.format(c.getTime());
                                end.setText(time);


                            }
                        },c_hours,c_min,false);

                timePickerDialog.show();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text= adapterView.getItemAtPosition(i).toString();
if(i!=0){
    Toast.makeText(this,text, Toast.LENGTH_SHORT).show();
}

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}