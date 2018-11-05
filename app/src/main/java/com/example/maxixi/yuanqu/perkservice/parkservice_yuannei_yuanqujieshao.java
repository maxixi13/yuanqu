package com.example.maxixi.yuanqu.perkservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class parkservice_yuannei_yuanqujieshao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bctivity_parkservice_yuannei_yuanqujieshao);

        Toolbar toolbar = (Toolbar) findViewById(R.id.yuannei_yuanqujieshao_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        WebView webView=(WebView)findViewById(R.id.yuanqujieshao_webview);
        webView.loadUrl(getString(R.string.yuanqujieshao_url));
    }
}
