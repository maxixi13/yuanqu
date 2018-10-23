package com.example.maxixi.yuanqu.personal.yunfuwushenqingfuwu;

public class yunfuwujilulei {
    private String title;
    private String zhuangtai;
    private String xiangmu;
    private String time;
    private String cid;

    public yunfuwujilulei(String title,String zhuangtai,String xiangmu,String time,String cid){
        this.title=title;
        this.zhuangtai=zhuangtai;
        this.xiangmu=xiangmu;
        this.time=time;
        this.cid=cid;
    }

    public String getTitle() {
        return title;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public String getXiangmu() {
        return xiangmu;
    }

    public String getTime() {
        return time;
    }

    public String getCid() {
        return cid;
    }
}
