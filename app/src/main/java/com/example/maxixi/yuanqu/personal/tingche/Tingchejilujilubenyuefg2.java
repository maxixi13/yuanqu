package com.example.maxixi.yuanqu.personal.tingche;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class Tingchejilujilubenyuefg2 extends Fragment {

    private List<tingchejilulei> jilulist = new ArrayList<>();
    private RecyclerView recyclerView;
    private String pid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.dctivity_tingchejilu_monthlist2, container, false);


        Intent intent=getActivity().getIntent();
        pid = intent.getStringExtra("pid");

        recyclerView = (RecyclerView) view.findViewById(R.id.tingche_monthlist_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        sendtingchejilu();

        return view;
    }

    private void sendtingchejilu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("pid", pid).build();
                Request request = new Request.Builder().url(getString(R.string.shangshanggeyuetingcheshoufeijilu_rul)).post(formBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("----", "错误" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); ++i) {
                                JSONObject jsonObjectcl = jsonArray.getJSONObject(i);
                                tingchejilulei tingchejilulei = new tingchejilulei(jsonObjectcl.getString("PayDateTime"), jsonObjectcl.getString("Fee_ToPay"));
                                jilulist.add(tingchejilulei);
                            }
                            final tingchejiluadapter tingchejiluadapter = new tingchejiluadapter(jilulist);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setAdapter(tingchejiluadapter);
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
