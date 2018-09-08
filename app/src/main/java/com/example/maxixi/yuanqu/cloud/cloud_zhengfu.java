package com.example.maxixi.yuanqu.cloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_adapter;
import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_lei;

import java.util.ArrayList;
import java.util.List;

public class cloud_zhengfu extends AppCompatActivity {


    private List<cloud_zhidao_lei> zhidaoList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_zhengfu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_zhengfu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initzhidaoList();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.cloud_zhengfu_recycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        cloud_zhidao_adapter cloud_zhidao_adapter=new cloud_zhidao_adapter(zhidaoList);
        recyclerView.setAdapter(cloud_zhidao_adapter);


    }

    private void initzhidaoList() {
        cloud_zhidao_lei madada=new cloud_zhidao_lei("政府大事件","2018-08");
        zhidaoList.add(madada);
        cloud_zhidao_lei madada1=new cloud_zhidao_lei("政府大事件","2018-08");
        zhidaoList.add(madada1);
        cloud_zhidao_lei madada2=new cloud_zhidao_lei("政府大事件","2018-08");
        zhidaoList.add(madada2);

    }
}
