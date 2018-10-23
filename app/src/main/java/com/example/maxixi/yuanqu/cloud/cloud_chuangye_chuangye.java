package com.example.maxixi.yuanqu.cloud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class cloud_chuangye_chuangye extends AppCompatActivity {

    private String lid;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_chuangye_chuangye);

        Intent intent = getIntent();
        lid = intent.getStringExtra("lid");
        title = intent.getStringExtra("title");

        //申请页面
        TextView shenqingText = (TextView) findViewById(R.id.cloud_chuangye_chuangye_shenqing);
        shenqingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cloud_chuangye_chuangye.this, cloud_chuangye_shenqing.class);
                intent.putExtra("lid", lid);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_chuangye_chuangye_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String postdata = "id=" + lid;
        WebView webView=(WebView)findViewById(R.id.chuangye_chuangye_web);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.postUrl(getString(R.string.chuangyefuwuxiangqing_url),postdata.getBytes());
    }
}
