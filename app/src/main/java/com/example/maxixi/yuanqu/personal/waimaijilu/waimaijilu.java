package com.example.maxixi.yuanqu.personal.waimaijilu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.diancan.adapter.QuerenAdapter;

import java.util.ArrayList;
import java.util.List;

public class waimaijilu extends AppCompatActivity {
    private List<Caipinfubean> caipinfubeanList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_waimaijilu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.waimaijilu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initList();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.waimaijilu_recycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(waimaijilu.this);
        recyclerView.setLayoutManager(layoutManager);
        CaipinfuAdapter caipinfuAdapter=new CaipinfuAdapter(waimaijilu.this,caipinfubeanList);
        recyclerView.setAdapter(caipinfuAdapter);

    }

    private void initList(){
        List<Caipinbean> caipinbeanList=new ArrayList<>();
        Caipinbean caipinbean=new Caipinbean(R.drawable.texttouxiang,"mingzi","123","999");
        caipinbeanList.add(caipinbean);
        caipinbeanList.add(caipinbean);
        caipinbeanList.add(caipinbean);
        caipinbeanList.add(caipinbean);
        caipinbeanList.add(caipinbean);
        Caipinfubean caipinfubean=new Caipinfubean("芈展芈展芈展",caipinbeanList);
        caipinfubeanList.add(caipinfubean);

        List<Caipinbean> caipinbeanList1=new ArrayList<>();
        Caipinbean caipinbean1=new Caipinbean(R.drawable.texttouxiang,"dadada","312","123");
        caipinbeanList1.add(caipinbean1);
        caipinbeanList1.add(caipinbean1);
        caipinbeanList1.add(caipinbean1);
        caipinbeanList1.add(caipinbean1);
        caipinbeanList1.add(caipinbean1);
        Caipinfubean caipinfubean1=new Caipinfubean("芈展芈展芈展",caipinbeanList1);
        caipinfubeanList.add(caipinfubean1);
    }

}