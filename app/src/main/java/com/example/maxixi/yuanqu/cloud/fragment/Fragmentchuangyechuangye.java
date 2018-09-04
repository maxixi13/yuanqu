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

public class Fragmentchuangyechuangye extends Fragment {

    private List<cloud_zhidao_lei> zhidaoList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container_cloud, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.cctivity_cloud_chuangye_zhidao, container_cloud, false);

        //viewlist
        initzhidaoList();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.cloud_chuangye_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        cloud_zhidao_adapter cloud_zhidao_adapter = new cloud_zhidao_adapter(zhidaoList);
        recyclerView.setAdapter(cloud_zhidao_adapter);


//        //创业recyceler监听
//        cloud_zhidao_adapter.setOnItemClickListener(new cloud_zhidao_adapterchild.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(getContext(), "click " + zhidaoList.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });
//        recyclerView.setAdapter(cloud_zhidao_adapter);


        return view;
    }

    //活动详情recyclerview
    private void initzhidaoList() {
        cloud_zhidao_lei madada = new cloud_zhidao_lei("芈租界大新闻","2018-07");
        zhidaoList.add(madada);
        cloud_zhidao_lei apple = new cloud_zhidao_lei("是不是指导","2018-07");
        zhidaoList.add(apple);
        cloud_zhidao_lei apple2 = new cloud_zhidao_lei("重写的指导","2018-07");
        zhidaoList.add(apple2);
        cloud_zhidao_lei apple3 = new cloud_zhidao_lei("重写的指导","2018-07");
        zhidaoList.add(apple3);
        cloud_zhidao_lei apple4 = new cloud_zhidao_lei("重写的指导","2018-07");
        zhidaoList.add(apple4);
        cloud_zhidao_lei apple5 = new cloud_zhidao_lei("重写的指导","2018-07");
        zhidaoList.add(apple5);
        cloud_zhidao_lei apple6 = new cloud_zhidao_lei("重写的指导","2018-07");
        zhidaoList.add(apple6);
        cloud_zhidao_lei apple7 = new cloud_zhidao_lei("重写的指导","2018-07");
        zhidaoList.add(apple7);
        cloud_zhidao_lei apple8 = new cloud_zhidao_lei("重写的指导","2018-07");
        zhidaoList.add(apple8);


    }

}
