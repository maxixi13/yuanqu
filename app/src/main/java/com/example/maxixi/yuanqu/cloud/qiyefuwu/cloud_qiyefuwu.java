package com.example.maxixi.yuanqu.cloud.qiyefuwu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class cloud_qiyefuwu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_qiyefuwu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_qiyefuwu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayout gongshangzhuce=(LinearLayout)findViewById(R.id.qiyefuwu_gongshangzhuce);
        gongshangzhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cloud_qiyefuwu.this,cloud_qiyefuwu_gongshangzhuce.class);
                startActivity(intent);
            }
        });

        LinearLayout zhishichanquan=(LinearLayout)findViewById(R.id.qiyefuwu_zhishichanquan);
        zhishichanquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cloud_qiyefuwu.this,cloud_qiyefuwu_zhishichanquan.class);
                startActivity(intent);
            }
        });
        LinearLayout shejiyinshua=(LinearLayout)findViewById(R.id.qiyefuwu_shejiyinshua);
        shejiyinshua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cloud_qiyefuwu.this,cloud_qiyefuwu_shejiyinshua.class);
                startActivity(intent);
            }
        });
        LinearLayout caiwudaili=(LinearLayout)findViewById(R.id.qiyefuwu_caiwudaili);
        caiwudaili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cloud_qiyefuwu.this,cloud_qiyefuwu_caiwudaili.class);
                startActivity(intent);
            }
        });
    }
}
