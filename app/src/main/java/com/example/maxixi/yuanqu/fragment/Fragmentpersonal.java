package com.example.maxixi.yuanqu.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.transition.CircularPropagation;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.maxixi.yuanqu.Loginpage;
import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.diancan.diancan_queren_dizhiguanli;
import com.example.maxixi.yuanqu.personal.renzhengqiye;
import com.example.maxixi.yuanqu.personal.tingche.tingchejilujilu;
import com.example.maxixi.yuanqu.personal.usermsset;
import com.example.maxixi.yuanqu.personal.waimaijilu;
import com.example.maxixi.yuanqu.personal.yuanneifuwujilu.Yuanqufujilu;
import com.example.maxixi.yuanqu.personal.yunfuwushenqingfuwu.yunfuwushenqingfuwu;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;


public class Fragmentpersonal extends Fragment {

    private String uid;
    private CircleImageView circleImageView;
    private TextView username;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.dctivity_personal, container, false);

        //view
        username = (TextView)view.findViewById(R.id.personal_username_text);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", null);

        circleImageView = (CircleImageView)view.findViewById(R.id.personal_touxiang_circleimageview);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),usermsset.class);
                startActivity(intent);
            }
        });

        ImageButton tingchejilu = (ImageButton) view.findViewById(R.id.personal_tingchejilu);
        tingchejilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), tingchejilujilu.class);
                startActivity(intent);
            }
        });


        ImageButton waimaijilu = (ImageButton) view.findViewById(R.id.personal_waimaijilu);
        waimaijilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), waimaijilu.class);
                startActivity(intent);
            }
        });

        TextView yunfuwushenqingfuwu = (TextView) view.findViewById(R.id.personal_yunfuwushenqingfuwu);
        yunfuwushenqingfuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), yunfuwushenqingfuwu.class);
                startActivity(intent);
            }
        });


        final TextView yuanqufuwujilu = (TextView) view.findViewById(R.id.personal_yuanqufushenqingjilu);
        yuanqufuwujilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Yuanqufujilu.class);
                startActivity(intent);
            }
        });


        TextView guanliwaimaidizhi = (TextView) view.findViewById(R.id.personal_guanliwaimaidizhi);
        guanliwaimaidizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), diancan_queren_dizhiguanli.class);
                startActivity(intent);
            }
        });


        TextView chengweiqiye = (TextView) view.findViewById(R.id.personal_chengweiqiye);
        chengweiqiye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendcheckqiye();
            }
        });


        LinearLayout logout = (LinearLayout) view.findViewById(R.id.personal_logoutbutton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Loginpage.class);
                startActivity(intent);
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userrdata", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
            }
        });


        getuserms();

        return view;
    }

    private void sendcheckqiye() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("uid", uid).build();
                Request request = new Request.Builder().url(getString(R.string.yanzhengqiyeyonghushifouzhuce_url)).post(formBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("sendcheckqiye", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            int code = jsonObject.getInt("code");
                            if (code == 202) {
                                Intent intent = new Intent(getContext(), renzhengqiye.class);
                                startActivity(intent);
                            } else if (code == 200) {
                                Log.e("sendcheckqiye", String.valueOf(code));
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(),"您已申请过",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else if (code==-1) {
                                Log.e("sendcheckqiye", String.valueOf(code));
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(),"待审核状态",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else if (code == -2) {
                                Log.e("sendcheckqiye", String.valueOf(code));
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(),"审核中状态",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    private void getuserms(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder().add("uid",uid).build();
                Request request=new Request.Builder().url(getString(R.string.yonghugerenxinxi_url)).post(formBody).build();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("--", "onFailure: " + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData=response.body().string();
                        try {
                            JSONObject jsonObject=new JSONObject(responseData);
                            JSONObject jsonObjectcl=jsonObject.getJSONObject("data");
                            final String usernameget=jsonObjectcl.getString("username");
                            final String touxiangurl=jsonObjectcl.getString("route");
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    username.setText(usernameget);
                                    Glide.with(getContext()).load(getString(R.string.touxiang_image_url)+touxiangurl).into(circleImageView);
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