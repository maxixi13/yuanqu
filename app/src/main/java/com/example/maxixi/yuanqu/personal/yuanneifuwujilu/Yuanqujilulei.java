package com.example.maxixi.yuanqu.personal.yuanneifuwujilu;

import android.widget.TextView;

import java.lang.ref.SoftReference;

public class Yuanqujilulei {
    private String name;
    private String time;
    private String rid;

    public Yuanqujilulei(String name,String time,String rid){
        this.name=name;
        this.time=time;
        this.rid=rid;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getRid(){
        return rid;
    }
}
