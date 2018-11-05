package com.example.maxixi.yuanqu.personal.waimaijilu;

public class Caipinbean {
    private String dishimage;
    private String dishname;
    private String dishamount;
    private String totalprice;


    public Caipinbean(String dishimage,String dishname,String dishamount,String totalprice){
        this.dishimage=dishimage;
        this.dishname=dishname;
        this.dishamount=dishamount;
        this.totalprice=totalprice;
    }
    public String getDishimage()
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
