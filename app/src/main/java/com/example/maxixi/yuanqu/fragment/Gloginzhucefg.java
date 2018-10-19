package com.example.maxixi.yuanqu.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maxixi.yuanqu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Gloginzhucefg extends Fragment {

    private CountDownTimer countDownTimer;
    private EditText shoujihao;
    private EditText mima;
    private EditText yonghuming;
    private EditText yanzhengmatx;
    private int clicktest = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.activity_loginpage_zhuce, container, false);

        //view
        shoujihao = (EditText) view.findViewById(R.id.login_zhuce_shoujihao_text);
        mima = (EditText) view.findViewById(R.id.login_zhuce_mima_text);
        yonghuming = (EditText) view.findViewById(R.id.login_zhuce_yonghuming_text);
        yanzhengmatx = (EditText) view.findViewById(R.id.login_zhuce_yanzhengma_text);

        final Button yanzhengma = (Button) view.findViewById(R.id.login_yanzhengmabutton);
        yanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shoujihao.getText().toString().length() != 0) {
                    if (clicktest == 1) {
                        getcode();
                    } else {
                        Toast.makeText(getContext(), "请一分钟后重新点击", Toast.LENGTH_SHORT).show();
                    }
                    jishiqi(yanzhengma);//倒计时
                } else {
                    Toast.makeText(getContext(), "请输入手机号码", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button zhucebutton = (Button) view.findViewById(R.id.login_zhuce_zhuce_button);
        zhucebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shoujihao.getText().toString().length() != 0 && mima.getText().toString().length() != 0 && yonghuming.getText().toString().length() != 0 && yanzhengmatx.getText().toString().length() != 0) {
                    Upload();
                } else {
                    Toast.makeText(getContext(), "请填写完整", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    private void jishiqi(final Button button) {
        clicktest = 0;
        if (countDownTimer != null) {
            countDownTimer.cancel(); //防止new出多个导致时间跳动加速
            countDownTimer = null;
        }
        countDownTimer = new CountDownTimer(60 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                button.setText(millisUntilFinished / 1000 + "s");
                if (getActivity() == null) {
                    cancel();
                }
            }

            @Override
            public void onFinish() {
                button.setText("重新获取验证码");
                clicktest = 1;

            }
        }.start();
    }

    private void Upload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("username", yonghuming.getText());
                    jsonObject.put("password", mima.getText());
                    jsonObject.put("tel", shoujihao.getText());
                    jsonObject.put("code", yanzhengmatx.getText());
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
                    Request request = new Request.Builder().url(getString(R.string.yonghuzhuce_url)).post(requestBody).build();
                    try {
                        Response response = okHttpClient.newCall(request).execute();
                        String responsedata = response.body().string();
                        //判断请求是否成功
                        if (response.isSuccessful()) {
                            //打印服务端返回结果
                            Log.e("------","--"+responsedata);
                            JSONObject jsonObjectget = new JSONObject(responsedata);
                            JSONObject jsonObjectgetid=jsonObjectget.getJSONObject("data");
                            SharedPreferences.Editor editor = getContext().getSharedPreferences("userdata", Context.MODE_PRIVATE).edit();
                            editor.putString("uid",jsonObjectgetid.getString("uid"));
                            editor.putInt("code", jsonObjectget.getInt("code"));
                            editor.apply();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void getcode() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("phone", shoujihao.getText().toString());
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
                    Request request = new Request.Builder().url(getString(R.string.huoquyanzhengma_url)).post(requestBody).build();
                    try {
                        Response response = okHttpClient.newCall(request).execute();
                        //判断请求是否成功
                        if (response.isSuccessful()) {
                            //打印服务端返回结果
                            Log.e("------", response.body().string());

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}