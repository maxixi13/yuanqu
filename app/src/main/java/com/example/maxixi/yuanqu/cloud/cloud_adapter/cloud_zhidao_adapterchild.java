package com.example.maxixi.yuanqu.cloud.cloud_adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.cloud.Fragmentchuangyechuangye;

import java.util.List;

import static android.media.CamcorderProfile.get;


public class cloud_zhidao_adapterchild extends RecyclerView.Adapter<cloud_zhidao_adapterchild.ViewHolder> {


    private List<cloud_zhidao_leichild> myzhidaochildlist;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView zhidaoText;
        TextView zhidaoTime;

        public ViewHolder(View view) {
            super(view);
            zhidaoText = (TextView) view.findViewById(R.id.chuangye_zhidao_namechild);
            zhidaoTime = (TextView) view.findViewById(R.id.chuangye_zhidao_timechild);
        }

    }

    public cloud_zhidao_adapterchild(List<cloud_zhidao_leichild> zhidaochildlist) {
        myzhidaochildlist = zhidaochildlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cctivity_cloud_chuangye_zhidao_itemchild, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final cloud_zhidao_leichild cloud_zhidao_leichild = myzhidaochildlist.get(position);
        holder.zhidaoText.setText(cloud_zhidao_leichild.getname());
        holder.zhidaoTime.setText(cloud_zhidao_leichild.getnametime());


        //点击事件
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(holder.itemView.getContext(),"指导的儿子"+get(position),Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return myzhidaochildlist.size();
    }

}