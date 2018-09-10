package com.example.maxixi.yuanqu.cloud.qiyefuwu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class cloud_qiyefuwu_shejiyinshua extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_qiyefuwu_shejiyinshua);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_qiyefuwu_shejiyinshua_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RelativeLayout first_shejiyinshua=(RelativeLayout)findViewById(R.id.first_shejiyinshua);
        first_shejiyinshua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cloud_qiyefuwu_shejiyinshua.this,cloud_qiyefuwu_shejiyinshuaSQ.class);
                startActivity(intent);
            }
        });
    }
}
