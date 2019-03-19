package com.example.cbocs;

import android.app.Application;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrderHolder extends Application {

    List<OrderCard> orderList = new ArrayList<>();
    String orderName, menuType;
    String side1 = null, side2 = null, side3 = null, bread = null;
    int numSides, guestCount;
    NewOrderAdapter noa;
    ActivityNewOrder ano;
    TextView order, textSide1, textSide2, textSide3, textBread;

    public void updateText(){
        try{
            if(orderName != null){
                order.setText("Menu Item: " + orderName);
            }else{
                order.setText("Menu Item: ");
            }
            if(side1 != null){
                textSide1.setText("Side: " + side1);
            }else if(numSides < 1){
                textSide1.setText("");
            }else{
                textSide1.setText("Side: ");
            }
            if(side2 != null){
                textSide2.setText("Side: " + side2);
            }else if(numSides < 2){
                textSide2.setText("");
            }else{
                textSide2.setText("Side: ");
            }
            if(side3 != null){
                textSide3.setText("Side: " + side3);
            }else if(numSides < 3){
                textSide3.setText("");
            }else{
                textSide3.setText("Side: ");
            }
            if(bread != null){
                textBread.setText("Bread: " + bread);
            }else{
                textBread.setText("Bread: ");
            }

        }
        catch(Exception e){

        }
    }
    public static OrderHolder instance = null;

    protected OrderHolder(){}

    public static synchronized OrderHolder myInstance(){
        if(null == instance){
            instance = new OrderHolder();
        }
        return instance;
    }
}
