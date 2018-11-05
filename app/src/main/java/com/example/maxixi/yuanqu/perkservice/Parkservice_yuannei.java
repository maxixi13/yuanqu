package com.example.maxixi.yuanqu.perkservice;


import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class Parkservice_yuannei extends AppCompatActivity {


    //toolbar设定
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bctivity_parkservice_yuannei);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_park_yuanei);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton wuyezixun=(ImageButton)findViewById(R.id.yuannei_wuyezixun);
        wuyezixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Parkservice_yuannei.this,parksevice_yuanei_zixun.class);
                startActivity(intent);
            }
        });

        ImageButton tousujianyi=(ImageButton)findViewById(R.id.yuannei_tousujianyi);
        tousujianyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Parkservice_yuannei.this,parkservice_yuanei_tousu.class);
                startActivity(intent);
            }
        });

        ImageButton wuyebaoxiu=(ImageButton)
                findViewById(R.id.yuannei_wuyebaoxiu);
        wuyebaoxiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Parkservice_yuannei.this,parkservice_yuannei_baoxiu.class);
                startActivity(intent);
            }
        });

        ImageButton yuanqujieshao=(ImageButton)findViewById(R.id.yuanei_yuanqujieshao);
        yuanqujieshao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Parkservice_yuannei.this,parkservice_yuannei_yuanqujieshao.class);
                startActivity(intent);
            }
        });
    }
}
