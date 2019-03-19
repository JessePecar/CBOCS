package com.example.cbocs;

import android.content.Intent;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BreadSelector extends Fragment {
    String menuType;
    OrderHolder oH = new OrderHolder();

    public BreadSelector(){

    }
    private BreadAdapter ffa;
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


        oH.myInstance().order = itemName;
        oH.myInstance().textSide1 = side1;
        oH.myInstance().textSide2 = side2;
        oH.myInstance().textSide3 = side3;
        oH.myInstance().textBread = bread;


        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DAY_OF_WEEK);
        String today;

        while(crs.moveToNext()) {
            String menuName = crs.getString(4);
            String menuDay = crs.getString(2);
            System.out.println(menuDay);
            String everyDay = "A";
            if(menuName.equals("Bread")){
                menuList.add(new MenuItem(crs.getString(1), crs.getInt(3)));
            }
            else{
                //menuList.add(new MenuItem("Broke", crs.getInt(3)));
            }

        }
        configConfirm(fragView);
        configCancel(fragView);
        ffa = new BreadAdapter(getActivity(), menuList);
        rV.setAdapter(ffa);
        //Loop through the database to find fancy fixing and then add them to a list...
        //Make button for each item in the list...
        return fragView;
    }
    public void configConfirm(View view){
        Button conf = view.findViewById(R.id.confirm);
        conf.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                        //Will need to go back to previous fragment
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_container, new NotesFragment());
                        ft.addToBackStack(null);
                        ft.commit();
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
                ft.replace(R.id.fragment_container, new SidesSelector());
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

}
