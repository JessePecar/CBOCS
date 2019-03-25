package com.example.cbocs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class NewOrderAdapter extends RecyclerView.Adapter<NewOrderAdapter.NewOrderViewHolder>{
    private Context ctx;
    List<OrderCard> menuList;
    NewOrderViewHolder savedSide1, savedSide2, savedSide3;
    public NewOrderAdapter(Context ctx, List<OrderCard> menuList){
        this.ctx = ctx;
        this.menuList = menuList;
    }

    @Override
    public NewOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.new_order_card, null);
        OrderHolder orderHolder = new OrderHolder();
        orderHolder.myInstance().updateText();
        return new NewOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewOrderViewHolder holder, final int position){
        OrderCard item = menuList.get(position);
        holder.itemName.setText(String.valueOf(item.getGuestMeal()));
        holder.side1.setText(String.valueOf(item.getGuestSide1()));
        holder.side2.setText(String.valueOf(item.getGuestSide2()));
        holder.side3.setText(String.valueOf(item.getGuestSide3()));
        holder.bread.setText(String.valueOf(item.getGuestBread()));
        holder.notes.setText(String.valueOf(item.getGuestNotes()));
        holder.deleteMeal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setCancelable(true);
                builder.setTitle("Cancel Meal");
                builder.setMessage("Are you Sure");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                menuList.remove(position);
                                OrderHolder oH = new OrderHolder();
                                oH.myInstance().orderList.remove(position);
                                notifyDataSetChanged();
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
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OrderHolder oH = new OrderHolder();
                oH.myInstance().orderName = menuList.get(position).getGuestMeal();
                oH.myInstance().side1 = menuList.get(position).getGuestSide1();
                oH.myInstance().side2 = menuList.get(position).getGuestSide2();
                oH.myInstance().side3 = menuList.get(position).getGuestSide3();
                oH.myInstance().bread = menuList.get(position).getGuestBread();

            }
        });

    }

    @Override
    public int getItemCount(){return menuList.size();}
    class NewOrderViewHolder extends RecyclerView.ViewHolder{
        TextView itemName, side1, side2, side3, bread, notes;
        CardView cardView;
        ImageButton deleteMeal;

        public NewOrderViewHolder(View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            itemName = itemView.findViewById(R.id.meal);
            side1 = itemView.findViewById(R.id.side1);
            side2 = itemView.findViewById(R.id.side2);
            side3 = itemView.findViewById(R.id.side3);
            bread = itemView.findViewById(R.id.bread);
            notes = itemView.findViewById(R.id.notes);
            deleteMeal = itemView.findViewById(R.id.deleteOrder);
        }
    }
}
