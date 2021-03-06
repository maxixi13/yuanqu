package com.example.maxixi.yuanqu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.cloud.cloud_chuangye;
import com.example.maxixi.yuanqu.cloud.cloud_jinrong;
import com.example.maxixi.yuanqu.cloud.qiyefuwu.cloud_qiyefuwu;
import com.example.maxixi.yuanqu.cloud.cloud_zhengfu;
import com.example.maxixi.yuanqu.cloud.cloud_zuling;

public class Fragmentcloud extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.cctivity_cloud, container, false);

        LinearLayout zhengfuzixun=(LinearLayout)view.findViewById(R.id.cloud_zhengfu);
        zhengfuzixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),cloud_zhengfu.class);
                startActivity(intent);
            }
        });

        LinearLayout jinrongfuwu=(LinearLayout)view.findViewById(R.id.cloud_jinrong);
        jinrongfuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),cloud_jinrong.class);
                startActivity(intent);
            }
        });

        LinearLayout qiyezuling = (LinearLayout) view.findViewById(R.id.cloud_zuling);
        qiyezuling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),cloud_zuling.class);
                startActivity(intent);
            }
        });

        LinearLayout chuangyefuwu = (LinearLayout) view.findViewById(R.id.cloud_chuangye);
        chuangyefuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), cloud_chuangye.class);
                startActivity(intent);
            }
        });

        LinearLayout qiyefuwu=(LinearLayout)view.findViewById(R.id.cloud_fuwu);
        qiyefuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),cloud_qiyefuwu.class);
                startActivity(intent);
            }
        });

        return view;
    }
}