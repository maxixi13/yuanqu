package com.example.maxixi.yuanqu.personal.tingche;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class tingchejilu_personal_list extends AppCompatActivity {
    private String uid;
    private RecyclerView recyclerView;
    private List<Tingchecheliang> tingchecheliangList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_tingchejilu_personal_list);


        SharedPreferences sharedPreferences = getSharedPreferences("userdata", Context.MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", "null");

        Toolbar toolbar = (Toolbar) findViewById(R.id.personal_tingchejilu_personal_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.tingchejilu_personal_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        sendtinghce();
    }

    private void sendtinghce() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("uid", uid).build();
                Request request = new Request.Builder().url(getString(R.string.yonghutingcheshoufeiliebiao_url)).post(formBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("---", "错误" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        try {
                            final JSONObject jsonObject = new JSONObject(responseData);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); ++i) {
                                JSONObject jsonObjectcl = jsonArray.getJSONObject(i);
                                Tingchecheliang tingchecheliang = new Tingchecheliang(jsonObjectcl.getString("card"), jsonObjectcl.getString("brand"), jsonObjectcl.getString("model"), jsonObjectcl.getString("license_plate"), jsonObjectcl.getString("day"), "停车状态：" + jsonObjectcl.getString("status"),jsonObjectcl.getString("cid"));
                                tingchecheliangList.add(tingchecheliang);
                            }
                            final TingchecheliangAdapter tingchecheliangAdapter = new TingchecheliangAdapter(tingchecheliangList);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setAdapter(tingchecheliangAdapter);
                                    tingchecheliangAdapter.setOnItemClickListener(new TingchecheliangAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            Intent intent=new Intent(tingchejilu_personal_list.this,tingchejilujilu.class);
                                            intent.putExtra("pid",tingchecheliangList.get(position).getPlatenum());
                                            intent.putExtra("type",tingchecheliangList.get(position).getType());
                                            intent.putExtra("carname",tingchecheliangList.get(position).getCarname());
                                            intent.putExtra("model",tingchecheliangList.get(position).getModel());
                                            intent.putExtra("time",tingchecheliangList.get(position).getTime());
                                            intent.putExtra("status",tingchecheliangList.get(position).getState());
                                            startActivity(intent);
                                        }
                                    });
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
}
