package com.example.maxixi.yuanqu.cloud.qiyefuwu;

public class Cloud_qiyefuwu_bean {
    private int image;
    private String qid;
    private int average_cost;
    private int park_use_rate;
    private int average_time;

    public Cloud_qiyefuwu_bean(int image,String qid,int average_cost,int park_use_rate,int average_time){
        this.image=image;
        this.qid=qid;
        this.average_cost=average_cost;
        this.park_use_rate=park_use_rate;
        this.average_time=average_time;
    }
    public int getImage(){
        return image;
    }

    public String getQid(){
        return qid;
    }

    public int getAverage_cost(){
        return average_cost;
    }

    public int getPark_use_rate(){
        return park_use_rate;
    }

    public int getAverage_time(){
        return average_time;
    }

}
