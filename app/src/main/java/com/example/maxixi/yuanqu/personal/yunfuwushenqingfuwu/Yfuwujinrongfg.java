package com.example.maxixi.yuanqu.personal.yunfuwushenqingfuwu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maxixi.yuanqu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Yfuwujinrongfg extends Fragment {

    private List<yunfuwujilulei> yunfuwuList = new ArrayList<>();
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.dctivity_yunfuwushenqingfuwu_jinrong, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.yfuwu_jinrong_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        sendRequestWithOkHttp(0);


        return view;
    }
    private int code;
    private int page = 0;

    private void sendRequestWithOkHttp(final int page_value) {
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("userdata",Context.MODE_PRIVATE);
        final String uid=sharedPreferences.getString("uid",null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody formBody = new FormBody.Builder().add("type", "2").add("id",uid).add("page", String.valueOf(page)).build();
                    Request request = new Request.Builder().url(getString(R.string.gerenzhongxinyunfuwu_url)).post(formBody).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        code = jsonObject.getInt("code");
                        JSONArray array = jsonObject.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObjectchil = array.getJSONObject(i);
                            yunfuwujilulei madada = new yunfuwujilulei(jsonObjectchil.getString("type"), jsonObjectchil.getString("state"), jsonObjectchil.getString("title"), jsonObjectchil.getString("ctime"),jsonObjectchil.getString("cid"));
                            yunfuwuList.add(madada);
                            final Yunfuwujiluadapter yunfuwujiluadapter = new Yunfuwujiluadapter(yunfuwuList);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setAdapter(yunfuwujiluadapter);
                                    yunfuwujiluadapter.setOnItemClickListener(new Yunfuwujiluadapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            Intent intent=new Intent(getContext(),Yfuwushenqinglist.class);
                                            intent.putExtra("cid",yunfuwuList.get(position).getCid());
                                            intent.putExtra("type","1");
                                            intent.putExtra("title",yunfuwuList.get(position).getTitle());
                                            startActivity(intent);
                                        }
                                    });
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (code != -1 && code != 201) {
                        sendRequestWithOkHttp(++page);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}