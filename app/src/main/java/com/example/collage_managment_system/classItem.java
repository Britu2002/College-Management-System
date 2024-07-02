package com.example.collage_managment_system;

public class classItem {
    private int cid;
    private String className;
   private  String subjectName;
   public classItem ()
   {

   }
   public classItem(int cid,String className,String subjectName)
   {
       this.cid=cid;
       this.className=className;
       this.subjectName=subjectName;
   }
    public String getClassName()
    {
        return className;
    }
    public void setClassName(String className)
    {
        this.className=className;
    }
    public String getSubjectName()
    {
        return subjectName;
    }
    public void setSubjectName(String subjectName)
    {
        this.subjectName=subjectName;
    }
    public classItem(String className,String subjectName)
    {
        this.className=className;
        this.subjectName=subjectName;

    }
    public int getCid()
    {
        return cid;
    }
    public void setCid(int cid)
    {
        this.cid=cid;
    }
}
