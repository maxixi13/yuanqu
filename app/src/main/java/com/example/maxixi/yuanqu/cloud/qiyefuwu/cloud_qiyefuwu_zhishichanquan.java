package com.example.maxixi.yuanqu.cloud.qiyefuwu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class cloud_qiyefuwu_zhishichanquan extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Cloud_qiyefuwu_bean> qiyefuwulist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_qiyefuwu_zhishichanquan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_qiyefuwu_zhishichanquan_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        RelativeLayout first_zhishichanquan=(RelativeLayout)findViewById(R.id.first_zhishichanquan);
//        first_zhishichanquan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(cloud_qiyefuwu_zhishichanquan.this,cloud_qiyefuwu_zhishichuanquanSQ.class);
//                startActivity(intent);
//            }
//        });

        recyclerView = (RecyclerView)findViewById(R.id.cloud_qiyefuwu_zhishichanquan_recycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SendOkhttp(0);
    }

    private int code;
    private int page=0;
    private void SendOkhttp(int page_value){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient=new OkHttpClient();
                    FormBody formBody=new FormBody.Builder().add("type","2").add("page",String.valueOf(page)).build();
                    Request request=new Request.Builder().url(getString(R.string.qiyefuwu_url)).post(formBody).build();
                    Response response = okHttpClient.newCall(request).execute();
                    String responseData=response.body().string();
                    JSONObject jsonObject=new JSONObject(responseData);
                    code = jsonObject.getInt("code");
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    for (int i=0;i<jsonArray.length();++i){
                        JSONObject jsonObjectcl=jsonArray.getJSONObject(i);
                        Cloud_qiyefuwu_bean bean=new Cloud_qiyefuwu_bean(R.drawable.qiyefuwu_zhishichanquan_icon,jsonObjectcl.getString("qid"),"平均费用"+jsonObjectcl.getString("average_cost")+"元","园区使用频率"+jsonObjectcl.getString("park_use_rate")+"%","平均申请时间"+jsonObjectcl.getString("average_time")+"天",jsonObjectcl.getString("lid"));
                        qiyefuwulist.add(bean);
                    }
                    final Cloud_qiyefuwu_adapter cloud_qiyefuwu_adapter=new Cloud_qiyefuwu_adapter(qiyefuwulist);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.setAdapter(cloud_qiyefuwu_adapter);
                            cloud_qiyefuwu_adapter.setmOnItemClickListener(new Cloud_qiyefuwu_adapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Intent intent=new Intent(cloud_qiyefuwu_zhishichanquan.this,cloud_qiyefuwu_gongshangzhuceSQ.class);
                                    intent.putExtra("lid",qiyefuwulist.get(position).getLid());
                                    intent.putExtra("title",qiyefuwulist.get(position).getQid());
                                    intent.putExtra("toolbartitle","知识产权");
                                    startActivity(intent);
                                }
                            });
                        }
                    });
//                    if (code != -1 && code !=201){
//                        SendOkhttp(++page);
//                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
