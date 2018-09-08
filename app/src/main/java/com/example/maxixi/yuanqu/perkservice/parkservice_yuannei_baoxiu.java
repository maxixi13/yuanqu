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

public class parkservice_yuannei_baoxiu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bctivity_parkservice_yuannei_baoxiu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.yuannei_baoxiu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayout yuanquxuanze=(LinearLayout)findViewById(R.id.baoxiu_yuanquxuanze_linearlayout);
        yuanquxuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });

        LinearLayout baoxiuxuanze=(LinearLayout)findViewById(R.id.baoxiu_baoxiuleixing_linearlayout);
        baoxiuxuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog1();
            }
        });

    }


    //服务
    private void Dialog() {
        //初始化字符串数组
        final String[] strArray = new String[]{"芈展服务园", "离离原上草", "一岁一枯荣", "野火烧不尽", "春风吹又生"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);//实例化builder
        builder.setTitle("请选择所在园区");//设置标题
        //设置单选列表
        builder.setSingleChoiceItems(strArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView = (TextView) findViewById(R.id.baoxiu_yuanquxuanze_textview);
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


    //服务
    private void Dialog1() {
        //初始化字符串数组
        final String[] strArray = new String[]{"水管爆裂", "电路问题", "一岁一枯荣", "野火烧不尽", "春风吹又生"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);//实例化builder
        builder.setTitle("请选择报修类型");//设置标题
        //设置单选列表
        builder.setSingleChoiceItems(strArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView = (TextView) findViewById(R.id.baoxiu_baoxiuleixing_textview);
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
