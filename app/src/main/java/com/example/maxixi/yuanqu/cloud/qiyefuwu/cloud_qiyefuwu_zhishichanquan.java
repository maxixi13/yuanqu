package com.example.maxixi.yuanqu.cloud.qiyefuwu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class cloud_qiyefuwu_zhishichanquan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_qiyefuwu_zhishichanquan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_qiyefuwu_zhishichanquan_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RelativeLayout first_zhishichanquan=(RelativeLayout)findViewById(R.id.first_zhishichanquan);
        first_zhishichanquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cloud_qiyefuwu_zhishichanquan.this,cloud_qiyefuwu_zhishichuanquanSQ.class);
                startActivity(intent);
            }
        });
    }
}
