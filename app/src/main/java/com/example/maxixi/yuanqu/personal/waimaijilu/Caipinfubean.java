package com.example.maxixi.yuanqu.personal.waimaijilu;

import java.util.List;

public class Caipinfubean {
    private String title;
    private List<Caipinbean> caipinbeanList;

    public Caipinfubean(String title,List<Caipinbean> caipinbeanList){
        this.title=title;
        this.caipinbeanList=caipinbeanList;
    }

    public String getTitle(){
        return title;
    }

    public List<Caipinbean> getCaipinbeanList(){
        return caipinbeanList;
    }
}
