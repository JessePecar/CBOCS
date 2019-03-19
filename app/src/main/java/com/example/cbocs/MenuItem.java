package com.example.cbocs;

public class MenuItem {
    String dishName;
    int sideNum;
    public MenuItem(String name, int numSides){
        this.dishName = name;
        this.sideNum = numSides;
    }
    public String getDishName(){return dishName;}
    public int getSideNum(){return sideNum;}
}
