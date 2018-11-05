package com.example.maxixi.yuanqu.personal.waimaijilu;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maxixi.yuanqu.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CaipinAdapter extends RecyclerView.Adapter<CaipinAdapter.ViewHolder> {
    private List<Caipinbean> mcaipinbeanList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView dishimage;
        TextView dishname;
        TextView dishamount;
        TextView totalprice;


        public ViewHolder(View view){
            super(view);
            dishname = (TextView)itemView.findViewById(R.id.queren_dish_name);
            totalprice = (TextView)itemView.findViewById(R.id.queren_dish_price);
            dishamount = (TextView) itemView.findViewById(R.id.queren_dish_amount);
            dishimage=(CircleImageView)itemView.findViewById(R.id.diancan_queren_dish_imgage);
        }
    }

    public CaipinAdapter(List<Caipinbean> caipinbeanList){
        mcaipinbeanList=caipinbeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ectivity_diancan_queren_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);



        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Caipinbean caipinbean=mcaipinbeanList.get(position);
        holder.dishname.setText(caipinbean.getDishname());
        holder.dishamount.setText(caipinbean.getDishamount());
        holder.totalprice.setText(caipinbean.getTotalprice());
        Glide.with(holder.dishimage).load(caipinbean.getDishimage()).into(holder.dishimage);

    }

    @Override
    public int getItemCount() {
        return mcaipinbeanList.size();
    }

}
