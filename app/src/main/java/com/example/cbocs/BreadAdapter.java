package com.example.cbocs;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BreadAdapter extends RecyclerView.Adapter<BreadAdapter.BreadViewHolder>{
    private Context ctx;
    List<MenuItem> menuList;
    BreadViewHolder bread1;
    public BreadAdapter(Context ctx, List<MenuItem> menuList){
        this.ctx = ctx;
        this.menuList = menuList;
    }

    @Override
    public BreadViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.menu_card_view, null);
        OrderHolder orderHolder = new OrderHolder();
        orderHolder.myInstance().updateText();
        return new BreadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BreadViewHolder holder, final int position){
        MenuItem item = menuList.get(position);
        holder.itemName.setText(String.valueOf(item.getDishName()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do Something
                 OrderHolder orderHolder = new OrderHolder();
                 if(menuList.get(position).dishName == orderHolder.myInstance().bread){
                     holder.cardView.setBackgroundColor(Color.parseColor("#551505"));
                 }
                 else{
                     holder.cardView.setBackgroundColor(Color.parseColor("#444444"));
                     orderHolder.myInstance().bread = menuList.get(position).dishName;
                     try{
                         orderHolder.myInstance().updateText();

                     }
                     catch(Exception e) {

                     }
                 }

            }
        });
    }

    @Override
    public int getItemCount(){return menuList.size();}
    class BreadViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        CardView cardView;

        public BreadViewHolder(View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            itemName = itemView.findViewById(R.id.itemName);
        }
    }
}
