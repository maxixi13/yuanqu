package com.example.maxixi.yuanqu.personal.waimaijilu;

import java.util.List;

public class Caipinfubean {
    private String title;
    private String ctime;
    private int status;
    private String oid;
    private List<Caipinbean> caipinbeanList;

    public Caipinfubean(String title,String ctime,int status,String oid,List<Caipinbean> caipinbeanList){
        this.title=title;
        this.ctime=ctime;
        this.status=status;
        this.caipinbeanList=caipinbeanList;
        this.oid=oid;
    }

    public String getTitle(){
        return title;
    }

    public String getCtime() {
        return ctime;
    }

    public int getStatus() {
        return status;
    }

    public String getOid(){
        return oid;
    }

    public List<Caipinbean> getCaipinbeanList(){
        return caipinbeanList;
    }
}
