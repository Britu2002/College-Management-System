package com.example.collage_managment_system;

public class StudentItem {
    private int roll;
    private int cid;
    private String name;
    private String status;
    private int sid;




    public StudentItem(int sid,int roll, String name)
    {this.sid=sid;
        this.roll =roll;
        this.name=name;

        status="";
    }
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
    public int getRoll()
    {
        return roll;
    }
    public void setRoll(int roll)
    {
        this.roll=roll;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status=status;
    }
    public int getSid(){return sid;}
    public void setSid(int sid){this.sid=sid;}
}
