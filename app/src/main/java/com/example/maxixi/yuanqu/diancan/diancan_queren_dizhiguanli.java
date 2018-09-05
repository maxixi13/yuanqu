package com.example.maxixi.yuanqu.diancan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.diancan.adapter.Dizhiguanliadapter;
import com.example.maxixi.yuanqu.diancan.model.Dizhiguanlilei;

import java.util.ArrayList;
import java.util.List;

public class diancan_queren_dizhiguanli extends AppCompatActivity {

    private List<Dizhiguanlilei> dizhiguanlileiList=new ArrayList<>();

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

        initdizhiguanliList();
        RecyclerView recyclerViewzcxx = (RecyclerView) findViewById(R.id.diancan_queren_dizhiguanli_recycler);
        LinearLayoutManager layoutManagerzcxx = new LinearLayoutManager(this);
        recyclerViewzcxx.setLayoutManager(layoutManagerzcxx);
        Dizhiguanliadapter dizhiguanliadapter = new Dizhiguanliadapter(dizhiguanlileiList);
        recyclerViewzcxx.setAdapter(dizhiguanliadapter);

        LinearLayout tianjiadizhi=(LinearLayout)findViewById(R.id.diancan_queren_dizhiguanli_tianjiadizhi);
        tianjiadizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(diancan_queren_dizhiguanli.this,diancan_tianjiadizhi.class);
                startActivity(intent);
            }
        });
    }


    private void initdizhiguanliList(){
        Dizhiguanlilei apple=new Dizhiguanlilei("王大姐","13888888888","上海市浦东新区唐镇B栋");
        dizhiguanlileiList.add(apple);
        Dizhiguanlilei apple1=new Dizhiguanlilei("王大姐","13888888888","上海市浦东新区唐镇B栋");
        dizhiguanlileiList.add(apple1);
        Dizhiguanlilei apple2=new Dizhiguanlilei("王大姐","13888888888","上海市浦东新区唐镇B栋");
        dizhiguanlileiList.add(apple2);
    }
}
