package com.example.maxixi.yuanqu.cloud;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class cloud_chuangye_chuangye extends AppCompatActivity {

    private String lid;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_chuangye_chuangye);

        Intent intent = getIntent();
        lid = intent.getStringExtra("lid");

        //申请页面
        TextView shenqingText = (TextView) findViewById(R.id.cloud_chuangye_chuangye_shenqing);
        shenqingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlcheck=webView.getUrl();
                if (!urlcheck.contains("id=")){
                    sendOkhttp();
                }else {
                    lid=urlcheck.substring(urlcheck.indexOf("id=")+3);
                    sendOkhttp();
                }
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
        webView = (WebView)findViewById(R.id.chuangye_chuangye_web);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.postUrl(getString(R.string.chuangyefuwuxiangqing_url),postdata.getBytes());
        WebViewClient webViewClient=new WebViewClient();
        webView.setWebViewClient(webViewClient);
    }

    private void sendOkhttp(){
        SharedPreferences sharedPreferences=getSharedPreferences("userdata", Context.MODE_PRIVATE);
        final String uid = sharedPreferences.getString("uid", null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder().add("id",lid).add("uid",uid).build();
                Request request=new Request.Builder().url(getString(R.string.chuangyefuwushenqingjiance_yrl)).post(formBody).build();
                try {
                    Response response=okHttpClient.newCall(request).execute();
                    String responseData=response.body().string();
                    JSONObject jsonObject=new JSONObject(responseData);
                    String code=jsonObject.getString("code");
                    if (code.equals("202")){
                        Intent intent = new Intent(cloud_chuangye_chuangye.this, cloud_chuangye_shenqing.class);
                        intent.putExtra("lid", lid);
                        startActivity(intent);
                    }else if (code.equals("200")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(cloud_chuangye_chuangye.this,"已申请",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
