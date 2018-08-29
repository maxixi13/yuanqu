package com.example.maxixi.yuanqu.cloud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class cloud_chuangye_chuangye extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_chuangye_chuangye);

        //申请页面
        TextView shenqingText=(TextView)findViewById(R.id.cloud_chuangye_chuangye_shenqing);
        shenqingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shengqingpage=new Intent(cloud_chuangye_chuangye.this,cloud_chuangye_shenqing.class);
                startActivity(shengqingpage);
            }
        });

        Toolbar toolbar=(Toolbar)findViewById(R.id.cloud_chuangye_chuangye_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
