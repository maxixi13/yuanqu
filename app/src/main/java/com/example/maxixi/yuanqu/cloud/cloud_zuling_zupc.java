package com.example.maxixi.yuanqu.cloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class cloud_zuling_zupc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_zuling_zupc);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_zuling_zupc_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        WebView zupc = (WebView) findViewById(R.id.webview_zupc);
        zupc.loadUrl("https://www.baidu.com");
        //WebSettings zupcSetting=zupc.getSettings();
        // zupcSetting.set
    }
}
