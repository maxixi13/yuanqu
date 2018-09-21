package com.example.maxixi.yuanqu.personal;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;


public class renzhengqiye extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_renzhengqiye);

        Toolbar toolbar = (Toolbar) findViewById(R.id.personal_renzhengqiye_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView upimg = (ImageView) findViewById(R.id.personal_renzheng_upimgview);
        upimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dialog();

            }
        });
    }
}



//https://www.jianshu.com/p/26d29e187f89

//https://www.jianshu.com/p/26d29e187f89