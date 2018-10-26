package com.example.maxixi.yuanqu.personal.tingche;

public class tingchejilulei {
    private String timeday;
//    private String timetime;
    private String money;

    public tingchejilulei(String timeday,String money){
        this.timeday=timeday;
//        this.timetime=timetime;
        this.money=money;
    }

    public String getTimeday(){return timeday;}
//    public String getTimetime(){return timetime;}
    public String getMoney(){return money;}
}
