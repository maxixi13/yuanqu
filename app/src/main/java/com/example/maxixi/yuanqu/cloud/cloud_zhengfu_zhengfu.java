package com.example.maxixi.yuanqu.cloud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class cloud_zhengfu_zhengfu extends AppCompatActivity {


    private String lid;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_zhengfu_zhengfu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_zhengfu_zhengfu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        lid = intent.getStringExtra("lid");
        title = intent.getStringExtra("title");

        TextView shengqing = (TextView) findViewById(R.id.cloud_zhengfu_zhengfu_shenqing);
        shengqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cloud_zhengfu_zhengfu.this, cloud_zhengfu_zhengfu_shenqing.class);
                intent.putExtra("lid", lid);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });

        String postdata = "id=" + lid;
        WebView webView = (WebView) findViewById(R.id.cloud_zhengfu_zhuangfu_web);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.postUrl(getString(R.string.zhengfuzhixunxiangqing_url), postdata.getBytes());

    }
}
