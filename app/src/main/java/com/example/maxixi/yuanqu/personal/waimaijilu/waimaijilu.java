package com.example.maxixi.yuanqu.personal.waimaijilu;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.diancan.adapter.QuerenAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class waimaijilu extends AppCompatActivity {
    private List<Caipinfubean> caipinfubeanList=new ArrayList<>();
    private String uid;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_waimaijilu);

        SharedPreferences sharedPreferences=getSharedPreferences("userdata",MODE_PRIVATE);
        uid = sharedPreferences.getString("uid","null");

        Toolbar toolbar = (Toolbar) findViewById(R.id.waimaijilu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.waimaijilu_recycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(waimaijilu.this);
        recyclerView.setLayoutManager(layoutManager);
        getjilulist();

    }

    private void getjilulist(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder().add("uid","1").build();
                Request request=new Request.Builder().url(getString(R.string.dingdanliebiao_url)).post(formBody).build();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("fail", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData=response.body().string();
                        try {
                            JSONObject jsonObject=new JSONObject(responseData);
                            JSONArray jsonArray=jsonObject.getJSONArray("data");
                            for (int i=0;i<jsonArray.length();++i){
                                JSONObject jsonObjectcl=jsonArray.getJSONObject(i);

                                JSONArray jsonArraycl=jsonObjectcl.getJSONArray("menu");
                                List<Caipinbean> caipinbeanList=new ArrayList<>();
                                for (int j=0;j<jsonArraycl.length();++j){
                                    JSONObject jsonObjectsz=jsonArraycl.getJSONObject(j);
                                    Caipinbean caipinbean=new Caipinbean(getString(R.string.waimaishouye_image_url)+jsonObjectsz.getString("food_img"),jsonObjectsz.getString("name"),jsonObjectsz.getString("number"),jsonObjectsz.getString("price"));
                                    caipinbeanList.add(caipinbean);
                                }
                                Caipinfubean caipinfubean=new Caipinfubean(jsonObjectcl.getString("stay_park"),jsonObjectcl.getString("ctime"),jsonObjectcl.getInt("status"),jsonObjectcl.getString("oid"),caipinbeanList);
                                caipinfubeanList.add(caipinfubean);
                            }
                            final CaipinfuAdapter caipinfuAdapter=new CaipinfuAdapter(waimaijilu.this,caipinfubeanList);
                            caipinfuAdapter.setOnItemClickListener(new CaipinfuAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Log.e("--","--"+caipinfubeanList.get(position).getOid());
                                }
                            });
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setAdapter(caipinfuAdapter);
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