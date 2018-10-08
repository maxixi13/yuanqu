package com.example.maxixi.yuanqu.perkservice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class parkservice_yuanei_tousu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bctivity_parkservice_yuanei_tousu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.yuannei_tousu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        LinearLayout yuanquxuanze=(LinearLayout)findViewById(R.id.tousu_yuanquxuanze_linearlayout);
        yuanquxuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });

        LinearLayout tousuleixing=(LinearLayout)findViewById(R.id.tousu_tousuleixing_linearlayout);
        tousuleixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog1();
            }
        });
    }


    //园区选择dialog
    private void Dialog() {
        //初始化字符串数组
        final String[] strArray = new String[]{"芈展服务园", "上海园区", "浙江园区", "北京园区", "广东园区"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);//实例化builder
        builder.setTitle("请选择所在园区");//设置标题
        //设置单选列表
        builder.setSingleChoiceItems(strArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView = (TextView) findViewById(R.id.tousu_yuanquxuanze_textview);
                textView.setText(strArray[which]);
                textView.setTextColor(Color.parseColor("#666666"));
                dialog.dismiss();
            }
        });
        //创建对话框
        AlertDialog dialog = builder.create();
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();//显示对话框
    }


    //投诉dialog
    private void Dialog1() {
        //初始化字符串数组
        final String[] strArray = new String[]{"服务态度问题", "服务质量问题", "投诉", "投诉", "投诉"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);//实例化builder
        builder.setTitle("请选择投诉类型");//设置标题
        //设置单选列表
        builder.setSingleChoiceItems(strArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView = (TextView) findViewById(R.id.tousu_tousuleixing_textview);
                textView.setText(strArray[which]);
                textView.setTextColor(Color.parseColor("#666666"));
                dialog.dismiss();
            }
        });
        //创建对话框
        AlertDialog dialog = builder.create();
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();//显示对话框
    }
}
