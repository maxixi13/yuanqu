package com.example.maxixi.yuanqu.diancan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class diancan_tianjiadizhi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ectivity_diancan_tianjiadizhi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.diancan_tianjiadizhi_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final ImageView shiView=(ImageView)findViewById(R.id.diancan_tianjiadizhi_shi);
        final ImageView fouView=(ImageView)findViewById(R.id.diancan_tianjiadizhi_fou);
        shiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shiView.setSelected(true);
                fouView.setSelected(false);
            }
        });
        fouView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fouView.setSelected(true);
                shiView.setSelected(false);
            }
        });

    }
}
