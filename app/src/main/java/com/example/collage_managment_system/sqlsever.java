package com.example.collage_managment_system;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

//import androidx.appcompat.app.AppCompatActivity;
//
//import com.airbnb.lottie.animation.content.Content;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.transform.Result;

public class sqlsever  {

    //  192.168.3.33     ghr--192.168.29.42
    public  static  String ip ="192.168.29.42";
    public  static  String port ="1433";
    public  static  String database ="demo_db";
    Connection con=null;

    public Connection getCon() {
        StrictMode.ThreadPolicy policy =new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
  try {
   Class.forName("net.sourceforge.jtds.jdbc.Driver");
   con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database,"demo","demo");
 } catch (ClassNotFoundException |SQLException ex) {
            Log.e("error",ex.getMessage());
        }

        return con;
    }

    public Boolean Is_login_successed(int student_id,String password)  throws SQLException {
        Boolean b=false;

//     String query="select student_data.*,login.password from student_data  left join login  on login.student_id=student_data.id \n" +
//             "where student_data.id=? And login.password=?";
//        String query="select student_data.*,login.password from student_data, login where student_data.id=? And login.password=?";
String query ="select student_data.id,login.password from login \n" +
        "\t\t\tleft join student_data\n" +
        "\t\t\ton student_data.id=login.student_id where student_data.id=? And login.password=?";
       PreparedStatement pre= con.prepareStatement(query);
            pre.setInt(1,student_id);
            pre.setString(2,password);
            ResultSet res= pre.executeQuery();
            if(res.next()){
                b=true;

            }else{
                 b=false;}
            pre.close();



        return b;
    }

public int set_Course_add(String class_name,String subject_name) throws SQLException {
      PreparedStatement pre=  con.prepareStatement("insert into course(course_name,subject_name) values(?,?);");
      pre.setString(1,class_name);
      pre.setString(2,subject_name);
   int res=   pre.executeUpdate();

   pre.close();
      return  res;

}
    public ResultSet get_Course_add() throws SQLException {
        PreparedStatement pre=  con.prepareStatement("select * from course");
   ResultSet res=   pre.executeQuery();

     return  res;

    }
//public Boolean get_Course_add() throws SQLException {
//    PreparedStatement pre=  con.prepareStatement("select * from course");
//    Boolean b=pre.execute();
//
//
//
//  return b;
//
//}

