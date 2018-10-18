package com.example.maxixi.yuanqu.diancan;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class diancan_tianjiadizhi extends AppCompatActivity {

    private EditText lianxiren;
    private EditText lianxidianhua;
    private EditText dizhi;

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

        //view
        lianxiren = (EditText) findViewById(R.id.tinajia_lianxiren_edit);
        lianxidianhua = (EditText) findViewById(R.id.tinajia_lianxidianhua_edit);
        dizhi = (EditText) findViewById(R.id.tinajia_xiangxidizhi_edit);

        Button baocunButton = (Button) findViewById(R.id.tianjia_baocun);
        baocunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lianxiren.getText().toString().length() != 0 && lianxidianhua.getText().toString().length() != 0 && dizhi.getText().toString().length() != 0) {
                    if (shiView.isSelected() || fouView.isSelected()) {
                        Upload();
                    } else {
                        Toast.makeText(diancan_tianjiadizhi.this, "请选择是否设置默认地址", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(diancan_tianjiadizhi.this, "请填写完整填写", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void Upload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("uid", "1");
                    jsonObject.put("name", lianxiren.getText());
                    jsonObject.put("tel", lianxidianhua.getText());
                    jsonObject.put("address", dizhi.getText());
                    jsonObject.put("status", "1");
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
                    Request request = new Request.Builder().url(getString(R.string.tianjiawaimaidizhi_url)).post(requestBody).build();

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
