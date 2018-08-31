package com.example.maxixi.yuanqu.cloud.cloud_adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxixi.yuanqu.R;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class cloud_luyan_adapter extends RecyclerView.Adapter<cloud_luyan_adapter.ViewHolder> {


    private List<cloud_zhidao_lei> myzhidaolist;
    private List<cloud_zhidao_leichild> zhidaochildList=new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView zhidaoText;


        public ViewHolder(View view) {
            super(view);
            zhidaoText = (TextView) view.findViewById(R.id.chuangye_zhidao_name);
        }

    }

    public cloud_luyan_adapter(List<cloud_zhidao_lei> zhidaolist) {
        myzhidaolist = zhidaolist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cctivity_cloud_chuangye_zhidao_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        //viewlist嵌套
        initzhidaochildList();
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.cloud_chuangye_recyclerchild);
        LinearLayoutManager layoutManager=new LinearLayoutManager(parent.getContext());
        recyclerView.setLayoutManager(layoutManager);
        cloud_luyan_adapterchild cloud_luyan_adapterchild=new cloud_luyan_adapterchild(zhidaochildList);//变更
        recyclerView.setAdapter(cloud_luyan_adapterchild);//变更
        recyclerView.setNestedScrollingEnabled(false);


        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final cloud_zhidao_lei cloud_zhidao_lei = myzhidaolist.get(position);
        holder.zhidaoText.setText(cloud_zhidao_lei.getname());




        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(),"luyanluyan"+position,Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return myzhidaolist.size();
    }

    private void initzhidaochildList(){
        cloud_zhidao_leichild madada=new cloud_zhidao_leichild("不演了","2018-07-08");
        zhidaochildList.add(madada);
        cloud_zhidao_leichild madada1=new cloud_zhidao_leichild("演不动了","2018-00-00");
        zhidaochildList.add(madada1);
        cloud_zhidao_leichild madada11=new cloud_zhidao_leichild("不想眼了","2015-00-00");
        zhidaochildList.add(madada11);

    }

}
