package com.example.cbocs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FancyFixinAdapter extends RecyclerView.Adapter<FancyFixinAdapter.FancyFixinViewHolder>{
    private Context ctx;
    List<MenuItem> menuList;
    FancyFixinViewHolder ffvh;
    public FancyFixinAdapter(Context ctx, List<MenuItem> menuList){
        this.ctx = ctx;
        this.menuList = menuList;
    }

    @Override
    public FancyFixinViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.menu_card_view, null);
        OrderHolder orderHolder = new OrderHolder();
        orderHolder.myInstance().updateText();
        return new FancyFixinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FancyFixinViewHolder holder, final int position){
        MenuItem item = menuList.get(position);
        holder.itemName.setText(String.valueOf(item.getDishName()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do Something
                OrderHolder orderHolder = new OrderHolder();
                String meal = menuList.get(position).getDishName();
                int numSides = menuList.get(position).getSideNum();
                System.out.println(meal + " : " + numSides);
                //Get the new sides here...
                if(menuList.get(position).dishName.equals(orderHolder.myInstance().orderName)){
                    holder.cardView.setBackgroundColor(Color.parseColor("#551505"));
                    orderHolder.myInstance().orderName = null;
                    orderHolder.myInstance().numSides = 0;
                    orderHolder.myInstance().updateText();

                }
                else
                {
                    if(ffvh == null){
                        ffvh = holder;
                    }else{
                        ffvh.cardView.setBackgroundColor(Color.parseColor("#551505"));
                        ffvh = holder;
                    }
                    holder.cardView.setBackgroundColor(Color.parseColor("#444444"));
                    orderHolder.myInstance().orderName = meal;
                    orderHolder.myInstance().numSides = numSides;

                    try{
                        orderHolder.myInstance().updateText();

                    }
                    catch(Exception e){

                    }
                }
            }
        });
    }

    @Override
    public int getItemCount(){return menuList.size();}
    class FancyFixinViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        CardView cardView;

        public FancyFixinViewHolder(View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            itemName = itemView.findViewById(R.id.itemName);
        }
    }
}
