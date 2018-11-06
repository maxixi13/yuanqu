package com.example.maxixi.yuanqu.RecyclerViewGroup;

public class hdxx {
    private String name;
    private String nametext;
    private String imageId;
    private String id;

    public hdxx(String name, String nametext, String imageId,String id) {
        this.name = name;
        this.nametext = nametext;
        this.imageId = imageId;
        this.id=id;
    }

    public String getname() {
        return name;
    }

    public String getnametext() {
        return nametext;
    }

    public String getimageId() {
        return imageId;
    }

    public String getId(){
        return id;
    }
}
