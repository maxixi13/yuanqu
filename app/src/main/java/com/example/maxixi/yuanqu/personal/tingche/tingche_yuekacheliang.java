package com.example.maxixi.yuanqu.personal.tingche;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.alipay.sdk.app.PayTask;
import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.util.AliPay;
import com.example.maxixi.yuanqu.util.OrderInfoUtil2_0;
import com.example.maxixi.yuanqu.util.PayResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class tingche_yuekacheliang extends AppCompatActivity {

    private TextView cartype;
    private TextView brand;
    private TextView model;
    private TextView license_plate;
    private TextView day;
    private TextView status;
    private TextView indatatime;
    private TextView time;
    private TextView carendindata;
    private String cid;
    private String uid;
    private AlertDialog dialog;
    private String APPID="zhanren@mijietech.com";
    private String PID="2088231581027432";
    private String RSA_PRIVATE="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDjUXTN7h/wshSfQ8J7Oa+0Rf/+Kxw0bNRHoY9uNWeoLKdkjP9mpeEBWV6+Y/G8H09mMwGtCxMJrtbg/M3f6e4u6VbJWc4a5GbEgogQPb+HFDwjG7YZk8zYD1NHkIYWlz1R1BDvNAgoUcpQlQLqI0UV5zkKuBEWY98JAnflLQol6tOP4IgAZUpy7ZIg2Cuj0+1N6FHo1MjvCN1k3srWFiRlHsaEjNJaC7xg17PQxMYc/axfN+7INL7YVOmRndqHVYuGL3d9asJjZiaJzzwu1pmZEcg/5/CUMtiBFPL921RNU+k1zeXDMCVePVgauEzduxv81t+BMnwGfGabvXVIg3x9AgMBAAECggEBAISKRhScNztcWfHSzF6U8AGonu5PrI5UtiFd7gdQVDQWdTOHkpMDPQJQXZGdLKGHkAAC1YNT5bHLo7ZjMJbSCvKHUvbryeScL998TGFL35SfE8FUswSOzO1dYi1j8wrQ/AvuHkGakPcRWWlKuPxTvEBdJUE+uZfYe38BVW7mp/garhY1cYi8TxcqKImk+rFVkV3F6qeQ35iywXOVowV3VHRGKQzTmGf8rP4RIBSXzlORe8oexltOW+vVZuJlEEkGc3dKkP3VzTPcXvIGZbcPYFlcHuP5nMLzMyqfi7HfnPz1g9nf//8otW2/Xl+twyVAP98Xvsr/yqWY+9J653i7SQECgYEA8zXuAIdZY3fRAytdoLzCkU+5xDwgAzibYZvtPnDQkfhJu1AVR5G7alvh2eySMoWIqxUbqaeRfOd50BEKBXcgHnro2ikrbslClJZVe49FJUqCnQcqyf/3RIma6LJe/PSum2gso1QhzJEl0uhjTLQsQYEPCTtQf3c+VS1LfziHVQ0CgYEA70WVitWzf02xt6OucqKSGuDlhsA32KXuZMbVJusH7IyLGX64MnWilVE2f//1/ml//xg/zQFp1b5ArA+m0GLxMf8mS2Dnb58c4O9ksOj10PgNdyqIWp3hVd0knrEieAJQIodd7kRxVZGmHGWD2ebPAAFDaob1G/UTqhNeJPzpyTECgYEA7+pOOpVJ13ZVZ6P6u1oZMRENdoe9KJBJrvMLCGJvTN85lJ5+L1iSIWw8EiZtXYsec23iGOyk9yq8nkSAtz15ILgsRTEBmErO9BaMgtOk6RFkYRS14AgxWpnHhy6Vx0BzkfgkuIWAxfSU9EWr1vmRApkWRQwO736orYt+AyS3U20CgYEAouexZRESYL45HVqnzwy4hOxR2WjNnQd8Q8jMHu8uOfOGURlht5k31ImynXtbtJeHudp8tcscj5Y02fDeFksHBI8/N1sGt4yRUOQsnfY+RsRcBqJCq8+KN7eU0yau3R5WCOw5G5wlvaioe/TxzE3E6a/ygnjYMOyvMsB0/KHlWkECgYAcObcpCdRHaaznvUVzzSNiiugn9Q+DM1zKGLRTVWwH3Socb6C5GAs7swvwuODKj/ZhDAfxLGHjm9G7begz8CmTkB246QErWjhqhvlctzSu/dkjTu8713Ul5JG3gQBTvlqW0w6UkjB6Iu4F5JD9isrl1jsv0QZUz2RTHXap3VSRrg==";

    private static final int SDK_PAY_FLAG = 1001;
    public final static int REQUEST_READ_PHONE_STATE = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_tingche_yuekacheliang);

        //动态权限申请
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            //TODO
        }

        Intent intent = getIntent();
        cid = intent.getStringExtra("cid");

        SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", null);

        //view
        cartype = (TextView) findViewById(R.id.tingchejiaofei_yueka_cartype_text);
        brand = (TextView) findViewById(R.id.tingchejiaofei_yueka_brand_text);
        model = (TextView) findViewById(R.id.tingchejiaofei_yueka_model_text);
        license_plate = (TextView) findViewById(R.id.tingchejiaofei_yueka_license_plate_text);
        day = (TextView) findViewById(R.id.tingchejiaofei_yueka_day_text);
        status = (TextView) findViewById(R.id.tingchejiaofei_yueka_status_text);
        indatatime = (TextView) findViewById(R.id.tingchejiaofei_yueka_indatatime_text);
        time = (TextView) findViewById(R.id.tingchejiaofei_yueka_time_text);
        carendindata = (TextView) findViewById(R.id.tingchejiaofei_yueka_carendindata_text);

        Toolbar toolbar = (Toolbar) findViewById(R.id.personal_tingchejiaofei_yueka_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sendokhttp();

        Button yuekaxufei = (Button) findViewById(R.id.tingchejiaofei_yueka_yuekaxufei_button);
        yuekaxufei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AliPay.Builder builder=new AliPay.Builder(tingche_yuekacheliang.this);
                builder.setSELLER(APPID)//商户收款账号//2018101561708246
                        .setPARTNER(PID)//设置商户PID
                        .setRSA_PRIVATE(RSA_PRIVATE)//商户密钥，pkcs
                        //设置支付宝支付成功后通知的地址，可以填写你公司的地址 .setNotifyURL()
                        .setOrderTitle("测试商品名字")
                        .setSubTitle("这是商品详情")
                        .setPrice("0.01")
                        .setPayCallBackListener(new AliPay.Builder.PayCallBackListener() {
                            @Override
                            public void onPayCallBack(int status, String resultStatus, String progress) {
                                //Toast.makeText(getParent(),progress,Toast.LENGTH_SHORT).show();
                                Log.e("debug",resultStatus+":"+progress);
                            }
                        });
                Log.e("--","seller"+builder);
                builder.pay();

                //zhifuDialog();
            }
        });
    }

    private void zhifuDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder.create();

        final View view = View.inflate(this, R.layout.dctivity_tingche_zhifu_item, null);
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题

        TextView weixin = (TextView) view.findViewById(R.id.zhifu_dialog_weixin_text);
        TextView zhifubao = (TextView) view.findViewById(R.id.zhifu_dialog_zhifubao_text);

        weixin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                AliPay.Builder builder=new AliPay.Builder(getParent());
                builder.setSELLER("zhanren@mijietech.com")//商户收款账号 //zhanren@mijietech.com
                        .setPARTNER("2088231581027432")//设置商户PID
                        .setRSA_PRIVATE("MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDjUXTN7h/wshSfQ8J7Oa+0Rf/+Kxw0bNRHoY9uNWeoLKdkjP9mpeEBWV6+Y/G8H09mMwGtCxMJrtbg/M3f6e4u6VbJWc4a5GbEgogQPb+HFDwjG7YZk8zYD1NHkIYWlz1R1BDvNAgoUcpQlQLqI0UV5zkKuBEWY98JAnflLQol6tOP4IgAZUpy7ZIg2Cuj0+1N6FHo1MjvCN1k3srWFiRlHsaEjNJaC7xg17PQxMYc/axfN+7INL7YVOmRndqHVYuGL3d9asJjZiaJzzwu1pmZEcg/5/CUMtiBFPL921RNU+k1zeXDMCVePVgauEzduxv81t+BMnwGfGabvXVIg3x9AgMBAAECggEBAISKRhScNztcWfHSzF6U8AGonu5PrI5UtiFd7gdQVDQWdTOHkpMDPQJQXZGdLKGHkAAC1YNT5bHLo7ZjMJbSCvKHUvbryeScL998TGFL35SfE8FUswSOzO1dYi1j8wrQ/AvuHkGakPcRWWlKuPxTvEBdJUE+uZfYe38BVW7mp/garhY1cYi8TxcqKImk+rFVkV3F6qeQ35iywXOVowV3VHRGKQzTmGf8rP4RIBSXzlORe8oexltOW+vVZuJlEEkGc3dKkP3VzTPcXvIGZbcPYFlcHuP5nMLzMyqfi7HfnPz1g9nf//8otW2/Xl+twyVAP98Xvsr/yqWY+9J653i7SQECgYEA8zXuAIdZY3fRAytdoLzCkU+5xDwgAzibYZvtPnDQkfhJu1AVR5G7alvh2eySMoWIqxUbqaeRfOd50BEKBXcgHnro2ikrbslClJZVe49FJUqCnQcqyf/3RIma6LJe/PSum2gso1QhzJEl0uhjTLQsQYEPCTtQf3c+VS1LfziHVQ0CgYEA70WVitWzf02xt6OucqKSGuDlhsA32KXuZMbVJusH7IyLGX64MnWilVE2f//1/ml//xg/zQFp1b5ArA+m0GLxMf8mS2Dnb58c4O9ksOj10PgNdyqIWp3hVd0knrEieAJQIodd7kRxVZGmHGWD2ebPAAFDaob1G/UTqhNeJPzpyTECgYEA7+pOOpVJ13ZVZ6P6u1oZMRENdoe9KJBJrvMLCGJvTN85lJ5+L1iSIWw8EiZtXYsec23iGOyk9yq8nkSAtz15ILgsRTEBmErO9BaMgtOk6RFkYRS14AgxWpnHhy6Vx0BzkfgkuIWAxfSU9EWr1vmRApkWRQwO736orYt+AyS3U20CgYEAouexZRESYL45HVqnzwy4hOxR2WjNnQd8Q8jMHu8uOfOGURlht5k31ImynXtbtJeHudp8tcscj5Y02fDeFksHBI8/N1sGt4yRUOQsnfY+RsRcBqJCq8+KN7eU0yau3R5WCOw5G5wlvaioe/TxzE3E6a/ygnjYMOyvMsB0/KHlWkECgYAcObcpCdRHaaznvUVzzSNiiugn9Q+DM1zKGLRTVWwH3Socb6C5GAs7swvwuODKj/ZhDAfxLGHjm9G7begz8CmTkB246QErWjhqhvlctzSu/dkjTu8713Ul5JG3gQBTvlqW0w6UkjB6Iu4F5JD9isrl1jsv0QZUz2RTHXap3VSRrg==")//商户密钥，pkcs
                        //设置支付宝支付成功后通知的地址，可以填写你公司的地址
                        .setOrderTitle("测试商品名字")
                        .setSubTitle("这是商品详情")
                        .setPrice("0.01")
                        .setPayCallBackListener(new AliPay.Builder.PayCallBackListener() {
                            @Override
                            public void onPayCallBack(int status, String resultStatus, String progress) {
                                Toast.makeText(getParent(),progress,Toast.LENGTH_SHORT).show();
                                Log.e("debug",resultStatus+":"+progress);
                            }
                        });
                builder.pay();



                dialog.dismiss();
            }
        });
        zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);//透明

        dialog.show();

    }

    private void sendokhttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("cid", cid).build();
                Request request = new Request.Builder().url(getString(R.string.cheliangtingcheshoufeixiangqing_url)).post(formBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("-----", "错误" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            final JSONObject jsonObjectcl = jsonObject.getJSONObject("data");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        cartype.setText(jsonObjectcl.getString("cartype"));
                                        brand.setText(jsonObjectcl.getString("brand"));
                                        model.setText(jsonObjectcl.getString("model"));
                                        license_plate.setText(jsonObjectcl.getString("license_plate"));
                                        day.setText(jsonObjectcl.getString("day"));
                                        String statusstr = "停车状态：" + jsonObjectcl.getString("status");
                                        status.setText(statusstr);
                                        if (status.getText().toString().contains("停车中"))
                                            status.setTextColor(Color.parseColor("#FFF13D46"));
                                        if (day.getText().toString().contains("0"))
                                            day.setTextColor(Color.parseColor("#FFF13D46"));
                                        indatatime.setText(jsonObjectcl.getString("InDateTime"));
                                        time.setText(jsonObjectcl.getString("time"));
                                        carendindata.setText(jsonObjectcl.getString("CarEndIndate"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
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

    private void updatajiaofei() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("uid", uid);
                    jsonObject.put("carNo", license_plate.getText().toString());
                    jsonObject.put("money", "150");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
