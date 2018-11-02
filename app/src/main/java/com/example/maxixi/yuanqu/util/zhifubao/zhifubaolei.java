package com.example.maxixi.yuanqu.util.zhifubao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class zhifubaolei {

    private Context context;
    private Activity activity;
    private String sum;
    private String title;
    private String pid;
    private String backurl;

    public zhifubaolei(Context context,Activity activity,String sum,String title,String pid,String backurl){
        this.context=context;
        this.activity=activity;
        this.sum=sum;
        this.title=title;
        this.pid=pid;
        this.backurl=backurl;
    }


    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2018101561708246";

    /** 支付宝账户登录授权业务：入参pid值 */
    //public static final String PID = "2088231581027432";
    /** 支付宝账户登录授权业务：入参target_id值 */
    //public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    public static final String RSA2_PRIVATE = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDjUXTN7h/wshSfQ8J7Oa+0Rf/+Kxw0bNRHoY9uNWeoLKdkjP9mpeEBWV6+Y/G8H09mMwGtCxMJrtbg/M3f6e4u6VbJWc4a5GbEgogQPb+HFDwjG7YZk8zYD1NHkIYWlz1R1BDvNAgoUcpQlQLqI0UV5zkKuBEWY98JAnflLQol6tOP4IgAZUpy7ZIg2Cuj0+1N6FHo1MjvCN1k3srWFiRlHsaEjNJaC7xg17PQxMYc/axfN+7INL7YVOmRndqHVYuGL3d9asJjZiaJzzwu1pmZEcg/5/CUMtiBFPL921RNU+k1zeXDMCVePVgauEzduxv81t+BMnwGfGabvXVIg3x9AgMBAAECggEBAISKRhScNztcWfHSzF6U8AGonu5PrI5UtiFd7gdQVDQWdTOHkpMDPQJQXZGdLKGHkAAC1YNT5bHLo7ZjMJbSCvKHUvbryeScL998TGFL35SfE8FUswSOzO1dYi1j8wrQ/AvuHkGakPcRWWlKuPxTvEBdJUE+uZfYe38BVW7mp/garhY1cYi8TxcqKImk+rFVkV3F6qeQ35iywXOVowV3VHRGKQzTmGf8rP4RIBSXzlORe8oexltOW+vVZuJlEEkGc3dKkP3VzTPcXvIGZbcPYFlcHuP5nMLzMyqfi7HfnPz1g9nf//8otW2/Xl+twyVAP98Xvsr/yqWY+9J653i7SQECgYEA8zXuAIdZY3fRAytdoLzCkU+5xDwgAzibYZvtPnDQkfhJu1AVR5G7alvh2eySMoWIqxUbqaeRfOd50BEKBXcgHnro2ikrbslClJZVe49FJUqCnQcqyf/3RIma6LJe/PSum2gso1QhzJEl0uhjTLQsQYEPCTtQf3c+VS1LfziHVQ0CgYEA70WVitWzf02xt6OucqKSGuDlhsA32KXuZMbVJusH7IyLGX64MnWilVE2f//1/ml//xg/zQFp1b5ArA+m0GLxMf8mS2Dnb58c4O9ksOj10PgNdyqIWp3hVd0knrEieAJQIodd7kRxVZGmHGWD2ebPAAFDaob1G/UTqhNeJPzpyTECgYEA7+pOOpVJ13ZVZ6P6u1oZMRENdoe9KJBJrvMLCGJvTN85lJ5+L1iSIWw8EiZtXYsec23iGOyk9yq8nkSAtz15ILgsRTEBmErO9BaMgtOk6RFkYRS14AgxWpnHhy6Vx0BzkfgkuIWAxfSU9EWr1vmRApkWRQwO736orYt+AyS3U20CgYEAouexZRESYL45HVqnzwy4hOxR2WjNnQd8Q8jMHu8uOfOGURlht5k31ImynXtbtJeHudp8tcscj5Y02fDeFksHBI8/N1sGt4yRUOQsnfY+RsRcBqJCq8+KN7eU0yau3R5WCOw5G5wlvaioe/TxzE3E6a/ygnjYMOyvMsB0/KHlWkECgYAcObcpCdRHaaznvUVzzSNiiugn9Q+DM1zKGLRTVWwH3Socb6C5GAs7swvwuODKj/ZhDAfxLGHjm9G7begz8CmTkB246QErWjhqhvlctzSu/dkjTu8713Ul5JG3gQBTvlqW0w6UkjB6Iu4F5JD9isrl1jsv0QZUz2RTHXap3VSRrg==";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                        //sendfanhui();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(context, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    private void sendfanhui() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder().add("pid",pid).add("money",sum).build();
                final Request request=new Request.Builder().url(backurl).post(formBody).build();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("错误", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("成功",response.body().string());
                    }
                });

            }
        }).start();
    }


    /**
     * 支付宝支付业务
     *
     * @param v
     */
    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(context).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            activity.finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2,sum,title);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
