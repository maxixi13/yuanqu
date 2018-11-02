package com.example.maxixi.yuanqu.perkservice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class parkservice_yuannei_baoxiu extends AppCompatActivity {

    private TextView textView;
    private TextView textView1;
    private EditText bangongquyu;
    private EditText gongsimingcheng;
    private EditText baoxiuduixiang;
    private EditText liangxiren;
    private EditText lianxidianhua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bctivity_parkservice_yuannei_baoxiu);

        //view
        textView = (TextView) findViewById(R.id.baoxiu_yuanquxuanze_textview);
        bangongquyu = (EditText) findViewById(R.id.baoxiu_bangongquyu_textview);
        gongsimingcheng = (EditText) findViewById(R.id.baoxiu_gongsimingcheng_textview);
        textView1 = (TextView) findViewById(R.id.baoxiu_baoxiuleixing_textview);
        baoxiuduixiang = (EditText) findViewById(R.id.baoxiu_baoxiuduixiang_textview);
        liangxiren = (EditText) findViewById(R.id.baoxiu_lianxiren_textview);
        lianxidianhua = (EditText) findViewById(R.id.baoxiu_lianxidianhua_textview);


        Toolbar toolbar = (Toolbar) findViewById(R.id.yuannei_baoxiu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayout yuanquxuanze = (LinearLayout) findViewById(R.id.baoxiu_yuanquxuanze_linearlayout);
        yuanquxuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });

        LinearLayout baoxiuxuanze = (LinearLayout) findViewById(R.id.baoxiu_baoxiuleixing_linearlayout);
        baoxiuxuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog1();
            }
        });

        Button tijiao = (Button) findViewById(R.id.baoxiu_tijiao_button);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView.getText().toString().length() != 0 && bangongquyu.getText().toString().length() != 0 && gongsimingcheng.getText().toString().length() != 0 && textView1.getText().toString().length() != 0 && baoxiuduixiang.getText().toString().length() != 0 && liangxiren.getText().toString().length() != 0 && lianxidianhua.getText().toString().length() != 0) {
                    Upload();
                } else {
                    Toast.makeText(parkservice_yuannei_baoxiu.this, "请填写完整", Toast.LENGTH_SHORT).show();
                }
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url(getString(R.string.baoxiuleixing_url)).build();
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
                            //初始化字符串数组
                            final AlertDialog.Builder builder = new AlertDialog.Builder(parkservice_yuannei_baoxiu.this);//实例化builder
                            builder.setTitle("请选择报修类型");//设置标题
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
                            AlertDialog dialog = builder.create();
                            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();//显示对话框
                        }
                    });
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void Upload() {
        SharedPreferences sharedPreferences=getSharedPreferences("userdata",Context.MODE_PRIVATE);
        final String uid=sharedPreferences.getString("uid","null");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("uid", uid);
                    jsonObject.put("stay_park", textView.getText());
                    jsonObject.put("region", bangongquyu.getText());
                    jsonObject.put("stay_company", gongsimingcheng.getText());
                    jsonObject.put("rid", textView1.getText());
                    jsonObject.put("obj", baoxiuduixiang.getText());
                    jsonObject.put("name", liangxiren.getText());
                    jsonObject.put("tel", lianxidianhua.getText());
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
                    Request request = new Request.Builder().url(getString(R.string.tianjiabaoxiu_url)).post(requestBody).build();
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
