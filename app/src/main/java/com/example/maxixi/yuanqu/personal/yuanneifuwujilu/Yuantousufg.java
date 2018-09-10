package com.example.maxixi.yuanqu.personal.yuanneifuwujilu;

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

public class Yuantousufg extends Fragment {

    private List<Yuanqujilulei> yuanqujiluleiList=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.dctivity_yuanqufujilu_tousu, container, false);

        inityuanqujiluleiList();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.yuanqufuwujilu_tousu_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Yuanqujiluadapter yuanqujiluadapter = new Yuanqujiluadapter(yuanqujiluleiList);
        recyclerView.setAdapter(yuanqujiluadapter);


        return view;
    }


    private void inityuanqujiluleiList() {
        Yuanqujilulei madada=new Yuanqujilulei("网络链接不通畅","2017-09-09");
        yuanqujiluleiList.add(madada);
        Yuanqujilulei madada1=new Yuanqujilulei("网络链接不通畅","2017-09-09");
        yuanqujiluleiList.add(madada1);
        Yuanqujilulei madada2=new Yuanqujilulei("网络链接不通畅","2017-09-09");
        yuanqujiluleiList.add(madada2);

    }
}