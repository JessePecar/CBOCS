package com.example.cbocs;

import android.support.annotation.Nullable;

public class OrderCard {
    String guestMeal, side1, side2, side3, breadChoice, notes;
    int guestNum;
    public OrderCard(int guestNum,
                         String guestMealID,
                         @Nullable String side1ID,
                         @Nullable String side2ID,
                         @Nullable String side3ID,
                         @Nullable String breadChoiceID,
                         @Nullable String notes){
        {
            this.guestNum = guestNum;
            this.guestMeal = guestMealID;
            if(side1ID != null){
                this.side1 = side1ID;
            }else{
                this.side1 = "None";
            }
            if(side2ID != null){
                this.side2 = side2ID;
            }
            else{
                this.side2 = "None";
            }
            if(side3ID != null){
                this.side3 = side3ID;
            }
            else{
                this.side3 = "None";
            }
            if(breadChoiceID != null){
                this.breadChoice = breadChoiceID;
            }
            else{
                this.breadChoice = "None";
            }
            if(notes != null){
                this.notes = notes;
            }else{
                this.notes = "None";
            }

        }
    }
    int getGuestNum(){return guestNum;}
    String getGuestMeal(){return guestMeal;}
    String getGuestSide1(){return side1;}
    String getGuestSide2(){return side2;}
    String getGuestSide3(){return side3;}
    String getGuestBread(){return breadChoice;}
    String getGuestNotes(){return notes;}

}
