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

    private String lid;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_jingrong_jinrong);


        Intent intent = getIntent();
        lid = intent.getStringExtra("lid");
        title = intent.getStringExtra("title");

        //申请页面
        TextView shenqingText = (TextView) findViewById(R.id.cloud_jinrong_jinrong_shenqing);
        shenqingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cloud_jingrong_jinrong.this, cloud_jinrong_shenqing.class);
                intent.putExtra("lid", lid);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_jinrong_jinrong_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String postdata = "id=" + lid;
        WebView webView=(WebView)findViewById(R.id.jinrong_jingrong_web);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.postUrl(getString(R.string.jinrongfuwuxiangqing_url),postdata.getBytes());


    }
}
