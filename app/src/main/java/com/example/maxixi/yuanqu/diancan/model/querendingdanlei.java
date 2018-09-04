package com.example.maxixi.yuanqu.diancan.model;

public class querendingdanlei {
    private int dishimage;
    private String dishname;
    private String dishamount;
    private String totalprice;

    public querendingdanlei(int dishimage,String dishname,String dishamount,String totalprice){
        this.dishimage=dishimage;
        this.dishname=dishname;
        this.dishamount=dishamount;
        this.totalprice=totalprice;
    }
    public int getDishimage()
    {
        return dishimage;
    }
    public  String getDishname(){
        return dishname;
    }
    public String getDishamount()
    {
        return dishamount;
    }
    public String getTotalprice()
    {
        return totalprice;
    }

}