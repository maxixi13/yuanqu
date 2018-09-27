package com.example.maxixi.yuanqu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.maxixi.yuanqu.GlideImageLoader;
import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.RecyclerViewGroup.hdxx;
import com.example.maxixi.yuanqu.RecyclerViewGroup.hdxxAdapter;
import com.example.maxixi.yuanqu.diancan.diancan;
import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Fragmentnavigation extends Fragment {

    private List<hdxx> zcxxList = new ArrayList<>();
    private List<hdxx> hdxxList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewzcxx;
    private List imagesBanner=new ArrayList();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);

        initBanner();//加载网络数据
        //startBanner();//banner

        //viewlist
        recyclerView = (RecyclerView) view.findViewById(R.id.relativelayout_hdxx);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        sendRequestWithOkHttp();
        recyclerView.setNestedScrollingEnabled(false);

        recyclerViewzcxx = (RecyclerView) view.findViewById(R.id.relativelayout_zcxx);
        LinearLayoutManager layoutManagerzcxx = new LinearLayoutManager(getActivity());
        recyclerViewzcxx.setLayoutManager(layoutManagerzcxx);
        sendRequestWithOkHttpZcxx();
        recyclerViewzcxx.setNestedScrollingEnabled(false);

        //hdxx监听
//        adapterhdxx.setOnItemClickListener(new hdxxAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(getContext(), "click " + hdxxList.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });
////        adapterhdxx.setOnItemLongClickListener(new hdxxAdapter.OnItemLongClickListener() {
////            @Override
////            public void onItemLongClick(View view, int position) {
////                Toast.makeText(getActivity(),"long click "+hdxxList.get(position),Toast.LENGTH_SHORT).show();
////            }
////        });
//        recyclerView.setAdapter(adapterhdxx);


        //zcxx监听
//        adapterzcxx.setOnItemClickListener(new hdxxAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(getContext(), "click " + zcxxList.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });
////        adapterzcxx.setOnItemLongClickListener(new hdxxAdapter.OnItemLongClickListener() {
////            @Override
////            public void onItemLongClick(View view, int position) {
////                Toast.makeText(getActivity(),"long click "+position,Toast.LENGTH_SHORT).show();
////            }
////        });
//        recyclerViewzcxx.setAdapter(adapterzcxx);


        //diancananniu
        ImageButton bannerdiancan = (ImageButton) view.findViewById(R.id.banner_diancan);
        bannerdiancan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bannerdiancan = new Intent();
                bannerdiancan.setClass(getActivity(), diancan.class);
                startActivity(bannerdiancan);
            }
        });

        ImageButton tingchejilu = (ImageButton) view.findViewById(R.id.banner_tingche);
        tingchejilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), com.example.maxixi.yuanqu.personal.tingche.tingchejilu.class);
                startActivity(intent);
            }
        });


        return view;
    }

    private void startBanner(){
        //banner设置
        Banner banner = (Banner) getView().findViewById(R.id.banner);
        //设置banner样式
        //banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
//        images = new Integer[]{R.drawable.home_page_icon, R.drawable.park_service_icon, R.drawable.cloud_service_icon, R.drawable.personal_center_icon, R.drawable.textimage};
        banner.setImages(Arrays.asList(imagesBanner));
        //设置banner动画效果
        //banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        //banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        //banner.isAutoPlay(true);
        //设置轮播时间
        //banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        //banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void initBanner(){
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        RequestBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder().url("http://192.168.11.165/index").build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(getContext(), "网络连接失败", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONObject jsonObjectget=jsonObject.getJSONObject("data");
                    JSONArray array=jsonObjectget.getJSONArray("banner");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObjectin=array.getJSONObject(i);
                        imagesBanner.add("http://192.168.11.165/index"+jsonObjectin.getString("path"));


                    }
                    startBanner();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Advertisement advertisement = new Gson().fromJson(str, Advertisement.class);
//                        int resultCode = advertisement.getResultCode();
//                        if (resultCode == 100) {
//                            adList = advertisement.getadvertisement1();
//                            for (int i = 0; i < adList.size(); i++) {
//                                String pic = adList.get(i).getPicture();
//                                imageUrl.add(ImageBasePath + pic);
//                            }
//                            startBanner();
//                        } else {
//                            Toast.makeText(getContext(), "advertisement.getResultMessage()", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
            }
        });
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://192.168.11.165/index").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        JSONObject jsonObjectget=jsonObject.getJSONObject("data");
                        JSONArray array=jsonObjectget.getJSONArray("activity");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObjectin=array.getJSONObject(i);
                            final hdxx madada = new hdxx(jsonObjectin.getString("title"),jsonObjectin.getString("simple"),"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538043454477&di=6221ab0980962e93ed2943a70b4d961c&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F62%2F50%2F62558PICxm8_1024.jpg");
                            hdxxList.add(madada);
                            final hdxxAdapter adapterhdxx = new hdxxAdapter(hdxxList);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setAdapter(adapterhdxx);
                                    adapterhdxx.setOnItemClickListener(new hdxxAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            Toast.makeText(getContext(),"哈哈哈"+hdxxList.get(position),Toast.LENGTH_SHORT).show();
                                        }
                                    });
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

    private void sendRequestWithOkHttpZcxx() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://192.168.11.165/index").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        JSONObject jsonObjectget=jsonObject.getJSONObject("data");
                        JSONArray array=jsonObjectget.getJSONArray("policy");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObjectin=array.getJSONObject(i);
                            hdxx madada = new hdxx(jsonObjectin.getString("title"),jsonObjectin.getString("simple"),"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538043454477&di=6221ab0980962e93ed2943a70b4d961c&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F62%2F50%2F62558PICxm8_1024.jpg");
                            zcxxList.add(madada);
                            final hdxxAdapter adapterzcxx = new hdxxAdapter(zcxxList);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerViewzcxx.setAdapter(adapterzcxx);
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
