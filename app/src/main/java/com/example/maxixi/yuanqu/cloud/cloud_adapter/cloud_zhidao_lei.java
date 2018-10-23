package com.example.maxixi.yuanqu.cloud.cloud_adapter;

public class cloud_zhidao_lei {
    private String name;
    private String nametime;
    private String lid;

    public cloud_zhidao_lei(String name, String nametime,String lid) {
        this.name = name;
        this.nametime = nametime;
        this.lid=lid;
    }

    public String getname() {
        return name;
    }

    public String getnametime() {
        return nametime;
    }

    public String getLid(){
        return lid;
    }
}
