package com.example.maxixi.yuanqu.db;

public class Chuangyezhidaobean {
    private int id;
    private String title;
    private String ctime;

    public Chuangyezhidaobean(){}

    public int getId() {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setName(String title)
    {
        this.title = title;
    }

    public String getCtime() {
        return ctime;
    }
    public void setCtime(){
        this.ctime=ctime;
    }

    public Chuangyezhidaobean(int id, String title, String ctime)
    {
        super();
        this.id = id;
        this.title = title;
        this.ctime = ctime;
    }

    @Override
    public String toString()
    {
        return "Person [id=" + id + ", name=" + title + ", address=" + ctime + "]";
    }
}
