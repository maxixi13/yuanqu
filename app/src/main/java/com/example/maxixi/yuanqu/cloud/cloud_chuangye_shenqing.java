package com.example.maxixi.yuanqu.cloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class cloud_chuangye_shenqing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_chuangye_shenqing);

        Toolbar toolbar=(Toolbar)findViewById(R.id.cloud_chuangye_shenqing_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}