package com.example.maxixi.yuanqu.cloud.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_adapter;
import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_lei;

import java.util.ArrayList;
import java.util.List;

public class Fragmentchuangyeluyan extends Fragment {

    //    private List<cloud_zhidao_lei> luyanList=new ArrayList<>();
    private List<cloud_zhidao_lei> zhidaoList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container_cloud, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.cctivity_cloud_chuangye_luyan, container_cloud, false);


        initzhidaoList();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.cloud_luyan_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        cloud_zhidao_adapter cloud_luyan_adapter = new cloud_zhidao_adapter(zhidaoList);
        recyclerView.setAdapter(cloud_luyan_adapter);
        recyclerView.setNestedScrollingEnabled(false);


        return view;
    }

    //活动详情recyclerview
    private void initzhidaoList() {
        cloud_zhidao_lei madada = new cloud_zhidao_lei("路演","2018-06");
        zhidaoList.add(madada);
        cloud_zhidao_lei apple = new cloud_zhidao_lei("hahah","2018-06");
        zhidaoList.add(apple);
        cloud_zhidao_lei apple1 = new cloud_zhidao_lei("1234","2018-06");
        zhidaoList.add(apple1);
    }
}
