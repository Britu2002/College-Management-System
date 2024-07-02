package com.example.collage_managment_system;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLException;
import java.util.ArrayList;

public class MyApdapterAtt extends RecyclerView.Adapter<MyApdapterAtt.MyViewHolder> {
    private Context context;
    private ArrayList <String>coursename,subjectname,time;
CardView cardView;
RecyclerView recyclerView;
sqlsever sql=new sqlsever();

    public MyApdapterAtt(Context context, ArrayList coursename, ArrayList subjectname,ArrayList time) {
        this.context = context;
        this.coursename = coursename;
        this.subjectname = subjectname;
        this.time=time;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        final String m=coursename.get(position);
    holder.subjectname.setText(String.valueOf(subjectname.get(position)));
    holder.coursename.setText(String.valueOf(coursename.get(position)));
    holder.time.setText(String.valueOf(time.get(position)));
    holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
//            try {
//                sql.deleteMessage(Integer.parseInt(m));
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }

            coursename.remove(position);
            notifyItemRemoved(position);
            return true;
        }
    });

    }

    @Override
    public int getItemCount() {

        return coursename.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView coursename,subjectname,time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            coursename=itemView.findViewById(R.id.txtAtCourse);
            subjectname=itemView.findViewById(R.id.txtAtSubject);
            cardView=itemView.findViewById(R.id.cardView);
            time=itemView.findViewById(R.id.txtAtTime);
            
        }
//        public void addData(String Cname,String Sname)
//        {
//            coursename.setText(Cname);
//            subjectname.setText(Sname);
//            notifyDataSetChanged();
//        }

    }
}
