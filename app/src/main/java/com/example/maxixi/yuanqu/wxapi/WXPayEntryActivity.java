package com.example.maxixi.yuanqu.wxapi;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.MainActivity;
import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.util.weixin.Constants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {


    private static final String TAG = "WXPayEntryActivity";
    private IWXAPI api;
    private TextView weixinzhifufanhui;
    private int code;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.weixinzhifuback_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        weixinzhifufanhui = (TextView) findViewById(R.id.weixinzhifuback_text);
        weixinzhifufanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WXPayEntryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);
        payqueren();


    }

    private void payqueren() {
        SharedPreferences sharedPreferences = getSharedPreferences("wxpayoid", MODE_PRIVATE);
        final String oid = sharedPreferences.getString("oid", "null");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("out_trade_no", oid).build();
                Request request = new Request.Builder().url(getString(R.string.weixinappid_queren_url)).post(formBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("错误", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responsedata = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(responsedata);
                            code = jsonObject.getInt("code");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("response", responsedata);
                        if (code == 200) {
                            weixinzhifufanhui.setText("支付成功,轻触返回");
                        } else if (code == 201) {
                            weixinzhifufanhui.setText("未完成支付,请返回重新支付");
                        } else if (code == 203) {
                            weixinzhifufanhui.setText("未查到该订单,请返回下单");
                        } else if (code == -1) {
                            weixinzhifufanhui.setText("异常错误");
                        }
                    }
                });
            }
        }).start();
    }
}