package com.example.cbocs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class NewOrderFragment extends Fragment {
    Context ctx;
    NewOrderAdapter ffa;
    List<OrderCard> menuList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.new_order_fragmemt, container, false);
        configButton(fragView);
        configSave(fragView);
        configRefresh(fragView);
        configCancel(fragView);
        String menuType;
        ctx = getActivity();
        OrderHolder oH = new OrderHolder();



        menuList = new ArrayList<>();
        RecyclerView rV = fragView.findViewById(R.id.recyclerView);

        rV.setHasFixedSize(true);
        rV.setLayoutManager(new LinearLayoutManager(getActivity()));

        for(int i = 0; i < oH.myInstance().orderList.size(); i++){
            menuList.add(oH.myInstance().orderList.get(i));
        }
        System.out.println("Getting the new items");
        System.out.println(menuList.size());

        ffa = new NewOrderAdapter(getActivity(), menuList);
        oH.myInstance().noa = ffa;
        rV.setAdapter(ffa);

        return fragView;
    }

    public void configButton(View view){
        Button newButton = view.findViewById(R.id.newOrder);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderHolder oH = new OrderHolder();
                oH.myInstance().guestCount = oH.myInstance().orderList.size();
                Intent intent = new Intent(getActivity(), ActivityNewOrder.class);
                startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            // Make sure the request was successful
            if(resultCode == 420){
                System.out.println("INTENT DATA : " + data.getStringExtra("NEW_TEAM_ID") + " " + data.getStringExtra("NEW_TEAM_NAME") + " " + data.getStringExtra("NEW_TEAM_COMMENTS"));
                // add the data into a new card
                OrderHolder oH = new OrderHolder();
                oH.myInstance().side1 = null;
                oH.myInstance().orderName = null;
                oH.myInstance().side2 = null;
                oH.myInstance().side3 = null;
                oH.myInstance().bread = null;

                menuList.add(oH.myInstance().orderList.get(oH.myInstance().orderList.size() - 1));
                ffa.notifyDataSetChanged();
            }

        }
    }
    public void configRefresh(View view){

        ImageButton refresh = view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(ctx == null){
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, new NewOrderFragment())
                            .addToBackStack(null)
                            .commit();
                    return;
                }
                if(ctx instanceof MainActivity){
                    MainActivity mA = (MainActivity) ctx;
                    mA.loadFragment(new NewOrderFragment());
                }
            }
        });
    }
    public void configSave(View view){
        Button save = view.findViewById(R.id.submit);
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //do nothing

            }
        });
    }
    public void configCancel(View view){
        Button cancel = view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //do nothing
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setCancelable(true);
                builder.setTitle("Cancel Order");
                builder.setMessage("Are you Sure");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                menuList.clear();
                                OrderHolder oH = new OrderHolder();
                                oH.myInstance().orderList.clear();
                                ffa.notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
