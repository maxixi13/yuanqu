package com.example.maxixi.yuanqu.cloud.qiyefuwu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class cloud_qiyefuwu_shejiyinshuaSQ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_qiyefuwu_shejiyinshua_sq);

        Toolbar toolbar=(Toolbar)findViewById(R.id.shejiyinshua_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
