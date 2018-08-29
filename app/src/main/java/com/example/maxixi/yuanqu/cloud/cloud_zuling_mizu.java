package com.example.maxixi.yuanqu.cloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

import static android.webkit.WebSettings.LayoutAlgorithm.SINGLE_COLUMN;

public class cloud_zuling_mizu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_zuling_mizu);

        Toolbar toolbar=(Toolbar)findViewById(R.id.cloud_zuling_mizu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        WebView webView=(WebView)findViewById(R.id.webview_mizujie);
        webView.loadUrl("http://www.mzujie.com");
        //String date= getIntent().getStringExtra("data");
        //Log.i("FragmentActivity", date);
        WebSettings webSettings=webView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        //webView.loadUrl(date);


        }
}
