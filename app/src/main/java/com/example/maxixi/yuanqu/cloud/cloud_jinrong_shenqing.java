package com.example.maxixi.yuanqu.cloud;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class cloud_jinrong_shenqing extends AppCompatActivity {

    private TextView textView;
    private TextView textView1;
    private EditText gongsimingcheng;
    private EditText lianxiren;
    private EditText liangxidianhua;
    private String lid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_jinrong_shenqing);

        //view
        textView = (TextView) findViewById(R.id.jinrong_jinrong_shengqing_fuwu_text);
        textView1 = (TextView) findViewById(R.id.jinrong_jinrong_shengqing_yuanqu_text);
        gongsimingcheng = (EditText)findViewById(R.id.jinrong_jinrong_shengqing_gongsimingcheng_text);
        lianxiren = (EditText)findViewById(R.id.jinrong_jinrong_shengqing_lianxiren_text);
        liangxidianhua = (EditText)findViewById(R.id.jinrong_jinrong_shengqing_lianxidianhua_text);

        Intent intent = getIntent();
        lid = intent.getStringExtra("lid");

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_jinrong_shenqing_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //园区
        LinearLayout yuanquxuanzelayout = (LinearLayout) findViewById(R.id.jinrong_jinrong_shengqing_yuanqu_layout);
        yuanquxuanzelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog1();
            }
        });

        Button tijiaobutton = (Button) findViewById(R.id.jinrong_jinrong_shengqing_tijiao_button);
        tijiaobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView1.getText().toString().length()!=0 && gongsimingcheng.getText().toString().length()!=0 && lianxiren.getText().toString().length()!=0 && liangxidianhua.getText().toString().length()!=0 ){
                    Upload();
                }else {
                    Toast.makeText(cloud_jinrong_shenqing.this, "请填写完整", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sendOkhttp();

    }

    private void Upload() {
        SharedPreferences sharedPreferences = getSharedPreferences("userdata", Context.MODE_PRIVATE);
        final String uid = sharedPreferences.getString("uid", "null");
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("uid", uid);
                    jsonObject.put("sid", lid);
                    jsonObject.put("stay_park", textView1.getText());
                    jsonObject.put("stay_company", gongsimingcheng.getText());
                    jsonObject.put("name", lianxiren.getText());
                    jsonObject.put("phone", liangxidianhua.getText());
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
                    Request request = new Request.Builder().url(getString(R.string.jinrongfuwushenqing_url)).post(requestBody).build();
                    try {
                        Response response = okHttpClient.newCall(request).execute();
                        //判断请求是否成功
                        if (response.isSuccessful()) {
                            //打印服务端返回结果
                            Thread.sleep(1000);
                            String responseData=response.body().string();
                            JSONObject jsonObjectgetcode=new JSONObject(responseData);
                            String code=jsonObjectgetcode.getString("code");
                            if (code.equals("200")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(cloud_jinrong_shenqing.this,"提交成功",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                cloud_jinrong_shenqing.this.finish();
                            }else {
                                Toast.makeText(cloud_jinrong_shenqing.this,"提交失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    //园区
    private void Dialog1() {
        //初始化字符串数组
        final String[] strArray = new String[]{"芈展服务园"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);//实例化builder
        builder.setTitle("请选择所在园区");//设置标题
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

    private void sendOkhttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder().add("lid",lid).build();
                Request request=new Request.Builder().url(getString(R.string.fanhuijinrongfuwubiaoti_url)).post(formBody).build();
                try {
                    Response response=okHttpClient.newCall(request).execute();
                    String responseData=response.body().string();
                    JSONObject jsonObject=new JSONObject(responseData);
                    textView.setText(jsonObject.getString("data"));
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
