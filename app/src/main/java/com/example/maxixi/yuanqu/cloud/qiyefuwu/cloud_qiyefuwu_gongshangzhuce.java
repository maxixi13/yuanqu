package com.example.maxixi.yuanqu.cloud.qiyefuwu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

import java.util.ArrayList;
import java.util.List;

public class cloud_qiyefuwu_gongshangzhuce extends AppCompatActivity {
    private List<Cloud_qiyefuwu_bean> qiyefuwulist=new ArrayList<>();

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

//        RelativeLayout first_gongshangzhuce=(RelativeLayout)findViewById(R.id.first_gongshangzhuce);
//        first_gongshangzhuce.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(cloud_qiyefuwu_gongshangzhuce.this,cloud_qiyefuwu_gongshangzhuceSQ.class);
//                startActivity(intent);
//            }
//        });

        initList();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.cloud_qiyefuwu_gongshangzhuce_recycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Cloud_qiyefuwu_adapter cloud_qiyefuwu_adapter=new Cloud_qiyefuwu_adapter(qiyefuwulist);
        recyclerView.setAdapter(cloud_qiyefuwu_adapter);
    }

    private void initList() {
        Cloud_qiyefuwu_bean madada=new Cloud_qiyefuwu_bean(R.drawable.qiyefuwu_gongshangzhuce_icon,"XXXXX工商注册","125","454421","123124");
        qiyefuwulist.add(madada);
        Cloud_qiyefuwu_bean madada1=new Cloud_qiyefuwu_bean(R.drawable.qiyefuwu_gongshangzhuce_icon,"XXXXX工商注册","125","454421","123124");
        qiyefuwulist.add(madada1);
        Cloud_qiyefuwu_bean madada2=new Cloud_qiyefuwu_bean(R.drawable.qiyefuwu_gongshangzhuce_icon,"XXXXX工商注册","125","454421","123124");
        qiyefuwulist.add(madada2);
        Cloud_qiyefuwu_bean madada3=new Cloud_qiyefuwu_bean(R.drawable.qiyefuwu_gongshangzhuce_icon,"XXXXX工商注册","125","454421","123124");
        qiyefuwulist.add(madada3);
        Cloud_qiyefuwu_bean madada4=new Cloud_qiyefuwu_bean(R.drawable.qiyefuwu_gongshangzhuce_icon,"XXXXX工商注册","125","454421","123124");
        qiyefuwulist.add(madada4);
    }
}
