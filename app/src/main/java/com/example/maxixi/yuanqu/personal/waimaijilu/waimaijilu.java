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

public class waimaijilu extends AppCompatActivity {
    private List<Caipinfubean> caipinfubeanList=new ArrayList<>();
    private String uid;

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

        initList();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.waimaijilu_recycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(waimaijilu.this);
        recyclerView.setLayoutManager(layoutManager);
        CaipinfuAdapter caipinfuAdapter=new CaipinfuAdapter(waimaijilu.this,caipinfubeanList);
        recyclerView.setAdapter(caipinfuAdapter);

    }

//    private void getjilulist(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient okHttpClient=new OkHttpClient();
//                FormBody formBody=new FormBody.Builder().add("uid",uid).build()
//                Request request=new Request.Builder().url(getString(R.string.dingdanliebiao_url)).post(formBody).build();
//                Call call=okHttpClient.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.e("fail", String.valueOf(e));
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        String responseData=response.body().string();
//                        try {
//                            JSONObject jsonObject=new JSONObject(responseData);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//        }).start();
//    }

    private void initList(){
        List<Caipinbean> caipinbeanList=new ArrayList<>();
        Caipinbean caipinbean=new Caipinbean(R.drawable.texttouxiang,"mingzi","123","999");
        caipinbeanList.add(caipinbean);
        caipinbeanList.add(caipinbean);
        caipinbeanList.add(caipinbean);
        caipinbeanList.add(caipinbean);
        caipinbeanList.add(caipinbean);
        Caipinfubean caipinfubean=new Caipinfubean("芈展芈展芈展",caipinbeanList);
        caipinfubeanList.add(caipinfubean);

        List<Caipinbean> caipinbeanList1=new ArrayList<>();
        Caipinbean caipinbean1=new Caipinbean(R.drawable.texttouxiang,"dadada","312","123");
        caipinbeanList1.add(caipinbean1);
        caipinbeanList1.add(caipinbean1);
        caipinbeanList1.add(caipinbean1);
        caipinbeanList1.add(caipinbean1);
        caipinbeanList1.add(caipinbean1);
        Caipinfubean caipinfubean1=new Caipinfubean("芈展芈展芈展",caipinbeanList1);
        caipinfubeanList.add(caipinfubean1);
    }

}