package com.example.maxixi.yuanqu.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maxixi.yuanqu.MainActivity;
import com.example.maxixi.yuanqu.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.maxixi.yuanqu.R.color.white;

public class Glogindenglufg extends Fragment {

    private CountDownTimer countDownTimer;
    private EditText shoujihao;
    private EditText mima;
    private EditText yanzhengmatx;
    private int clicktest = 1;
    private int code;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.activity_loginpage_denglu, container, false);


//
//        Button ceshi=(Button)view.findViewById(R.id.ceshi);
//        ceshi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences.Editor editor = getContext().getSharedPreferences("userdata", Context.MODE_PRIVATE).edit();//测试完删除ddd
//                editor.putString("uid","5");
//                editor.apply();
//                Intent intent = new Intent(getContext(), MainActivity.class);
//                getActivity().finish();
//                startActivity(intent);
//            }
//        });



        //view
        shoujihao = (EditText) view.findViewById(R.id.login_denglu_shoujihao_text);
        mima = (EditText) view.findViewById(R.id.login_denglu_mima_text);
        yanzhengmatx = (EditText) view.findViewById(R.id.login_denglu_yanzhengma_text);

        Button denglu = (Button) view.findViewById(R.id.login_denglubutton);
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shoujihao.getText().toString().length() != 0 && mima.getText().toString().length() != 0 && yanzhengmatx.getText().toString().length() != 0) {
                    Upload();
                } else {
                    Toast.makeText(getContext(), "请填写完整", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final Button yanzhengma = (Button) view.findViewById(R.id.login_yanzhengmabutton);
        yanzhengma.setOnClickListener(new View.OnClickListener()

        {
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
                    jsonObject.put("tel", shoujihao.getText());
                    jsonObject.put("password", mima.getText());
                    jsonObject.put("code", yanzhengmatx.getText());
                    OkHttpClient okHttpClient = new OkHttpClient();
                    SharedPreferences share = getContext().getSharedPreferences("Session", MODE_PRIVATE);
                    String sessionid = share.getString("sessionid", null);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
                    Request request = new Request.Builder().url(getString(R.string.yonghudenglu_url)).addHeader("cookie", sessionid).post(requestBody).build();
                    try {
                        Response response = okHttpClient.newCall(request).execute();
                        String responsedata = response.body().string();
                        //判断请求是否成功
                        if (response.isSuccessful()) {
                            //打印服务端返回结果
                            Log.e("------", "--" + responsedata);
                            JSONObject jsonObjectget = new JSONObject(responsedata);
                            code = jsonObjectget.getInt("code");
                            JSONObject jsonObjectgetid = jsonObjectget.getJSONObject("data");
                            SharedPreferences.Editor editor = getContext().getSharedPreferences("userdata", Context.MODE_PRIVATE).edit();
                            editor.putString("uid", jsonObjectgetid.getString("uid"));
                            editor.apply();


                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code == 200) {
                            Toast.makeText(getContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            getActivity().finish();
                            startActivity(intent);
                            SharedPreferences share = getContext().getSharedPreferences("Session", MODE_PRIVATE);
                            SharedPreferences.Editor editor = share.edit();
                            editor.clear().apply();
                        } else if (code == -2) {
                            Toast.makeText(getContext(), "用户密码错误", Toast.LENGTH_SHORT).show();
                        } else if (code == -1) {
                            Toast.makeText(getContext(), "登陆失败，请确认账户、密码", Toast.LENGTH_SHORT).show();
                        } else if (code == 404) {
                            Toast.makeText(getContext(), "网络连接失败", Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(getContext(), "验证码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
                        //保持session
                        Headers headers = response.headers();
                        List<String> cookieStringList = headers.values("Set-Cookie");
                        String cookieString = cookieStringList.get(0);
                        String sessionid = cookieString.substring(0, cookieString.indexOf(";"));
                        SharedPreferences share = getContext().getSharedPreferences("Session", MODE_PRIVATE);
                        SharedPreferences.Editor editor = share.edit();//编辑文件
                        editor.putString("sessionid", sessionid);
                        editor.apply();

                        if (response.isSuccessful()) {
                            //打印服务端返回结果
                            Log.e("------", response.body().string());

                        } else {
                            code = 404;
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
