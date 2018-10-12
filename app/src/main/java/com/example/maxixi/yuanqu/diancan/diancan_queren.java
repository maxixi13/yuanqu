package com.example.maxixi.yuanqu.diancan;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.diancan.adapter.QuerenAdapter;
import com.example.maxixi.yuanqu.diancan.model.ShopCart;

import org.json.JSONObject;

public class diancan_queren extends AppCompatActivity {





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


        Intent intent=getIntent();
        ShopCart shopCart=(ShopCart) intent.getSerializableExtra("shopcart");
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.qurendingdan_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        QuerenAdapter dishAdapter = new QuerenAdapter(this,shopCart);
        recyclerView.setAdapter(dishAdapter);
        Log.e("---","---"+shopCart.getDishAccount()+"---"+shopCart.getShoppingAccount()+"---"+shopCart.getShoppingSingleMap()+"---"+shopCart.getShoppingTotalPrice());


        LinearLayout dizhiguanli=(LinearLayout)findViewById(R.id.querendingdan_dizhilayout);
        dizhiguanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(diancan_queren.this,diancan_queren_dizhiguanli.class);
                startActivity(intent);
            }
        });


        TextView diancan_queren_totalprice=(TextView)findViewById(R.id.diancan_queren_totalprice);
        diancan_queren_totalprice.setText("￥ "+shopCart.getShoppingTotalPrice());
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

}
