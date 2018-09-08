package com.example.maxixi.yuanqu.personal.yunfuwushenqingfuwu;

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

public class Yfuwuchuangyefg extends Fragment {

    private List<yunfuwujilulei> yunfuwuList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.dctivity_yunfuwushenqingfuwu_chuangye, container, false);


        inityunfuwuList();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.yfuwu_chuangye_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Yunfuwujiluadapter yunfuwujiluadapter = new Yunfuwujiluadapter(yunfuwuList);
        recyclerView.setAdapter(yunfuwujiluadapter);

        return view;
    }

    private void inityunfuwuList() {
        yunfuwujilulei madada=new yunfuwujilulei("创业服务","已完成","申请公司","2018-09-09");
        yunfuwuList.add(madada);
        yunfuwujilulei madada1=new yunfuwujilulei("创业服务","已完成","申请公司","2018-09-09");
        yunfuwuList.add(madada1);
        yunfuwujilulei madada2=new yunfuwujilulei("创业服务","已完成","申请公司","2018-09-09");
        yunfuwuList.add(madada2);
    }


}