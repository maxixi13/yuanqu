package com.example.maxixi.yuanqu.diancan;


import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

import com.example.maxixi.yuanqu.diancan.adapter.querendingdanAdapter;
import com.example.maxixi.yuanqu.diancan.model.querendingdanlei;


import java.util.ArrayList;
import java.util.List;

public class diancan_queren extends AppCompatActivity {

    private List<querendingdanlei> querendingdan = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ectivity_diancan_queren);

        Toolbar toolbar = (Toolbar) findViewById(R.id.diancan_queren_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView zhifuText=(TextView)findViewById(R.id.queren_zhifufangshiText);
        zhifuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });

        initquerendingdan();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.qurendingdan_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        querendingdanAdapter dishAdapter = new querendingdanAdapter(querendingdan);
        recyclerView.setAdapter(dishAdapter);


    }

    private void Dialog(){
        final String[] strArray=new String[]{"微信","支付宝"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("请选择支付方式");
        builder.setSingleChoiceItems(strArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView=(TextView)findViewById(R.id.queren_zhifufangshiText);
                textView.setText(strArray[which]);
                textView.setTextColor(Color.parseColor("#666666"));
                dialog.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    private void initquerendingdan(){
        querendingdanlei dish1=new querendingdanlei(R.drawable.textkele,"西兰花","1","20");
        querendingdan.add(dish1);
        querendingdanlei dish2=new querendingdanlei(R.drawable.textkele,"西兰花","1","20");
        querendingdan.add(dish2);
        querendingdanlei dish3=new querendingdanlei(R.drawable.textkele,"西兰花","1","20");
        querendingdan.add(dish3);
    }
}
