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

public class SidesAdapter extends RecyclerView.Adapter<SidesAdapter.SidesViewHolder>{
    private Context ctx;
    List<MenuItem> menuList;
    String pointer;
    SidesViewHolder savedSide1, savedSide2, savedSide3;
    public SidesAdapter(Context ctx, List<MenuItem> menuList){
        this.ctx = ctx;
        this.menuList = menuList;
    }

    @Override
    public SidesViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.menu_card_view, null);
        OrderHolder orderHolder = new OrderHolder();
        orderHolder.myInstance().updateText();
        return new SidesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SidesViewHolder holder, final int position){
        MenuItem item = menuList.get(position);
        holder.itemName.setText(String.valueOf(item.getDishName()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do Something

                OrderHolder orderHolder = new OrderHolder();
                orderHolder.myInstance().pointer = "1";
                String side = menuList.get(position).getDishName();
                int numSides = menuList.get(position).getSideNum();
                System.out.println(side + " : " + numSides);
                //This is where the sides will be implemented
                
                if(orderHolder.myInstance().side3 == null || orderHolder.myInstance().side3 == null || orderHolder.myInstance().side2 == null){
                    if(orderHolder.myInstance().side1 == null){
                        orderHolder.myInstance().side1 = menuList.get(position).dishName;
                    }
                    else if(orderHolder.myInstance().side2 == null){
                        orderHolder.myInstance().side2 = menuList.get(position).dishName;
                    }
                    else if(orderHolder.myInstance().side3 == null){
                        orderHolder.myInstance().side3 = menuList.get(position).dishName;
                    }
                    orderHolder.myInstance().updateText();
                }

            }
        });
    }

    @Override
    public int getItemCount(){return menuList.size();}
    class SidesViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        CardView cardView;

        public SidesViewHolder(View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            itemName = itemView.findViewById(R.id.itemName);
        }
    }
}
