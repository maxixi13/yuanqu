package com.example.maxixi.yuanqu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.maxixi.yuanqu.GlideImageLoader;
import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.RecyclerViewGroup.hdxx;
import com.example.maxixi.yuanqu.RecyclerViewGroup.hdxxAdapter;
import com.example.maxixi.yuanqu.cloud.cloud_chuangye_chuangye;
import com.example.maxixi.yuanqu.cloud.cloud_zhengfu_zhengfu;
import com.example.maxixi.yuanqu.diancan.diancan;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

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


public class Fragmentnavigation extends Fragment {

    private List<hdxx> zcxxList = new ArrayList<>();
    private List<hdxx> hdxxList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewzcxx;
    private List<String> imagesBanner = new ArrayList<>();
    private String bannerurl;
    private ImageView zcxximg;
    private ImageView hdxximg;
    private TextView zcxxtitletext;
    private TextView hdxxtitletext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);

        //viewlist
        zcxximg = (ImageView)view.findViewById(R.id.zcxx_image);
        hdxximg = (ImageView)view.findViewById(R.id.hdxx_image);
        zcxxtitletext=(TextView)view.findViewById(R.id.zcxx_title_text);
        hdxxtitletext=(TextView)view.findViewById(R.id.hdxx_title_text);

        recyclerView = (RecyclerView) view.findViewById(R.id.relativelayout_hdxx);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerViewzcxx = (RecyclerView) view.findViewById(R.id.relativelayout_zcxx);
        LinearLayoutManager layoutManagerzcxx = new LinearLayoutManager(getActivity());
        recyclerViewzcxx.setLayoutManager(layoutManagerzcxx);
        recyclerViewzcxx.setNestedScrollingEnabled(false);

        sendRequestWithOkHttp();//异步加载

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

    private void startBanner() {
        //banner设置
        Banner banner = (Banner) getView().findViewById(R.id.banner);
        //设置banner样式
        //banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imagesBanner);
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
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity(), "嘿哈"+position+bannerurl, Toast.LENGTH_SHORT).show();

            }
        });
        banner.start();
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(getString(R.string.shouye_url)).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        JSONObject jsonObjectget = jsonObject.getJSONObject("data");

                        //banner
                        JSONArray arraybanner = jsonObjectget.getJSONArray("banner");
                        for (int i = 0; i < arraybanner.length(); i++) {
                            JSONObject jsonObjectin = arraybanner.getJSONObject(i);
                            imagesBanner.add(getString(R.string.shouye_image_url) + jsonObjectin.getString("path"));
                            bannerurl=jsonObjectin.getString("url");
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    startBanner();
                                }
                            });
                        }

                        //活动信息
                        JSONArray array = jsonObjectget.getJSONArray("activity");
                        for (int i = 1; i < array.length(); i++) {
                            final JSONObject jsonObjectout=array.getJSONObject(0);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Glide.with(getContext()).load(getString(R.string.shouye_image_url)+jsonObjectout.getString("path")).into(hdxximg);
                                        final String vid=jsonObjectout.getString("vid");
                                        hdxxtitletext.setText(jsonObjectout.getString("title"));
                                        hdxximg.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent=new Intent(getContext(),cloud_chuangye_chuangye.class);
                                                intent.putExtra("lid",vid);
                                                startActivity(intent);
                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            JSONObject jsonObjectin = array.getJSONObject(i);
                            final hdxx madada = new hdxx(jsonObjectin.getString("title"), jsonObjectin.getString("simple"), getString(R.string.shouye_image_url) + jsonObjectin.getString("path"),jsonObjectin.getString("vid"));
                            hdxxList.add(madada);
                            final hdxxAdapter adapterhdxx = new hdxxAdapter(hdxxList);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setAdapter(adapterhdxx);
                                    adapterhdxx.setOnItemClickListener(new hdxxAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            Intent intent=new Intent(getContext(),cloud_chuangye_chuangye.class);
                                            intent.putExtra("lid",hdxxList.get(position).getId());
                                            startActivity(intent);
                                        }
                                    });
                                }
                            });
                        }

                        //政策信息
                        JSONArray arrayzcxx = jsonObjectget.getJSONArray("policy");
                        for (int i = 1; i < arrayzcxx.length(); i++) {
                            final JSONObject jsonObjectout=arrayzcxx.getJSONObject(0);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Glide.with(getContext()).load(getString(R.string.shouye_image_url)+jsonObjectout.getString("path")).into(zcxximg);
                                        zcxxtitletext.setText(jsonObjectout.getString("title"));
                                        final String pid=jsonObjectout.getString("pid");
                                        zcxximg.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent=new Intent(getContext(),cloud_zhengfu_zhengfu.class);
                                                intent.putExtra("lid",pid);
                                                startActivity(intent);
                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            JSONObject jsonObjectin = arrayzcxx.getJSONObject(i);
                            hdxx madada = new hdxx(jsonObjectin.getString("title"), jsonObjectin.getString("simple"), getString(R.string.shouye_image_url) + jsonObjectin.getString("path"),jsonObjectin.getString("pid"));
                            zcxxList.add(madada);
                            final hdxxAdapter adapterzcxx = new hdxxAdapter(zcxxList);
                            adapterzcxx.setOnItemClickListener(new hdxxAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Intent intent=new Intent(getContext(),cloud_zhengfu_zhengfu.class);
                                    intent.putExtra("lid",zcxxList.get(position).getId());
                                    startActivity(intent);
                                }
                            });
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

//        private void initBanner(){
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        Request request = new Request.Builder().url("http://192.168.11.165/index").build();
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Looper.prepare();
//                Toast.makeText(getContext(), "网络连接失败", Toast.LENGTH_SHORT).show();
//                Looper.loop();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String responseData = response.body().string();
//                try {
//                    JSONObject jsonObject = new JSONObject(responseData);
//                    JSONObject jsonObjectget=jsonObject.getJSONObject("data");
//                    JSONArray array=jsonObjectget.getJSONArray("banner");
//                    for (int i = 0; i < array.length(); i++){
//                        JSONObject jsonObjectin=array.getJSONObject(i);
//                        imagesBanner.add("http://192.168.11.165/static/uploads/20180919/cb1014155a19ff7c181ac8d22c8280a0.jpg");
//                    }
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            startBanner();
//                        }
//                    });
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
}
