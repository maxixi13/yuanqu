package com.example.maxixi.yuanqu.personal.yuanneifuwujilu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Yuanqubaoxiulist extends AppCompatActivity {
    private String uid;
    private TextView suozaiyuanqu;
    private TextView bangongquyu;
    private TextView gongsimingcheng;
    private TextView baoxiuleixing;
    private TextView baoxiuduixiang;
    private TextView lianxiren;
    private TextView lianxidianhua;
    private TextView shenqingshijian;
    private String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_yuanqubaoxiulist);

        SharedPreferences sharedPreferences=getSharedPreferences("userdata",Context.MODE_PRIVATE);
        uid = sharedPreferences.getString("uid","null");

        Intent intent=getIntent();
        cid = intent.getStringExtra("rid");

        Toolbar toolbar = (Toolbar) findViewById(R.id.yuanqu_baoxiu_list_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView yuanneizixundianhua=(TextView)findViewById(R.id.yuanqu_baoxiu_list_dianhuatext);
        SpannableString spannableString = new SpannableString("如果您有问题要解决，请拨打201-66828839");
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#09affb")), 13,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        yuanneizixundianhua.setText(spannableString);

        //View
        suozaiyuanqu = (TextView)findViewById(R.id.baoxiushengqing_suozaiyuanqu_text);
        bangongquyu = (TextView)findViewById(R.id.baoxiushengqing_bangongquyu_text);
        gongsimingcheng = (TextView)findViewById(R.id.baoxiushengqing_gongsimingcheng_text);
        baoxiuleixing = (TextView)findViewById(R.id.baoxiushengqing_baoxiuleixing_text);
        baoxiuduixiang = (TextView)findViewById(R.id.baoxiushengqing_baoxiuduixiang_text);
        lianxiren = (TextView)findViewById(R.id.baoxiushengqing_lianxiren_text);
        lianxidianhua = (TextView)findViewById(R.id.baoxiushengqing_lianxidianhua_text);
        shenqingshijian=(TextView)findViewById(R.id.baoxiushengqing_shenqingshijian_text);

        getlist();

        Button lijilianxi=(Button)findViewById(R.id.baoxiushenqing_lijilianxi_button);
        lijilianxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到拨号界面
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.tel)));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void getlist(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder().add("uid",uid).add("cid",cid).build();
                Request request=new Request.Builder().url(getString(R.string.baoxiuxiangqing_url)).post(formBody).build();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("fail", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData=response.body().string();
                        try {
                            JSONObject jsonObject=new JSONObject(responseData);
                            JSONObject jsonObjectcl=jsonObject.getJSONObject("data");
                            suozaiyuanqu.setText(jsonObjectcl.getString("stay_park"));
                            bangongquyu.setText(jsonObjectcl.getString("region"));
                            gongsimingcheng.setText(jsonObjectcl.getString("stay_company"));
                            baoxiuleixing.setText(jsonObjectcl.getString("type_name"));
                            baoxiuduixiang.setText(jsonObjectcl.getString("obj"));
                            lianxiren.setText(jsonObjectcl.getString("complaint_name"));
                            lianxidianhua.setText(jsonObjectcl.getString("phone"));
                            shenqingshijian.setText(jsonObjectcl.getString("ctime"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }
}
