package com.example.maxixi.yuanqu.cloud;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maxixi.yuanqu.R;

import java.util.ArrayList;
import java.util.List;

public class Fragmentchuangyechuangye extends Fragment {

    private List<cloud_zhidao_lei> zhidaoList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container_cloud, Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.cctivity_cloud_chuangye_zhidao,container_cloud,false);

        //viewlist
        initzhidaoList();
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.cloud_chuangye_recycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        cloud_zhidao_adapter cloud_zhidao_adapter=new cloud_zhidao_adapter(zhidaoList);
        recyclerView.setAdapter(cloud_zhidao_adapter);
        recyclerView.setNestedScrollingEnabled(false);

        return view;
    }

    //活动详情recyclerview
    private void initzhidaoList() {
        cloud_zhidao_lei madada = new cloud_zhidao_lei("这是标题", "2000-00-00");
        zhidaoList.add(madada);
        cloud_zhidao_lei apple = new cloud_zhidao_lei("这是标题", "2000-00-00");
        zhidaoList.add(apple);
        cloud_zhidao_lei apple1 = new cloud_zhidao_lei("这是标题", "2000-00-00");
        zhidaoList.add(apple1);
    }
}
