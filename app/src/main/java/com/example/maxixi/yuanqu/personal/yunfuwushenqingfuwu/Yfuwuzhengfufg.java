package com.example.maxixi.yuanqu.personal.yunfuwushenqingfuwu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maxixi.yuanqu.R;

import java.util.ArrayList;
import java.util.List;

public class Yfuwuzhengfufg extends Fragment {

    private List<yunfuwujilulei> yunfuwuList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.dctivity_yunfuwushenqingfuwu_zhengfu, container, false);

        inityunfuwuList();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.yfuwu_zhengfu_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Yunfuwujiluadapter yunfuwujiluadapter = new Yunfuwujiluadapter(yunfuwuList);
        recyclerView.setAdapter(yunfuwujiluadapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>50){
                    Log.i("----------","已经过50啦"+dy+"=================="+dx);
                }
            }
        });

        return view;
    }

    private void inityunfuwuList() {
        yunfuwujilulei madada = new yunfuwujilulei("政府政策", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada);
        yunfuwujilulei madada1 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada1);
        yunfuwujilulei madada2 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada2);
        yunfuwujilulei madada21 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada21);
        yunfuwujilulei madada22 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada22);
        yunfuwujilulei madada23 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada23);
        yunfuwujilulei madada24 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada24);
        yunfuwujilulei madada25 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada25);
        yunfuwujilulei madada26 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada26);
        yunfuwujilulei madada2666 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada2666);
        yunfuwujilulei madada266666 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada266666);
        yunfuwujilulei madada211 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada211);
        yunfuwujilulei madada222 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada222);
        yunfuwujilulei madada233 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada233);
        yunfuwujilulei madada244 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada244);
        yunfuwujilulei madada255 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada255);
        yunfuwujilulei madada26666 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada26666);
        yunfuwujilulei madada2123 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada2123);
        yunfuwujilulei madada21233 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada21233);
        yunfuwujilulei madada212333 = new yunfuwujilulei("创业服务", "已完成", "申请公司", "2018-09-09");
        yunfuwuList.add(madada212333);

    }


}