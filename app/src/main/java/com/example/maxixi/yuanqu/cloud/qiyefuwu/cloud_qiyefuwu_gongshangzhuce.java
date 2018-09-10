package com.example.maxixi.yuanqu.cloud.qiyefuwu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class cloud_qiyefuwu_gongshangzhuce extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_qiyefuwu_gongshangzhuce);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_qiyefuwu_gongshangzhuce_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RelativeLayout first_gongshangzhuce=(RelativeLayout)findViewById(R.id.first_gongshangzhuce);
        first_gongshangzhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cloud_qiyefuwu_gongshangzhuce.this,cloud_qiyefuwu_gongshangzhuceSQ.class);
                startActivity(intent);
            }
        });
    }
}
