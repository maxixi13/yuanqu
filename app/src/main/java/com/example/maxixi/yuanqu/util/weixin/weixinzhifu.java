package com.example.maxixi.yuanqu.util.weixin;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.diancan.diancan_queren;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
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

public class weixinzhifu {

    private Context context;
    private String oid;
    private String totalprice;

    public weixinzhifu(Context context,String oid,String totalprice) {
        this.context = context;
        this.oid=oid;
        this.totalprice=totalprice;
    }

    public void tongyixiadan() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("money", totalprice).add("out_trade_no", oid).build();
                Request request = new Request.Builder().url("http://mizhanzc.com/index/Wechat/unified_order").post(formBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("错误：", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            toWXPay(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    private void toWXPay(JSONObject jsonObject) throws JSONException {
        SharedPreferences sharedPreferences=context.getSharedPreferences("wxpayoid",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("oid",oid);
        editor.apply();

        IWXAPI wxapi = WXAPIFactory.createWXAPI(context, null); //初始化微信api
        wxapi.registerApp(Constants.APP_ID); //注册appid  appid可以在开发平台获取
        //调起微信APP的对象
        PayReq req = new PayReq();
        //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明

        req.appId = jsonObject.getString("appid");
        req.partnerId = jsonObject.getString("partnerid");
        req.prepayId = jsonObject.getString("prepayid");
        req.nonceStr = jsonObject.getString("noncestr");
        req.timeStamp = jsonObject.getString("timestamp");
        req.packageValue = jsonObject.getString("package");
        req.sign = jsonObject.getString("sign");
//            req.extData			= "app data";
        wxapi.sendReq(req);//发送调起微信的请求

    }
}
