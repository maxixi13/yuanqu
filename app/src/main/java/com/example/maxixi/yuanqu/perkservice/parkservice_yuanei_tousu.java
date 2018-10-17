package com.example.maxixi.yuanqu.perkservice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.icu.lang.UProperty;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class parkservice_yuanei_tousu extends AppCompatActivity {

    private EditText bangongquyu;
    private EditText gongsimingcheng;
    private EditText tousuduixiang;
    private EditText liangxiren;
    private EditText liangxidianhua;
    private TextView textView;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bctivity_parkservice_yuanei_tousu);

        //view
        textView = (TextView) findViewById(R.id.tousu_yuanquxuanze_textview);
        bangongquyu = (EditText) findViewById(R.id.tousu_bangongquyu_textview);
        gongsimingcheng = (EditText) findViewById(R.id.tousu_gongsimingcheng_textview);
        textView1 = (TextView) findViewById(R.id.tousu_tousuleixing_textview);
        tousuduixiang = (EditText) findViewById(R.id.tousu_tousuduixiang_textview);
        liangxiren = (EditText) findViewById(R.id.tousu_lianxiren_textview);
        liangxidianhua = (EditText) findViewById(R.id.tousu_lianxidianhua_textview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.yuannei_tousu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        LinearLayout yuanquxuanze = (LinearLayout) findViewById(R.id.tousu_yuanquxuanze_linearlayout);
        yuanquxuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });

        LinearLayout tousuleixing = (LinearLayout) findViewById(R.id.tousu_tousuleixing_linearlayout);
        tousuleixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog1();
            }
        });

        Button tijiao = (Button) findViewById(R.id.tousu_tijiao_button);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView.getText().toString().length() != 0 && bangongquyu.getText().toString().length() != 0 && gongsimingcheng.getText().toString().length() != 0 && textView1.getText().toString().length() != 0 && tousuduixiang.getText().toString().length() != 0 && liangxiren.getText().toString().length() != 0 && liangxidianhua.getText().toString().length() != 0) {
                    Upload();
                } else {
                    Toast.makeText(parkservice_yuanei_tousu.this, "请填写完整", Toast.LENGTH_SHORT).show();
                }
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url("http://192.168.11.165/index/Property/complaint_type").build();
                    Response response = okHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    List<String> str = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); ++i) {
                        JSONObject jsonObjectcl = jsonArray.getJSONObject(i);
                        str.add(jsonObjectcl.getString("type_name"));
                    }
                    final String[] strArray = str.toArray(new String[str.size()]);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(parkservice_yuanei_tousu.this);//实例化builder
                            builder.setTitle("请选择投诉类型");//设置标题
                            //设置单选列表
                            builder.setSingleChoiceItems(strArray, 0, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    textView1.setText(strArray[which]);
                                    textView1.setTextColor(Color.parseColor("#666666"));
                                    dialog.dismiss();
                                }
                            });
                            //创建对话框
                            final AlertDialog dialog = builder.create();
                            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();//显示对话框
                        }
                    });
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void Upload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("uid", "1");
                    jsonObject.put("stay_park", textView.getText());
                    jsonObject.put("region", bangongquyu.getText());
                    jsonObject.put("stay_company", gongsimingcheng.getText());
                    jsonObject.put("tid", textView1.getText());
                    jsonObject.put("obj", tousuduixiang.getText());
                    jsonObject.put("name", liangxiren.getText());
                    jsonObject.put("tel", liangxidianhua.getText());
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
                    Request request = new Request.Builder().url("http://192.168.11.165/index/Property/complaint").post(requestBody).build();
                    try {
                        Response response = okHttpClient.newCall(request).execute();
                        //判断请求是否成功
                        if (response.isSuccessful()) {
                            //打印服务端返回结果
//                            Log.e("------", response.body().string());

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
