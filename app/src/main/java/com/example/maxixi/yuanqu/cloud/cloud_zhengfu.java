package com.example.maxixi.yuanqu.cloud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_adapter;
import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_lei;
import com.example.maxixi.yuanqu.personal.yunfuwushenqingfuwu.Yfuwushenqinglist;
import com.example.maxixi.yuanqu.personal.yunfuwushenqingfuwu.Yunfuwujiluadapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class cloud_zhengfu extends AppCompatActivity {


    private List<cloud_zhidao_lei> zhidaoList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_zhengfu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_zhengfu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.cloud_zhengfu_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        sendRequestWithOkHttp(0);


    }

    private int code;
    private int page = 0;

    private void sendRequestWithOkHttp(final int page_value) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody formBody = new FormBody.Builder().add("page", String.valueOf(page)).build();
                    Request request = new Request.Builder().url(getString(R.string.zhengfuzixun_url)).post(formBody).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        code = jsonObject.getInt("code");
                        JSONArray array = jsonObject.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObjectchil = array.getJSONObject(i);
                            cloud_zhidao_lei madada = new cloud_zhidao_lei(jsonObjectchil.getString("title"), jsonObjectchil.getString("ctime"),jsonObjectchil.getString("lid"));
                            zhidaoList.add(madada);
                            final cloud_zhidao_adapter cloud_zhidao_adapter = new cloud_zhidao_adapter(zhidaoList);
                            cloud_zhengfu.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setAdapter(cloud_zhidao_adapter);
                                    cloud_zhidao_adapter.setOnItemClickListener(new cloud_zhidao_adapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            Intent intent=new Intent(cloud_zhengfu.this,cloud_zhengfu_zhengfu.class);
                                            intent.putExtra("lid",zhidaoList.get(position).getLid());
                                            intent.putExtra("title",zhidaoList.get(position).getname());
                                            startActivity(intent);
                                        }
                                    });
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (code != -1 && code != 201) {
                        sendRequestWithOkHttp(++page);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
