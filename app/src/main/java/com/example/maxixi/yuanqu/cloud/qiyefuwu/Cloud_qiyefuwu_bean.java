package com.example.maxixi.yuanqu.cloud.qiyefuwu;

public class Cloud_qiyefuwu_bean {
    private int image;
    private String qid;
    private String average_cost;
    private String park_use_rate;
    private String average_time;
    private String lid;

    public Cloud_qiyefuwu_bean(int image,String qid,String average_cost,String park_use_rate,String average_time,String lid){
        this.image=image;
        this.qid=qid;
        this.average_cost=average_cost;
        this.park_use_rate=park_use_rate;
        this.average_time=average_time;
        this.lid=lid;
    }
    public int getImage(){
        return image;
    }

    public String getQid(){
        return qid;
    }

    public String getAverage_cost(){
        return average_cost;
    }

    public String getPark_use_rate(){
        return park_use_rate;
    }

    public String getAverage_time(){
        return average_time;
    }

    public String getLid(){
        return lid;
    }

}
