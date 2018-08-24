package com.example.maxixi.yuanqu;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Toolbar;

public class Parkservice_yuannei extends AppCompatActivity {


    //toolbar设定
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bctivity_parkservice_yuannei);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_park_yuanei);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
