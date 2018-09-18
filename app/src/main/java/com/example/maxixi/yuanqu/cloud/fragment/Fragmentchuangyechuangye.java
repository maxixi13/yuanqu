package com.example.maxixi.yuanqu.cloud.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.maxixi.yuanqu.R;

import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_adapter;
import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_lei;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Fragmentchuangyechuangye extends Fragment {

    private List<cloud_zhidao_lei> dataArray = new ArrayList<>();
    private RecyclerView recyclerView;
    private cloud_zhidao_adapter cloud_zhidao_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container_cloud, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.cctivity_cloud_chuangye_zhidao, container_cloud, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.cloud_chuangye_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        sendRequestWithOkHttp();


        return view;
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://192.168.11.121/index/Consultationdetails/finance_list").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        JSONArray array = jsonObject.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObjectchil = array.getJSONObject(i);
                            cloud_zhidao_lei madada = new cloud_zhidao_lei(jsonObjectchil.getString("title"), jsonObjectchil.getString("ctime"));
                            dataArray.add(madada);
                            cloud_zhidao_adapter = new cloud_zhidao_adapter(dataArray);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
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
