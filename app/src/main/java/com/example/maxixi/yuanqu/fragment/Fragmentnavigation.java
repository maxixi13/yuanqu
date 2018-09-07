package com.example.maxixi.yuanqu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.maxixi.yuanqu.GlideImageLoader;
import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.RecyclerViewGroup.hdxx;
import com.example.maxixi.yuanqu.RecyclerViewGroup.hdxxAdapter;
import com.example.maxixi.yuanqu.diancan.diancan;
import com.example.maxixi.yuanqu.personal.tingchejilu;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Fragmentnavigation extends Fragment {

    private List<hdxx> hdxxList = new ArrayList<>();
    private List<hdxx> zcxxList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);


        //banner设置
        Banner banner = (Banner) view.findViewById(R.id.banner);
        //设置banner样式
        //banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        Integer[] images = {R.drawable.home_page_icon, R.drawable.park_service_icon, R.drawable.cloud_service_icon, R.drawable.personal_center_icon, R.drawable.textimage};
        banner.setImages(Arrays.asList(images));
        //设置banner动画效果
        //banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        //banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        //banner.isAutoPlay(true);
        //设置轮播时间
        //banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        //banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        //viewlist
        inithdxx();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.relativelayout_hdxx);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        hdxxAdapter adapterhdxx = new hdxxAdapter(hdxxList);
        recyclerView.setAdapter(adapterhdxx);
        recyclerView.setNestedScrollingEnabled(false);

        initzcxx();
        RecyclerView recyclerViewzcxx = (RecyclerView) view.findViewById(R.id.relativelayout_zcxx);
        LinearLayoutManager layoutManagerzcxx = new LinearLayoutManager(getActivity());
        recyclerViewzcxx.setLayoutManager(layoutManagerzcxx);
        hdxxAdapter adapterzcxx = new hdxxAdapter(zcxxList);
        recyclerViewzcxx.setAdapter(adapterzcxx);
        recyclerViewzcxx.setNestedScrollingEnabled(false);

        //hdxx监听
        adapterhdxx.setOnItemClickListener(new hdxxAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "click " + hdxxList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
//        adapterhdxx.setOnItemLongClickListener(new hdxxAdapter.OnItemLongClickListener() {
//            @Override
//            public void onItemLongClick(View view, int position) {
//                Toast.makeText(getActivity(),"long click "+hdxxList.get(position),Toast.LENGTH_SHORT).show();
//            }
//        });
        recyclerView.setAdapter(adapterhdxx);


        //zcxx监听
        adapterzcxx.setOnItemClickListener(new hdxxAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "click " + zcxxList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
//        adapterzcxx.setOnItemLongClickListener(new hdxxAdapter.OnItemLongClickListener() {
//            @Override
//            public void onItemLongClick(View view, int position) {
//                Toast.makeText(getActivity(),"long click "+position,Toast.LENGTH_SHORT).show();
//            }
//        });
        recyclerViewzcxx.setAdapter(adapterzcxx);


        //diancananniu
        ImageButton bannerdiancan = (ImageButton) view.findViewById(R.id.banner_diancan);
        bannerdiancan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bannerdiancan = new Intent();
                bannerdiancan.setClass(getActivity(), diancan.class);
                startActivity(bannerdiancan);
            }
        });

        ImageButton tingchejilu = (ImageButton) view.findViewById(R.id.banner_tingche);
        tingchejilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), com.example.maxixi.yuanqu.personal.tingchejilu.class);
                startActivity(intent);
            }
        });


        return view;
    }

    //活动详情recyclerview
    private void inithdxx() {
        hdxx madada = new hdxx("这是标题", "这是新闻的文字嗷哦嗷嗷", R.drawable.textimage);
        hdxxList.add(madada);
        hdxx apple = new hdxx("这是标题", "这是新闻的文字嗷哦嗷嗷啊", R.drawable.textimage);
        hdxxList.add(apple);
        hdxx apple1 = new hdxx("这是标题", "这是新闻的文字嗷嗷嗷嗷", R.drawable.textimage);
        hdxxList.add(apple1);
    }

    //政策政策recyclerview
    private void initzcxx() {
        hdxx madada = new hdxx("这是政策", "啊啊啊啊", R.drawable.textimage1);
        zcxxList.add(madada);
        hdxx apple = new hdxx("这是政策", "嗷嗷嗷啊", R.drawable.textimage1);
        zcxxList.add(apple);
        hdxx apple1 = new hdxx("这是真的政策", "啊呜呜呜呜", R.drawable.textimage1);
        zcxxList.add(apple1);
    }
}
