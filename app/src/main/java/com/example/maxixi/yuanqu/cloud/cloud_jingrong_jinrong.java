package com.example.maxixi.yuanqu.cloud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class cloud_jingrong_jinrong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_jingrong_jinrong);


        //申请页面
        TextView shenqingText = (TextView) findViewById(R.id.cloud_jinrong_jinrong_shenqing);
        shenqingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shengqingpage = new Intent(cloud_jingrong_jinrong.this, cloud_jinrong_shenqing.class);
                startActivity(shengqingpage);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_jinrong_jinrong_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        WebView webView=(WebView)findViewById(R.id.jinrong_jingrong_web);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl(getIntent().getStringExtra("baidu"));


    }
}
