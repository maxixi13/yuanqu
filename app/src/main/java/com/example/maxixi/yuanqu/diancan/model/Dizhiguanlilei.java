package com.example.maxixi.yuanqu.diancan.model;

public class Dizhiguanlilei {
    private String name;
    private String dianhua;
    private String dizhi;
    private String aid;
    private int status;

    public Dizhiguanlilei(String name, String dianhua, String dizhi, String aid,int status) {
        this.name = name;
        this.dianhua = dianhua;
        this.dizhi = dizhi;
        this.aid = aid;
        this.status=status;
    }

    public String getName() {
        return name;
    }

    public String getDianhua() {
        return dianhua;
    }

    public String getDizhi() {
        return dizhi;
    }

    public String getAid() {
        return aid;
    }

    public int getStatus(){
        return status;
    }
}
