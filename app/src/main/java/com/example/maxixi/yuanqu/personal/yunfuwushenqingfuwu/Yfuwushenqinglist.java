package com.example.maxixi.yuanqu.personal.yunfuwushenqingfuwu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.JsonReader;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Yfuwushenqinglist extends AppCompatActivity {

    private TextView suozaiyuanqu;
    private TextView gongsimingcheng;
    private TextView shenqingneirong;
    private TextView lianxiren;
    private TextView lianxidianhua;
    private String cid;
    private String type;
    private String title;
    private TextView yuanneizixundianhua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_yfuwushenqinglist);

        //view
        suozaiyuanqu = (TextView)findViewById(R.id.yfuwu_shenqing_suozaiyuanqu_text);
        gongsimingcheng = (TextView)findViewById(R.id.yfuwu_shenqing_gongsimingcheng_text);
        shenqingneirong = (TextView)findViewById(R.id.yfuwu_shenqing_shenqingneirong_text);
        lianxiren = (TextView)findViewById(R.id.yfuwu_shenqing_lianxiren_text);
        lianxidianhua = (TextView)findViewById(R.id.yfuwu_shenqing_lianxidianhua_text);
        yuanneizixundianhua = (TextView)findViewById(R.id.yuanqu_yfuwu_list_dianhuatext);

        Toolbar toolbar = (Toolbar) findViewById(R.id.yuanqu_yfuwu_list_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent=getIntent();
        cid = intent.getStringExtra("cid");
        type = intent.getStringExtra("type");
        title = intent.getStringExtra("title");

        sendOkhttp();

    }


    private void sendOkhttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder().add("id",cid).add("type",type).build();
                Request request=new Request.Builder().url(getString(R.string.yonghuchakanyunfuwushenqing_url)).post(formBody).build();
                try {
                    Response response=okHttpClient.newCall(request).execute();
                    String responseData=response.body().string();
                    JSONObject jsonObject=new JSONObject(responseData);
                    JSONObject jsonObjectcl=jsonObject.getJSONObject("data");
                    suozaiyuanqu.setText(jsonObjectcl.getString("stay_park"));
                    gongsimingcheng.setText(jsonObjectcl.getString("stay_company"));
                    shenqingneirong.setText(title);
                    lianxiren.setText(jsonObjectcl.getString("name"));
                    lianxidianhua.setText(jsonObjectcl.getString("phone"));

                    SpannableString spannableString = new SpannableString(jsonObjectcl.getString("simple"));
                    spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#09affb")), spannableString.length()-4,spannableString.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    yuanneizixundianhua.setText(spannableString);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
