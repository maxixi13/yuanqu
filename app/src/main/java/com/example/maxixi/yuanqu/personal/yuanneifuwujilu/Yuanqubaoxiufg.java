package com.example.maxixi.yuanqu.personal.yuanneifuwujilu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

public class Yuanqubaoxiufg extends Fragment {

    private List<Yuanqujilulei> yuanqujiluleiList=new ArrayList<>();
    private String uid;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.dctivity_yuanqufujilu_baoxiu, container, false);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("userdata",Context.MODE_PRIVATE);
        uid = sharedPreferences.getString("uid","null");

        recyclerView = (RecyclerView) view.findViewById(R.id.yuanqufuwujilu_baoxiu_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        getlist();


        return view;
    }

    private void getlist(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder().add("uid",uid).build();
                Request request=new Request.Builder().url(getString(R.string.baoxiuliebiao_url)).post(formBody).build();
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
                            final JSONObject jsonObject=new JSONObject(responseData);
                            JSONArray jsonArray=jsonObject.getJSONArray("data");
                            for (int i=0;i<jsonArray.length();++i){
                                JSONObject jsonObjectcl=jsonArray.getJSONObject(i);
                                Yuanqujilulei madada=new Yuanqujilulei(jsonObjectcl.getString("obj"),jsonObjectcl.getString("ctime"),jsonObjectcl.getString("rid"));
                                yuanqujiluleiList.add(madada);
                            }
                            final Yuanqujiluadapter yuanqujiluadapter = new Yuanqujiluadapter(yuanqujiluleiList);
                            yuanqujiluadapter.setOnItemClickListener(new Yuanqujiluadapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Intent intent=new Intent(getContext(),Yuanqubaoxiulist.class);
                                    intent.putExtra("rid",yuanqujiluleiList.get(position).getRid());
                                    startActivity(intent);
                                }
                            });
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setAdapter(yuanqujiluadapter);
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