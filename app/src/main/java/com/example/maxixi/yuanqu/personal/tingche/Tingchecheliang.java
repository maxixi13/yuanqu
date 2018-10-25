package com.example.maxixi.yuanqu.personal.tingche;

public class Tingchecheliang {
    private String type;
    private String carname;
    private String model;
    private String platenum;
    private String time;
    private String state;
    private String cid;

    public Tingchecheliang(String type,String carname,String model,String platenum,String time,String state,String cid){
        this.type=type;
        this.carname=carname;
        this.model=model;
        this.platenum=platenum;
        this.time=time;
        this.state=state;
        this.cid=cid;
    }

    public String getType() {
        return type;
    }

    public String getCarname() {
        return carname;
    }

    public String getModel() {
        return model;
    }

    public String getPlatenum() {
        return platenum;
    }

    public String getTime() {
        return time;
    }

    public String getState() {
        return state;
    }

    public String getCid(){
        return cid;
    }
}