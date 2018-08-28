package com.example.maxixi.yuanqu.cloud;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maxixi.yuanqu.R;

import java.util.List;

public class cloud_luyan_adapter extends RecyclerView.Adapter<cloud_luyan_adapter.ViewHolder>{

    private List<cloud_zhidao_lei> myzhidaolist;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView zhidaoText;
        TextView zhidaoTime;

        public ViewHolder(View view){
            super(view);
            zhidaoText=(TextView)view.findViewById(R.id.chuangye_luyan_name);
            zhidaoTime=(TextView)view.findViewById(R.id.chuangye_luyan_time) ;
        }

    }

    public cloud_luyan_adapter(List<cloud_zhidao_lei>zhidaolist){ myzhidaolist=zhidaolist; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cctivity_cloud_chuangye_luyan_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        cloud_zhidao_lei cloud_zhidao_lei = myzhidaolist.get(position);
        holder.zhidaoText.setText(cloud_zhidao_lei.getname());
        holder.zhidaoTime.setText(cloud_zhidao_lei.getnametext());
    }

    @Override
    public int getItemCount(){
        return myzhidaolist.size();
    }

}