package com.example.maxixi.yuanqu.diancan.model;

import java.io.Serializable;

public class Dish implements Serializable {

    private String dishName;
    private double dishPrice;
    private int dishAmount;
    private int dishRemain;
    private String dishImage;
    private String mid;

    public Dish(String dishName, double dishPrice, int dishAmount,String dishImage,String mid){
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishAmount = dishAmount;
        this.dishRemain = dishAmount;
        this.dishImage=dishImage;
        this.mid=mid;
    }

    public String getMid(){
        return mid;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public int getDishAmount() {
        return dishAmount;
    }

    public void setDishAmount(int dishAmount) {
        this.dishAmount = dishAmount;
    }

    public String getDishImage(){ return dishImage;}

    public int getDishRemain() {
        return dishRemain;
    }

    public void setDishRemain(int dishRemain) {
        this.dishRemain = dishRemain;
    }

    public int hashCode(){
        int code = this.dishName.hashCode()+(int)this.dishPrice;
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this)return true;

        return obj instanceof Dish &&
                this.dishName.equals(((Dish)obj).dishName) &&
                this.dishPrice ==  ((Dish)obj).dishPrice &&
                this.dishAmount == ((Dish)obj).dishAmount &&
                this.dishRemain == ((Dish)obj).dishRemain;
    }
}