public int deleteClass(int cid) throws SQLException {
    PreparedStatement pre=  con.prepareStatement("delete from course where C_ID=?;");
    pre.setInt(1,cid);
    int res = pre.executeUpdate();

pre.close();
    return res;
}
    public int updateClass(int cid,String class_name,String subject_name) throws SQLException {
        PreparedStatement pre=  con.prepareStatement("update course set course_name=?, subject_name=? where C_ID=?;");
        pre.setString(1,class_name);
        pre.setString(2,subject_name);
        pre.setInt(3,cid);
        int res=   pre.executeUpdate();

        pre.close();
        return  res;
    }
    public int addStudent(int cid,int roll,String name) throws SQLException {
        PreparedStatement pre=  con.prepareStatement("insert into student_data(roll,name,C_ID) values(?,?,?);");
      pre.setInt(1,roll);
        pre.setString(2,name);
        pre.setInt(3,cid);
        int res=   pre.executeUpdate();
        pre.close();
        return  res;
    }
    public ResultSet getStudentTable(int cid) throws SQLException {
        PreparedStatement pre=  con.prepareStatement("select id,name,roll from student_data where C_ID=?;");
        pre.setInt(1,cid);
        ResultSet res=   pre.executeQuery();

        return  res;
    }
    public int deleteStudent(int sid) throws SQLException {
        PreparedStatement pre=  con.prepareStatement("delete from student_data where id=?;");
        pre.setInt(1,sid);
        int res = pre.executeUpdate();
pre.close();
        return res;
    }
    public int updateStudent(int sid,String name) throws SQLException {
        PreparedStatement pre=  con.prepareStatement("update student_data set name=? where id=?;");
        pre.setString(1,name);
        pre.setInt(2,sid);
//        pre.setInt(3,cid);
        int res=   pre.executeUpdate();

pre.close();
        return  res;
    }

    public ResultSet get_student_data_id() throws SQLException {
    Statement statement= con.createStatement();
       ResultSet res =  statement.executeQuery("select id from student_data;");


        return  res;
    }

    public  Boolean Is_teacher_Succes(int t_id,String t_password) throws SQLException {
        Boolean b=false;
//        String query="select * from login WHERE student_id =? and password=?";
        String query="select teacher.t_id,login.password from login \n" +
                "\t\t\tleft join teacher\n" +
                "\t\t\ton teacher.t_id=login.student_id where teacher.t_id=? And login.password=?";
        PreparedStatement pre= con.prepareStatement(query);
        pre.setInt(1,t_id);
        pre.setString(2,t_password);
        ResultSet res= pre.executeQuery();
        if(res.next()){
            b=true;

        }else{
            b=false;}
        res.close();
        pre.close();



        return b;
    }
    public int addStatus(int sid,int cid,String date,String status) throws SQLException {
        PreparedStatement pre=  con.prepareStatement("insert into status(s_id,Date,status,C_ID) values(?,?,?,?) ;");
        pre.setInt(1,sid);
        pre.setString(2,date);
        pre.setString(3,status);
        pre.setInt(4,cid);
        int res=   pre.executeUpdate();

        pre.close();
        return  res;
    }
    public int updateStatus(int sid,String date,String status) throws SQLException {
        PreparedStatement pre=  con.prepareStatement("update status set status=? where s_id=? AND Date=?;");
        pre.setString(1,status);
        pre.setInt(2,sid);
        pre.setString(3,date);
        int res=   pre.executeUpdate();

        pre.close();
        return  res;
    }
    public String getStatus(int sid,String date) throws SQLException {
//public String getStatus(String sid,String date) throws SQLException {
        String status=null;
        PreparedStatement pre=  con.prepareStatement("select * from status where C_ID=44 AND Date=? ;");
//        PreparedStatement pre=  con.prepareStatement("select * from status where time=? AND Date=? ;");
//        pre.setInt(1,sid);
        pre.setString(1,date);
        ResultSet res=   pre.executeQuery();
        if(res.next())
        {
            status=res.getString(4);
        }

        return status;
    }
    public ResultSet getDistinctMonths(int cid) throws SQLException {
        PreparedStatement pre=  con.prepareStatement("select Distinct[time],Date from status where C_ID=?;");
        pre.setInt(1,cid);
        ResultSet res=   pre.executeQuery();

        return  res;
    }

    public Boolean IS_student_scholor_id_check(int scholor_id) throws SQLException {
        Boolean b=false;
        PreparedStatement pre=  con.prepareStatement("select * from student_data where id=?");
        pre.setInt(1,scholor_id);
        ResultSet res=pre.executeQuery();
        if(res.next()){
            b=true;
        }
        res.close();
        pre.close();
        return  b;
    }
    public Boolean IS_teacher_scholor_id_check(int scholor_id) throws SQLException {
        Boolean b=false;
        PreparedStatement pre=  con.prepareStatement("select * from teacher where t_id=?");
        pre.setInt(1,scholor_id);
        ResultSet res=pre.executeQuery();
        if(res.next()){
            b=true;
        }
        res.close();
        pre.close();
        return  b;
    }

  public Boolean already_reg(int scholor_id) throws SQLException {
       boolean b= false;
      PreparedStatement pre=  con.prepareStatement("select * from login where student_id=?");
      pre.setInt(1,scholor_id);
      ResultSet res=pre.executeQuery();
      if(res.next()){
         b=true;
      }
      res.close();
      pre.close();
        return  b;
  }
