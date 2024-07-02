package com.example.collage_managment_system;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.sql.SQLException;

public class MyDialog extends DialogFragment {
    public static final String CLASS_ADD_DIALOG="addClass";
    public static final String CLASS_UPDATE_DIALOG="updateClass";
    public static final String STUDENT_ADD_DIALOG="addStudent";
    public static final String STUDENT_UPDATE_DIALOG = "updateStudent";
//    public static  final String TEACHER_ADD_DIALOG="addTeacher";
sqlsever sql =new sqlsever();
    private  String name;
    private  int roll;
    private onCLickListener listener;

    public MyDialog(int roll, String name) {
        this.roll=roll;
        this.name=name;

    }

    public MyDialog() {

    }

    public interface onCLickListener{
        void onClick(String text1,String text2) throws SQLException;
    }

    public void setListener(onCLickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog=null;
        if(getTag().equals(CLASS_ADD_DIALOG))
            dialog=getAddClassDialog();
        if(getTag().equals(STUDENT_ADD_DIALOG))
            dialog=getAddStudentDialog();
        if(getTag().equals(CLASS_UPDATE_DIALOG))
            dialog=getUpdateClassDialog();
        if(getTag().equals(STUDENT_UPDATE_DIALOG))
            dialog=getUpdateStudentDialog();

        return dialog;
    }

//    private Dialog getAddTeacherDialog() {
//        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
//
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
//        view.setMinimumHeight(700);
//
//        builder.setView(view);
//        TextView title=view.findViewById(R.id.titleDialog);
//        title.setText("Add Teacher");
//        EditText class_edit=view.findViewById(R.id.edt01);
//        EditText subject_edit=view.findViewById(R.id.edt02);
//        class_edit.setEnabled(false);
//        subject_edit.setHint("Teacher");
//        Button btn_cancel=  view.findViewById(R.id.btnCancel);
//        Button btn_add=view.findViewById(R.id.btnAdd);
//
//        btn_cancel.setOnClickListener(v-> dismiss());
//        btn_add.setOnClickListener(v->
//        {
//            String className =class_edit.getText().toString();
//            String subName=subject_edit.getText().toString();
//            try {
//                listener.onClick(className,subName);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            dismiss();
//        });
//
//        return builder.create();
//    }

    private Dialog getUpdateStudentDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        view.setMinimumHeight(1700);
        builder.setView(view);
        TextView title=view.findViewById(R.id.titleDialog);
        title.setText("Update Student");

        EditText roll_edit=view.findViewById(R.id.edt01);
        EditText name_edit=view.findViewById(R.id.edt02);
        roll_edit.setHint("Roll");
        name_edit.setHint(" Name");
        Button btn_cancel=  view.findViewById(R.id.btnCancel);
        Button btn_add=view.findViewById(R.id.btnAdd);
btn_add.setText("Update");
        roll_edit.setText(roll+"");
        roll_edit.setEnabled(false);
           name_edit.setText(name);
        btn_cancel.setOnClickListener(v-> dismiss());
        btn_add.setOnClickListener(v->
        {
            String roll =roll_edit.getText().toString();
            String name=name_edit.getText().toString();
            if(name.equals("")){
                name_edit.setError("required");
                return;
            }
         //   roll_edit.setText(String.valueOf(Integer.parseInt(roll)+1));
//            name_edit.setText("");
            try {
                listener.onClick(roll,name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            dismiss();

        });

        return builder.create();
    }

    private Dialog getUpdateClassDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        view.setMinimumHeight(1700);
        builder.setView(view);
        TextView title=view.findViewById(R.id.titleDialog);
        title.setText("Update Class");
        EditText class_edit=view.findViewById(R.id.edt01);
        EditText subject_edit=view.findViewById(R.id.edt02);
        class_edit.setHint("Class Name");
        subject_edit.setHint("Subject Name");
        Button btn_cancel=  view.findViewById(R.id.btnCancel);
        Button btn_add=view.findViewById(R.id.btnAdd);
        btn_add.setText("Update");
        btn_cancel.setOnClickListener(v-> dismiss());
        btn_add.setOnClickListener(v->
        {
            String className =class_edit.getText().toString();
            String subName=subject_edit.getText().toString();
            if(className.equals("")){
                class_edit.setError("required");
                return;
            }

            if(subName.equals("")){
                subject_edit.setError("required");
                return;
            }
            try {
                listener.onClick(className,subName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            dismiss();
        });

        return builder.create();
    }

    private Dialog getAddStudentDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        view.setMinimumHeight(1700);
        builder.setView(view);
        TextView title=view.findViewById(R.id.titleDialog);
        title.setText("Add New Student");

        EditText roll_edit=view.findViewById(R.id.edt01);
        EditText name_edit=view.findViewById(R.id.edt02);
        roll_edit.setVisibility(View.GONE);
//       roll_edit.setHint("Roll");
       name_edit.setHint(" Name");
        Button btn_cancel=  view.findViewById(R.id.btnCancel);
        Button btn_add=view.findViewById(R.id.btnAdd);

        btn_cancel.setOnClickListener(v-> dismiss());
        btn_add.setOnClickListener(v->
        {
//            String roll =roll_edit.getText().toString();

            String roll ="1";
            String name=name_edit.getText().toString();
            if(name.equals("")){
                name_edit.setError("required");
                return;
            }
//         roll_edit.setText(String.valueOf(Integer.parseInt(roll)+1));
//            name_edit.setText("");
            try {
                listener.onClick(roll,name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
dismiss();
        });

        return builder.create();
    }

    @SuppressLint("MissingInflatedId")
    private Dialog getAddClassDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        view.setMinimumHeight(700);

        builder.setView(view);
        TextView title=view.findViewById(R.id.titleDialog);
        title.setText("Add New Class");
       EditText class_edit=view.findViewById(R.id.edt01);
       EditText subject_edit=view.findViewById(R.id.edt02);

       class_edit.setHint("Class Name");
       subject_edit.setHint("Subject");
        Button btn_cancel=  view.findViewById(R.id.btnCancel);
        Button btn_add=view.findViewById(R.id.btnAdd);

        btn_cancel.setOnClickListener(v-> dismiss());
        btn_add.setOnClickListener(v->
        {
            String className =class_edit.getText().toString();
            String subName=subject_edit.getText().toString();
            if(className.equals("")){
                class_edit.setError("required");
                return;
            }
            if(subName.equals("")){
                subject_edit.setError("required");

                return;
            }
            try {
                listener.onClick(className,subName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            dismiss();
        });

        return builder.create();
    }
}
