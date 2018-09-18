package com.example.maxixi.yuanqu.cloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_adapter;
import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_lei;

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


    private List<cloud_zhidao_lei> zhidaoList=new ArrayList<>();
    private RecyclerView recyclerView;
    private int a=1;


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


        recyclerView=(RecyclerView)findViewById(R.id.cloud_zhengfu_recycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        sendRequestWithOkHttp(0);
        Log.e("-----------------------","aa"+lid);





    }

private int lid;
    private  int page=0;

    private void sendRequestWithOkHttp(final int page_value) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody formBody=new FormBody.Builder().add("type","2").add("page",String.valueOf(page)).build();
                    Request request = new Request.Builder().url("http://192.168.11.121/index/Consultationdetails/finance_list").post(formBody).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        lid=jsonObject.getInt("code");
                        JSONArray array = jsonObject.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObjectchil = array.getJSONObject(i);
                            cloud_zhidao_lei madada = new cloud_zhidao_lei(jsonObjectchil.getString("title"), jsonObjectchil.getString("ctime"));
                            zhidaoList.add(madada);
                            final cloud_zhidao_adapter cloud_zhidao_adapter = new cloud_zhidao_adapter(zhidaoList);

                           cloud_zhengfu.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (lid != -1 && lid!=201){
                                        sendRequestWithOkHttp(++page);
                                    }
                                    recyclerView.setAdapter(cloud_zhidao_adapter);
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
