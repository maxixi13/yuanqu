package com.example.maxixi.yuanqu.cloud.fragment;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maxixi.yuanqu.R;

import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_adapter;
import com.example.maxixi.yuanqu.cloud.cloud_adapter.cloud_zhidao_lei;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class Fragmentchuangyechuangye extends Fragment {

    private List<cloud_zhidao_lei> dataArray = new ArrayList<>();
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container_cloud, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.cctivity_cloud_chuangye_zhidao, container_cloud, false);

        //viewlist
        recyclerView = (RecyclerView) view.findViewById(R.id.cloud_chuangye_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        Call call = new OkHttpClient().newCall(new Request.Builder().get().url("http://guolin.tech/api/china").build());
        //异步调用并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();

                try {
                    JSONArray array = new JSONArray(responseStr);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        cloud_zhidao_lei madada = new cloud_zhidao_lei(jsonObject.get("name").toString(), jsonObject.get("id").toString());
                        dataArray.add(madada);
                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
//                        cloud_zhidao_adapter.notifyDataSetChanged();
                        Log.i(TAG, "hhhhhhh" + jsonObject.get("name").toString());


                    }
                    //Log.i(TAG, "hhhhhhh"+jsonObject.get("code").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



//        String url = "http://wwww.baidu.com";
//        OkHttpClient okHttpClient = new OkHttpClient();
//        final Request request = new Request.Builder()
//                .url(url)
//                .get()//默认就是GET请求，可以不写
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "onFailure: ");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d(TAG, "onResponse: " + response.body().string());
//            }
//        });


//        //创业recyceler监听
//        cloud_zhidao_adapter.setOnItemClickListener(new cloud_zhidao_adapterchild.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(getContext(), "click " + zhidaoList.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });
//        recyclerView.setAdapter(cloud_zhidao_adapter);


        return view;
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        handler.removeCallbacksAndMessages(null);
//    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //添加分割线
                    cloud_zhidao_adapter cloud_zhidao_adapter = new cloud_zhidao_adapter(dataArray);
                    //设置布局显示格式
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(cloud_zhidao_adapter);
                    break;


            }
        }

    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
