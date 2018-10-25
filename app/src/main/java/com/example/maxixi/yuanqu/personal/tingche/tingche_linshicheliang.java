package com.example.maxixi.yuanqu.personal.tingche;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class tingche_linshicheliang extends AppCompatActivity {

    private TextView cartype;
    private TextView brand;
    private TextView model;
    private TextView license_plate;
    private TextView day;
    private TextView status;
    private TextView indatatime;
    private TextView time;
    private TextView carendindata;
    private TextView sumfee;
    private TextView fee;
    private TextView indatatime2;
    private String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_tingche_linshicheliang);

        Intent intent=getIntent();
        cid = intent.getStringExtra("cid");

        //view
        cartype = (TextView)findViewById(R.id.tingchejiaofei_yueka_cartype_text);
        brand = (TextView)findViewById(R.id.tingchejiaofei_yueka_brand_text);
        model = (TextView)findViewById(R.id.tingchejiaofei_yueka_model_text);
        license_plate = (TextView)findViewById(R.id.tingchejiaofei_yueka_license_plate_text);
        day = (TextView)findViewById(R.id.tingchejiaofei_yueka_day_text);
        status = (TextView)findViewById(R.id.tingchejiaofei_yueka_status_text);
        indatatime = (TextView)findViewById(R.id.tingchejiaofei_yueka_indatatime_text);
        time = (TextView)findViewById(R.id.tingchejiaofei_yueka_time_text);
        carendindata = (TextView)findViewById(R.id.tingchejiaofei_yueka_carendindata_text);
        sumfee=(TextView)findViewById(R.id.tingchejiaofei_lisnhi_sumfee_text);
        fee=(TextView)findViewById(R.id.tingchejiaofei_lisnhi_fee_text);
        indatatime2=(TextView)findViewById(R.id.tingchejiaofei_linshi_indatatime2_text);

        Toolbar toolbar = (Toolbar) findViewById(R.id.personal_tingchejiaofei_linshi_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sendokhttp();
    }

    private void sendokhttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder().add("cid",cid).build();
                Request request=new Request.Builder().url(getString(R.string.cheliangtingcheshoufeixiangqing_url)).post(formBody).build();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("-----","错误"+e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData=response.body().string();
                        try {
                            JSONObject jsonObject=new JSONObject(responseData);
                            final JSONObject jsonObjectcl=jsonObject.getJSONObject("data");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        cartype.setText(jsonObjectcl.getString("cartype"));
                                        brand.setText(jsonObjectcl.getString("brand"));
                                        model.setText(jsonObjectcl.getString("model"));
                                        license_plate.setText(jsonObjectcl.getString("license_plate"));
                                        day.setText(jsonObjectcl.getString("day"));
                                        String statusstr= "tingche"+jsonObjectcl.getString("status");
                                        status.setText(statusstr);
//                                        if (status.getText().toString().contains("停车中")) status.setTextColor(Color.parseColor("#09affb"));
//                                        if (day.getText().toString().contains("0"))day.setTextColor(Color.parseColor("#FFF13D46"));
                                        indatatime.setText(jsonObjectcl.getString("InDateTime"));
                                        time.setText(jsonObjectcl.getString("time"));
                                        carendindata.setText(jsonObjectcl.getString("CarEndIndate"));
                                        sumfee.setText(jsonObjectcl.getString("sumfee"));
                                        fee.setText(jsonObjectcl.getString("fee"));
                                        indatatime2.setText(jsonObjectcl.getString("InDateTime"));
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
}
