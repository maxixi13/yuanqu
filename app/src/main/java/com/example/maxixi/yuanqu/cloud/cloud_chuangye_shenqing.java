package com.example.maxixi.yuanqu.cloud;

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


public class cloud_chuangye_shenqing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_chuangye_shenqing);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_chuangye_shenqing_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayout yuanquxuanzelayout = (LinearLayout) findViewById(R.id.chuangye_chuangye_shengqing_yuanqu_layout);
        yuanquxuanzelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });

    }

    private void Dialog() {
        //初始化字符串数组
        final String[] strArray = new String[]{"芈展服务园", "离离原上草", "一岁一枯荣", "野火烧不尽", "春风吹又生"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);//实例化builder
        builder.setIcon(R.mipmap.ic_launcher);//设置图标
//        builder.setTitle("请选择所在园区");//设置标题
        //设置单选列表
        builder.setSingleChoiceItems(strArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView = (TextView) findViewById(R.id.chuangye_chuangye_shengqing_yuanqu_text);
                textView.setText(strArray[which]);
                textView.setTextColor(Color.parseColor("#666666"));
            }
        });
        //创建对话框
        AlertDialog dialog = builder.create();


        //设置取消按钮
//        dialog.setButton(DialogInterface.BUTTON_NEUTRAL,"取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });

        //设置确定按钮
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();//显示对话框
    }


}