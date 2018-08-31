package com.example.maxixi.yuanqu.cloud;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_adapter;
import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_adapterchild;
import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_lei;
import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_leichild;

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
        recyclerView.setNestedScrollingEnabled(false);


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
        cloud_zhidao_lei madada = new cloud_zhidao_lei("2018-07");
        zhidaoList.add(madada);
        cloud_zhidao_lei apple = new cloud_zhidao_lei("2018-06");
        zhidaoList.add(apple);
        cloud_zhidao_lei apple1 = new cloud_zhidao_lei("2018-05");
        zhidaoList.add(apple1);
    }

}
