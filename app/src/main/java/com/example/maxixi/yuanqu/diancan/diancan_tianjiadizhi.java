package com.example.maxixi.yuanqu.diancan;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

public class diancan_tianjiadizhi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ectivity_diancan_tianjiadizhi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.diancan_tianjiadizhi_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final ImageView shiView = (ImageView) findViewById(R.id.diancan_tianjiadizhi_shi);
        final ImageView fouView = (ImageView) findViewById(R.id.diancan_tianjiadizhi_fou);
        shiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shiView.setSelected(true);
                fouView.setSelected(false);
            }
        });
        fouView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fouView.setSelected(true);
                shiView.setSelected(false);
            }
        });

        Upload();

    }

    private void Upload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("uid", "2");
                    jsonObject.put("name", "dada");
                    jsonObject.put("tel", "1234567");
                    jsonObject.put("address","hahah");
                    jsonObject.put("status", "1");
                    Log.e("---", String.valueOf(jsonObject));

                    OkHttpClient okHttpClient=new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
                    Request request=new Request.Builder().url("http://192.168.11.165/index/Menu/doaddress").post(formBody).build();

//                    //申明给服务端传递一个json串
//                    //创建一个OkHttpClient对象
//                    OkHttpClient okHttpClient = new OkHttpClient();
//                    //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
//                    //json为String类型的json数据
//                    RequestBody requestBody = RequestBody.create(JSON, json);
//                    //创建一个请求对象
//                    Request request = new Request.Builder()
//                            .url("http://192.168.0.102:8080/TestProject/JsonServlet")
//                            .post(requestBody)
//                            .build();
//                    //发送请求获取响应
//                    try {
//                        Response response=okHttpClient.newCall(request).execute();
//                        //判断请求是否成功
//                        if(response.isSuccessful()){\
//                            //打印服务端返回结果
//                            Log.i(TAG,response.body().string());
//
//                        }

                    try {
                        Response response=okHttpClient.newCall(request).execute();
                        //判断请求是否成功
                        if(response.isSuccessful()){
                            //打印服务端返回结果
                            Log.i("------",response.body().string());

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
