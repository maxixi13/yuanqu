package com.example.maxixi.yuanqu.personal.tingche;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ShareActionProvider;
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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class tingche_guanlicheliang extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Tingchecheliang> tingchecheliangList = new ArrayList<>();
    private String uid;
    private AlertDialog dialog;
    private EditText carnametext;
    private EditText modeltext;
    private EditText platenumtext;
    private String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_tingche_guanlicheliang);

        SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", "null");


        Toolbar toolbar = (Toolbar) findViewById(R.id.personal_guanlicheliang_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.personal_guanlicheliang_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(tingche_guanlicheliang.this);
        recyclerView.setLayoutManager(layoutManager);
        getchelianglist();

    }

    private void getchelianglist() {
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
                        Log.e("fail", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); ++i) {
                                JSONObject jsonObjectcl = jsonArray.getJSONObject(i);
                                Tingchecheliang tingchecheliang = new Tingchecheliang(jsonObjectcl.getString("card"), jsonObjectcl.getString("brand"), jsonObjectcl.getString("model"), jsonObjectcl.getString("license_plate"), jsonObjectcl.getString("day"), "停车状态：" + jsonObjectcl.getString("status"), jsonObjectcl.getString("cid"));
                                tingchecheliangList.add(tingchecheliang);
                            }
                            final TingchecheliangAdapter tingchecheliangAdapter = new TingchecheliangAdapter(tingchecheliangList);
                            tingchecheliangAdapter.setOnItemClickListener(new TingchecheliangAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    tianjiacheliangDialog();
                                    cid = tingchecheliangList.get(position).getCid();
                                }
                            });
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setAdapter(tingchecheliangAdapter);
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


    //修改车辆
    private void xiugaicheliang() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("uid", uid);
                    jsonObject.put("cid",cid);
                    jsonObject.put("license", platenumtext.getText());
                    jsonObject.put("model", modeltext.getText());
                    jsonObject.put("brand", carnametext.getText());
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
                    Request request = new Request.Builder().url(getString(R.string.tianjiacheliang_url)).post(requestBody).build();
                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("----", "error" + e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.e("----", response.body().string());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                    Toast.makeText(tingche_guanlicheliang.this, "提交成功", Toast.LENGTH_SHORT).show();
                                    tingchecheliangList.clear();
                                    getchelianglist();
                                }
                            });
                            getchelianglist();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    private void tianjiacheliangDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder.create();

        final View view = View.inflate(this, R.layout.dctivity_tingchejilu_tianjiaitem, null);
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题

        //view
        carnametext = (EditText) view.findViewById(R.id.tianjia_carname_text);
        modeltext = (EditText) view.findViewById(R.id.tianjia_model_text);
        platenumtext = (EditText) view.findViewById(R.id.tianjia_platenum_text);

        TextView quxiao = (TextView) view.findViewById(R.id.tianjia_dialog_quxiao);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView tijiao = (TextView) view.findViewById(R.id.tianjia_dialog_tiaojiao);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xiugaicheliang();
            }
        });

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);//透明

        dialog.show();

    }
}
