package com.example.cbocs;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SidesSelector extends Fragment {
    String menuType;
    OrderHolder oH = new OrderHolder();
    ImageButton delete1, delete2, delete3;
    public SidesSelector(){

    }
    private SidesAdapter ffa;
    List<MenuItem> menuList;
    ConstraintLayout constraintLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fancy_fixing_fragment, container, false);
        final Cursor crs;

        menuList = new ArrayList<>();
        RecyclerView rV = fragView.findViewById(R.id.recyclerView);

        rV.setHasFixedSize(true);
        rV.setLayoutManager(new LinearLayoutManager(getActivity()));

        final OrderHolder orderHolder = new OrderHolder();
        MenuDatabase mDB = new MenuDatabase(getActivity());
        crs = mDB.getCursor();

        TextView itemName = fragView.findViewById(R.id.itemName);
        TextView side1 = fragView.findViewById(R.id.side1);
        TextView side2 = fragView.findViewById(R.id.side2);
        TextView side3 = fragView.findViewById(R.id.side3);
        TextView bread = fragView.findViewById(R.id.bread);

        delete1 = fragView.findViewById(R.id.deleteSide1);
        delete2 = fragView.findViewById(R.id.deleteSide2);
        delete3 = fragView.findViewById(R.id.deleteSide3);

        configDeleters();

        oH.myInstance().order = itemName;
        oH.myInstance().textSide1 = side1;
        oH.myInstance().textSide2 = side2;
        oH.myInstance().textSide3 = side3;
        oH.myInstance().textBread = bread;


        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DAY_OF_WEEK);
        String today;

        switch(date){
            case(Calendar.MONDAY):
                today = "M";
                break;
            case(Calendar.TUESDAY):
                today = "T";
                break;
            case(Calendar.WEDNESDAY):
                today = "W";
                break;
            case(Calendar.THURSDAY):
                today = "R";
                break;
            case(Calendar.FRIDAY):
                today = "F";
                break;
            case(Calendar.SATURDAY):
                today = "S";
                break;
            case(Calendar.SUNDAY):
                today = "U";
                break;
            default:
                today = "A";
                break;
        }
        while(crs.moveToNext()) {
            String menuName = crs.getString(4);
            String menuDay = crs.getString(2);
            System.out.println(menuDay);
            String everyDay = "A";
            if(menuName.equals("Sides")){
                if (menuDay.endsWith(everyDay) || menuDay.equals(today)) {
                    menuList.add(new MenuItem(crs.getString(1), crs.getInt(3)));
                }
            }
            else{
                //menuList.add(new MenuItem("Broke", crs.getInt(3)));
            }

        }
        configConfirm(fragView);
        configCancel(fragView);
        ffa = new SidesAdapter(getActivity(), menuList);
        rV.setAdapter(ffa);
        //Loop through the database to find fancy fixing and then add them to a list...
        //Make button for each item in the list...
        return fragView;
    }
    private void configDeleters(){
        final OrderHolder oH = new OrderHolder();
        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oH.myInstance().side1 = null;
                oH.myInstance().pointer = "1";
                oH.myInstance().updateText();
            }
        });
        delete2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                oH.myInstance().side2 = null;
                oH.myInstance().pointer = "2";
                oH.myInstance().updateText();
            }
        });
        delete3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                oH.myInstance().side3 = null;
                oH.myInstance().pointer = "3";
                oH.myInstance().updateText();
            }
        });
    }
    public void configConfirm(View view){
        Button conf = view.findViewById(R.id.confirm);
        conf.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                OrderHolder oH = new OrderHolder();
                int nS = oH.numSides;
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                //I will make a dedicated screen for selecting the sides, it will keep track of the sides while selecting

                if(true){
                    //I will run a unique fragment that will ask for the sides needed, it will be very similar to the current side selector
                    //but will hae a different adapter
                    ft.replace(R.id.fragment_container, new BreadSelector());
                    ft.addToBackStack(null);
                    ft.commit();
                }
                else{
                    //Do Nothing
                }
            }
        });
    }
    public void configCancel(View view){
        Button cancel = view.findViewById(R.id.back);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Will need to go back to previous fragment
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                OrderHolder oH = new OrderHolder();
                ft.replace(R.id.fragment_container, new MenuItemSelector(oH.myInstance().menuType, 0));
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

}
