package com.example.maxixi.yuanqu.personal.tingche;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maxixi.yuanqu.R;

import java.util.List;

public class tingchejiluadapter extends RecyclerView.Adapter<tingchejiluadapter.ViewHolder> {
    private List<tingchejilulei> mylist;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView jilutimeday;
//        TextView jilutimetime;
        TextView jilumoney;

        public ViewHolder(View view){
            super(view);
            jilutimeday=(TextView)view.findViewById(R.id.tingchejilujilu_timeday);
//            jilutimetime=(TextView)view.findViewById(R.id.tingchejilujilu_timetime);
            jilumoney=(TextView)view.findViewById(R.id.tingchejilujilu_money);
        }
    }

    public tingchejiluadapter(List<tingchejilulei> jilulist){mylist=jilulist;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewtype){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dctivity_tingchejilu_monthlist_item,parent,false);
        ViewHolder holder=new ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        tingchejilulei tingchejilulei=mylist.get(position);
        holder.jilutimeday.setText(tingchejilulei.getTimeday());
//        holder.jilutimetime.setText(tingchejilulei.getTimetime());
        holder.jilumoney.setText(tingchejilulei.getMoney());
    }

    @Override
    public int getItemCount(){
        return mylist.size();
    }


}
