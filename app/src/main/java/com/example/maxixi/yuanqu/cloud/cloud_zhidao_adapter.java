package com.example.maxixi.yuanqu.cloud;

import android.content.Intent;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxixi.yuanqu.R;

import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class cloud_zhidao_adapter extends RecyclerView.Adapter<cloud_zhidao_adapter.ViewHolder>{

        private List<cloud_zhidao_lei> myzhidaolist;
        private AdapterView.OnItemClickListener onItemClickListener;

        static class ViewHolder extends RecyclerView.ViewHolder{
            TextView zhidaoText;
            TextView zhidaoTime;

            public ViewHolder(View view){
                super(view);
                zhidaoText=(TextView)view.findViewById(R.id.chuangye_zhidao_name);
                zhidaoTime=(TextView)view.findViewById(R.id.chuangye_zhidao_time) ;
            }

        }

    public cloud_zhidao_adapter(List<cloud_zhidao_lei>zhidaolist){ myzhidaolist=zhidaolist; }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cctivity_cloud_chuangye_zhidao_item,parent,false);
            ViewHolder holder=new ViewHolder(view);
            return holder;
        }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        final cloud_zhidao_lei cloud_zhidao_lei = myzhidaolist.get(position);
        holder.zhidaoText.setText(cloud_zhidao_lei.getname());
        holder.zhidaoTime.setText(cloud_zhidao_lei.getnametext());


        //监听
        holder.itemView.findViewById(R.id.chuangye_zhidao_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(holder.itemView.getContext(),cloud_chuangye_chuangye.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        }

    @Override
    public int getItemCount(){
        return myzhidaolist.size();
    }

}