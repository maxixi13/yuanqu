package com.example.maxixi.yuanqu.cloud.qiyefuwu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.cloud.cloud_zhengfu_zhengfu_shenqing;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class cloud_qiyefuwu_gongshangzhuceSQ extends AppCompatActivity {

    private TextView textView;
    private TextView textView1;
    private EditText gongsimingcheng;
    private EditText lianxiren;
    private EditText lianxidianhua;
    private String lid;
    private TextView simpletext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_qiyefuwu_gongshangzhuce_sq);

        //view
        simpletext=(TextView)findViewById(R.id.gongshangzhuce_simple_text);
        textView = (TextView) findViewById(R.id.gongshangzhuce_fuwu_text);
        textView1 = (TextView) findViewById(R.id.gongshangzhuce_yuanqu_text);
        gongsimingcheng = (EditText) findViewById(R.id.gongshangzhuce_gongsimingcheng_text);
        lianxiren = (EditText) findViewById(R.id.gongshangzhuce_lianxiren_text);
        lianxidianhua = (EditText) findViewById(R.id.gongshangzhuce_lianxidianhua_text);
        TextView textViewtoolbar=(TextView)findViewById(R.id.gongshangzhuce_toolbar_text);



        Intent intent=getIntent();
        lid=intent.getStringExtra("lid");
        String title=intent.getStringExtra("title");
        String toolbartitle=intent.getStringExtra("toolbartitle");


        textView.setText(title);
        textViewtoolbar.setText(toolbartitle);


        Toolbar toolbar=(Toolbar)findViewById(R.id.gongshangzhuce_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.gongshangzhuce_yuanqu_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog1();
            }
        });

        Button banlibutton=(Button)findViewById(R.id.gongshangzhuce_banli_button);
        banlibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView1.getText().toString().length()!=0 && gongsimingcheng.getText().toString().length()!=0 && lianxiren.getText().toString().length()!=0 && lianxidianhua.getText().toString().length()!=0 ){
                    Upload();
                }else {
                    Toast.makeText(cloud_qiyefuwu_gongshangzhuceSQ.this, "请填写完整", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SendOkhttp();
    }

    //园区
    private void Dialog1() {
        //初始化字符串数组
        final String[] strArray = new String[]{"芈展服务园"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);//实例化builder
        builder.setTitle("请选择所在园区");//设置标题
        //设置单选列表
        builder.setSingleChoiceItems(strArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView1.setText(strArray[which]);
                textView1.setTextColor(Color.parseColor("#666666"));
                dialog.dismiss();
            }
        });
        //创建对话框
        AlertDialog dialog = builder.create();
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();//显示对话框
    }

    private void Upload() {
        SharedPreferences sharedPreferences = getSharedPreferences("userdata", Context.MODE_PRIVATE);
        final String uid = sharedPreferences.getString("uid", "null");
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("uid", uid);
                    jsonObject.put("sid", lid);
                    jsonObject.put("stay_park", textView1.getText());
                    jsonObject.put("stay_company", gongsimingcheng.getText());
                    jsonObject.put("name", lianxiren.getText());
                    jsonObject.put("phone", lianxidianhua.getText());
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
                    Request request = new Request.Builder().url(getString(R.string.qiyefuwushenqing_url)).post(requestBody).build();
                    try {
                        Response response = okHttpClient.newCall(request).execute();
                        //判断请求是否成功
                        if (response.isSuccessful()) {
                            //打印服务端返回结果
                            Thread.sleep(1000);
                            String responseData=response.body().string();
                            JSONObject jsonObjectgetcode=new JSONObject(responseData);
                            String code=jsonObjectgetcode.getString("code");
                            if (code.equals("200")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(cloud_qiyefuwu_gongshangzhuceSQ.this,"提交成功",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                cloud_qiyefuwu_gongshangzhuceSQ.this.finish();
                            }else {
                                Toast.makeText(cloud_qiyefuwu_gongshangzhuceSQ.this,"提交失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void SendOkhttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
               OkHttpClient okHttpClient=new OkHttpClient();
               FormBody formBody=new FormBody.Builder().add("id",lid).build();
               Request request=new Request.Builder().url(getString(R.string.qiyefuwuxiangqing_url)).post(formBody).build();
                try {
                    Response response=okHttpClient.newCall(request).execute();
                    String responseData=response.body().string();
                    JSONObject jsonObject=new JSONObject(responseData);
                    JSONObject jsonObjectcl=jsonObject.getJSONObject("data");
                    String simple=jsonObjectcl.getString("simple")+jsonObjectcl.getString("telephone");
                    SpannableString spannableString = new SpannableString(simple);
                    //spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#09affb")), spannableString.length()-11,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    simpletext.setText(simple);
                    simpletext.setText(spannableString);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
