package com.example.maxixi.yuanqu.diancan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.diancan.adapter.Dizhiguanliadapter;
import com.example.maxixi.yuanqu.diancan.model.Dizhiguanlilei;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class diancan_queren_dizhiguanli extends AppCompatActivity {

    private List<Dizhiguanlilei> dizhiguanlileiList = new ArrayList<>();
    private String uid;
    private RecyclerView recyclerViewzcxx;
    private String aid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ectivity_diancan_queren_dizhiguanli);

        SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.diancan_queren_dizhiguanli_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerViewzcxx = (RecyclerView) findViewById(R.id.diancan_queren_dizhiguanli_recycler);
        LinearLayoutManager layoutManagerzcxx = new LinearLayoutManager(this);
        recyclerViewzcxx.setLayoutManager(layoutManagerzcxx);
        senddizhi();

        LinearLayout tianjiadizhi = (LinearLayout) findViewById(R.id.diancan_queren_dizhiguanli_tianjiadizhi);
        tianjiadizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(diancan_queren_dizhiguanli.this, diancan_tianjiadizhi.class);
                startActivity(intent);
                finish();
            }
        });

        senddizhi();
    }

    private void senddizhi() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("uid", "1").build();
                Request requestu = new Request.Builder().url(getString(R.string.waimaidizhiguanli_url)).post(formBody).build();
                Call call = okHttpClient.newCall(requestu);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("---", "错误" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); ++i) {
                                JSONObject jsonObjectcl = jsonArray.getJSONObject(i);
                                Dizhiguanlilei dizhiguanlilei = new Dizhiguanlilei(jsonObjectcl.getString("name"), jsonObjectcl.getString("tel"), jsonObjectcl.getString("address"), jsonObjectcl.getString("aid"),jsonObjectcl.getInt("status"));
                                dizhiguanlileiList.add(dizhiguanlilei);
                            }
                            final Dizhiguanliadapter dizhiguanliadapter = new Dizhiguanliadapter(dizhiguanlileiList);
                            dizhiguanliadapter.setOnItemClickListener(new Dizhiguanliadapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    aid = dizhiguanlileiList.get(position).getAid();
                                    sendmorendizhi();
                                    senddizhi();
                                }
                            });
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerViewzcxx.setAdapter(dizhiguanliadapter);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    private void sendmorendizhi() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("uid", "1").add("aid", aid).build();
                Request requestu = new Request.Builder().url(getString(R.string.xiugaimorendizhi_url)).post(formBody).build();
                Call call = okHttpClient.newCall(requestu);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("---", "错误" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(diancan_queren_dizhiguanli.this,"设置默认地址成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }).start();
    }

    public void refresh() {
        finish();
        Intent intent = new Intent(diancan_queren_dizhiguanli.this, diancan_queren_dizhiguanli.class);
        startActivity(intent);
    }
}
