package com.example.maxixi.yuanqu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.maxixi.yuanqu.GlideImageLoader;
import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.RecyclerViewGroup.hdxx;
import com.example.maxixi.yuanqu.RecyclerViewGroup.hdxxAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Fragmentnavigation extends Fragment {

    private List<hdxx> hdxxList=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.activity_home,container,false);



        //banner设置
        Banner banner = (Banner) view.findViewById(R.id.banner);
        //设置banner样式
        //banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        Integer[] images={R.drawable.home_page_icon,R.drawable.park_service_icon,R.drawable.cloud_service_icon,R.drawable.personal_center_icon,R.drawable.textimage};
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
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.relativelayout_hdxx);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        hdxxAdapter adapterhdxx=new hdxxAdapter(hdxxList);
        recyclerView.setAdapter(adapterhdxx);
        recyclerView.setNestedScrollingEnabled(false);


        return view;
    }

    //活动详情recyclerview
    private void inithdxx(){
        hdxx madada=new hdxx("这是标题","这是新闻的文字嗷哦嗷嗷",R.drawable.textimage);
        hdxxList.add(madada);
        hdxx apple=new hdxx("这是标题","这是新闻的文字嗷哦嗷嗷啊",R.drawable.textimage);
        hdxxList.add(apple);
        hdxx apple1=new hdxx("这是标题","这是新闻的文字嗷嗷嗷嗷",R.drawable.textimage);
        hdxxList.add(apple1);
    }
}
