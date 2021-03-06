package com.example.maxixi.yuanqu.cloud.cloud_adapter;


import android.content.Intent;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;


import com.example.maxixi.yuanqu.MainActivity;
import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.RecyclerViewGroup.hdxxAdapter;
import com.example.maxixi.yuanqu.cloud.cloud_chuangye_chuangye;
import com.example.maxixi.yuanqu.cloud.cloud_jingrong_jinrong;
import com.example.maxixi.yuanqu.cloud.cloud_zhengfu_zhengfu;
import com.example.maxixi.yuanqu.cloud.cloud_zhengfu_zhengfu_shenqing;


import java.util.List;


public class cloud_zhidao_adapter extends RecyclerView.Adapter<cloud_zhidao_adapter.ViewHolder> {


    //点击事件
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }



    private List<cloud_zhidao_lei> myzhidaolist;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView zhidaoText;
        TextView zhidaoTime;
        View adapterview;

        public ViewHolder(View view) {
            super(view);
            zhidaoText = (TextView) view.findViewById(R.id.chuangye_zhidao_name);
            zhidaoTime = (TextView) view.findViewById(R.id.chuangye_zhidao_time);
            adapterview = view;
        }

    }

    public cloud_zhidao_adapter(List<cloud_zhidao_lei> zhidaolist) {
        myzhidaolist = zhidaolist;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cctivity_cloud_chuangye_zhidao_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final cloud_zhidao_lei cloud_zhidao_lei = myzhidaolist.get(position);
        holder.zhidaoText.setText(cloud_zhidao_lei.getname());
        holder.zhidaoTime.setText(cloud_zhidao_lei.getnametime());


        //判断是否设置了监听器 点击事件
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return myzhidaolist.size();
    }


}
