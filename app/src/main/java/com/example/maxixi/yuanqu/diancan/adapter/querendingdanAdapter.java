package com.example.maxixi.yuanqu.diancan.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.diancan.model.querendingdanlei;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class querendingdanAdapter extends RecyclerView.Adapter<querendingdanAdapter.ViewHolder> {


    private List<querendingdanlei> mylist;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView dishimage;
        TextView dishname;
        TextView dishamount;
        TextView totalprice;

        public ViewHolder(View view) {
            super(view);
            dishimage = (CircleImageView) view.findViewById(R.id.diancan_queren_dish_imgage);
            dishname = (TextView) view.findViewById(R.id.queren_dish_name);
            dishamount = (TextView) view.findViewById(R.id.queren_dish_amount);
            totalprice = (TextView) view.findViewById(R.id.queren_dish_price);
        }

    }

    public querendingdanAdapter(List<querendingdanlei> querenlist) {
        mylist = querenlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ectivity_diancan_queren_item, parent, false);
        ViewHolder holder = new ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final querendingdanlei querendingdanlei = mylist.get(position);
        holder.dishimage.setImageResource(querendingdanlei.getDishimage());
        holder.dishname.setText(querendingdanlei.getDishname());
        holder.dishamount.setText(querendingdanlei.getDishamount());
        holder.totalprice.setText(querendingdanlei.getTotalprice());

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


}