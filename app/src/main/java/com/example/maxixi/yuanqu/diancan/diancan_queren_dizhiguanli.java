package com.example.maxixi.yuanqu.diancan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class diancan_queren_dizhiguanli extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ectivity_diancan_queren_dizhiguanli);

        Toolbar toolbar = (Toolbar) findViewById(R.id.diancan_queren_dizhiguanli_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