public  int insert_reg(int scholor_id,String pass) throws SQLException {
    PreparedStatement pre=  con.prepareStatement("insert into login values (?,?)");
    pre.setInt(1,scholor_id);
    pre.setString(2,pass);
    int res=pre.executeUpdate();

    pre.close();
    return  res;

}
public int insert_message(String date,String message,String time) throws SQLException {
    PreparedStatement pre=con.prepareStatement("insert into message values(?,?,?)");
    pre.setString(1,date);
    pre.setString(2,message);
    pre.setString(3,time);
    int res=pre.executeUpdate();
    pre.close();
    return res;
}
//public ResultSet profile_data(int scholor_id,String pass) throws SQLException {
//    PreparedStatement pre=  con.prepareStatement("select student_data.*,lt.pass from student_data,lt where lt.id=? And lt.pass=?");
//    pre.setInt(1,scholor_id);
//    pre.setString(2,pass);
//    ResultSet res=pre.executeQuery();
//        return  res;
//}
    public ResultSet getAttendanceData() throws SQLException {
        Statement ste=con.createStatement();
        ResultSet res = ste.executeQuery("select * from message");

        return res;
    }
    public int deleteMessage(int position) throws SQLException {
        PreparedStatement pre=  con.prepareStatement("delete from message");
        pre.setInt(1,position);
        int res = pre.executeUpdate();
        pre.close();
        return res;
    }
    public int addTeacher(String name,String email) throws SQLException {
        PreparedStatement pre =con.prepareStatement("insert into teacher values(?,?)");
        pre.setString(1,name);
        pre.setString(2,email);
       int res =pre.executeUpdate();
       pre.close();
       return res;
    }
    public ResultSet getProfile(int id) throws SQLException {

        PreparedStatement pre =con.prepareStatement("SELECT student_data.id,student_data.name,course.course_name,login.password\n" +
                "FROM student_data\n" +
                "LEFT JOIN course\n" +
                "ON student_data.C_ID = course.C_ID\n" +
                "LEFT JOIN login\n" +
                "on student_data.id=login.student_id where student_data.id=?");
//        PreparedStatement pre =con.prepareStatement("select * from student_data where id=?");
        pre.setInt(1,id);
ResultSet res=pre.executeQuery();
        return res;
    }
    public ResultSet getTeacherProfile(int id) throws SQLException {
        String query=" SELECT teacher.t_id,teacher.t_name,teacher.t_email,login.password\n" +
                "                FROM teacher\n" +
                "                LEFT JOIN login\n" +
                "                ON teacher.t_id= login.student_id where student_id=?";
        PreparedStatement pre =con.prepareStatement(query);
        pre.setInt(1,id);
        ResultSet res=pre.executeQuery();
        return res;
    }
    public Boolean check_email(String email) throws SQLException {
        Boolean b=false;
        String query="select teacher.t_email from teacher where t_email=?";
        PreparedStatement pre =con.prepareStatement(query);
        pre.setString(1,email);
        ResultSet res=pre.executeQuery();
        if(res.next()){
            b=true;
        }
        res.close();
        pre.close();
        return  b;
    }
    public ResultSet getTeacherDetailsData() throws SQLException {
        PreparedStatement pre=con.prepareStatement("select * from teacher");
        ResultSet res=pre.executeQuery();
        return  res;
    }
    public int getlastStudentId() throws SQLException {
        int s=0;
        PreparedStatement pre=con.prepareStatement("select top 1 student_data.id from student_data ORDER BY student_data.id DESC");
        ResultSet res=pre.executeQuery();
     while (res.next()){
         s= res.getInt(1);
     }
res.close();
     pre.close();
        return  s;
    }
    public ResultSet getStudent_data_details_show(int id) throws SQLException {
        String query="select status.Date,course.course_name, course.subject_name,status.status from status\n" +
                "left join student_data on \n" +
                "student_data.id=status.s_id \n" +
                "left join course on\n" +
                "course.C_ID=status.C_ID where student_data.id=?";
        PreparedStatement pre=con.prepareStatement(query);
        pre.setInt(1,id);
        ResultSet res=pre.executeQuery();
        return  res;
    }


}
