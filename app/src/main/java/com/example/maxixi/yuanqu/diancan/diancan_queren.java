package com.example.maxixi.yuanqu.diancan;


import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.diancan.adapter.QuerenAdapter;
import com.example.maxixi.yuanqu.diancan.model.ShopCart;
import com.example.maxixi.yuanqu.util.weixin.MD5;
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

public class diancan_queren extends AppCompatActivity {

    private PayReq request;
    private IWXAPI iwxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ectivity_diancan_queren);

        Toolbar toolbar = (Toolbar) findViewById(R.id.diancan_queren_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView zhifuText=(TextView)findViewById(R.id.queren_zhifufangshiText);
        zhifuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });


        Intent intent=getIntent();
        ShopCart shopCart=(ShopCart) intent.getSerializableExtra("shopcart");
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.qurendingdan_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        QuerenAdapter dishAdapter = new QuerenAdapter(this,shopCart);
        recyclerView.setAdapter(dishAdapter);
        Log.e("---","---"+shopCart.getDishAccount()+"---"+shopCart.getShoppingAccount()+"---"+shopCart.getShoppingSingleMap()+"---"+shopCart.getShoppingTotalPrice());


        LinearLayout dizhiguanli=(LinearLayout)findViewById(R.id.querendingdan_dizhilayout);
        dizhiguanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(diancan_queren.this,diancan_queren_dizhiguanli.class);
                startActivity(intent);
            }
        });


        TextView diancan_queren_totalprice=(TextView)findViewById(R.id.diancan_queren_totalprice);
        diancan_queren_totalprice.setText("￥ "+shopCart.getShoppingTotalPrice());

        Button querenbutton=(Button)findViewById(R.id.querendingdan_queren_button);
        querenbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getwxappid();
            }
        });

    }



    private void Dialog(){
        final String[] strArray=new String[]{"微信","支付宝"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("请选择支付方式");
        builder.setSingleChoiceItems(strArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView=(TextView)findViewById(R.id.queren_zhifufangshiText);
                textView.setText(strArray[which]);
                textView.setTextColor(Color.parseColor("#666666"));
                dialog.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    private void getwxappid(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder().add("money","0.1").add("out_trade_no","123").build();
                Request request=new Request.Builder().url(getString(R.string.diancaishoufei_url)).post(formBody).build();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("错误：", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData=response.body().string();
                        try {
                            JSONObject jsonObject=new JSONObject(responseData);
                            toWXPay(jsonObject.getString("appid"),jsonObject.getString("partnerid"),jsonObject.getString("prepayid"),jsonObject.getString("package"),jsonObject.getString("noncestr"),jsonObject.getString("timestamp"),jsonObject.getString("sign"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    private void toWXPay(final String appId, final String partnerId, final String prepayId, final String packageValue, final String nonceStr, final String timeStamp, final String sign) {
        iwxapi = WXAPIFactory.createWXAPI(diancan_queren.this,appId,false); //初始化微信api
        iwxapi.registerApp(appId); //注册appid  appid可以在开发平台获取


//        Runnable payRunnable = new Runnable() {
//            //这里注意要放在子线程
//            @Override
//            public void run() {
        //调起微信APP的对象
        request = new PayReq();
        //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
        request.appId = appId;
        request.nonceStr = nonceStr;
        request.packageValue =packageValue;
        request.partnerId = partnerId;
        request.prepayId = prepayId;
        request.timeStamp = timeStamp;

        request.sign=sign;
        //request.sign=genPayReq();

        iwxapi.sendReq(request);//发送调起微信的请求

    }

    /**
     * 生成签名
     */

    // 246055aabecbfd2d48f61218e33f1d66



//        };
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
//    }




    private String genPayReq() {

        ContentValues contentValues=new ContentValues();
        contentValues.put("appid", request.appId);
        contentValues.put("noncestr", request.nonceStr);
        contentValues.put("package", request.packageValue);
        contentValues.put("partnerid", request.partnerId);
        contentValues.put("prepayid", request.prepayId);
        contentValues.put("timestamp", request.timeStamp);

        String sb="appid="+request.appId+"&noncestr="+request.nonceStr+"&package="+request.packageValue+"&partnerid="+request.partnerId+"&prepayid="+request.partnerId+"&timestamp="+request.timeStamp;
        String appSign=MD5.getMessageDigest(sb.getBytes());
        return appSign;

    }



}
