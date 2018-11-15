package com.example.maxixi.yuanqu.personal.tingche;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.util.weixin.weixinzhifu;
import com.example.maxixi.yuanqu.util.zhifubao.zhifubaolei;

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
    private TextView sumfee;
    private TextView fee;
    private TextView indatatime2;
    private String cid;
    private String uid;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_tingche_linshicheliang);

        Intent intent = getIntent();
        cid = intent.getStringExtra("cid");

        SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", "null");

        //view
        cartype = (TextView) findViewById(R.id.tingchejiaofei_linshi_cartype_text);
        brand = (TextView) findViewById(R.id.tingchejiaofei_linshi_brand_text);
        model = (TextView) findViewById(R.id.tingchejiaofei_linshi_model_text);
        license_plate = (TextView) findViewById(R.id.tingchejiaofei_linshi_license_plate_text);
        day = (TextView) findViewById(R.id.tingchejiaofei_linshi_day_text);
        status = (TextView) findViewById(R.id.tingchejiaofei_linshi_status_text);
        indatatime = (TextView) findViewById(R.id.tingchejiaofei_linshi_indatatime_text);
        time = (TextView) findViewById(R.id.tingchejiaofei_linshi_time_text);
        sumfee = (TextView) findViewById(R.id.tingchejiaofei_lisnhi_sumfee_text);
        fee = (TextView) findViewById(R.id.tingchejiaofei_lisnhi_fee_text);
        indatatime2 = (TextView) findViewById(R.id.tingchejiaofei_linshi_indatatime2_text);

        Toolbar toolbar = (Toolbar) findViewById(R.id.personal_tingchejiaofei_linshi_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayout linshijiaofeibutton = (LinearLayout) findViewById(R.id.linshijiaofei_lijijiaofei_button);
        linshijiaofeibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhifuDialog();
            }
        });

        Button yuekachongzhi = (Button) findViewById(R.id.linshijiaofei_chongzhiyueka_button);
        yuekachongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhifuDialogyuekajiaofei();
            }
        });

        getcheliangxinxi();
    }


    private void zhifuDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder.create();

        final View view = View.inflate(this, R.layout.dctivity_tingche_zhifu_item, null);
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题

        TextView weixin = (TextView) view.findViewById(R.id.zhifu_dialog_weixin_text);
        final TextView zhifubao = (TextView) view.findViewById(R.id.zhifu_dialog_zhifubao_text);

        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sumfee.getText().toString().equals("0")){
                            Toast.makeText(tingche_linshicheliang.this,"您无需缴费",Toast.LENGTH_SHORT).show();
                }else {
                    weixinzhifu();
                }
                dialog.dismiss();
            }
        });
        zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sumfee.getText().toString().equals("0")){
                    Toast.makeText(tingche_linshicheliang.this,"您无需缴费",Toast.LENGTH_SHORT).show();
                }else {
                    zhifubaozhifu();
                }
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);//透明
        dialog.show();

    }

    private void zhifuDialogyuekajiaofei() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder.create();

        final View view = View.inflate(this, R.layout.dctivity_tingche_zhifu_item, null);
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题

        TextView weixin = (TextView) view.findViewById(R.id.zhifu_dialog_weixin_text);
        final TextView zhifubao = (TextView) view.findViewById(R.id.zhifu_dialog_zhifubao_text);

        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weixinzhifuyuekajiaofei();
                dialog.dismiss();
            }
        });
        zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhifubaozhifuyuekajiaofei();
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);//透明
        dialog.show();

    }

    private void getcheliangxinxi() {
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


    private void zhifubaozhifu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("uid", uid).add("carNo", String.valueOf(license_plate.getText())).add("money", sumfee.getText().toString()).add("paytype", "支付宝").build();
                Request request = new Request.Builder().url(getString(R.string.tingchejiaofei_url)).post(formBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("错误", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            final String outoder = jsonObject.getString("data");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    zhifubaolei zhifubaolei = new zhifubaolei(tingche_linshicheliang.this, tingche_linshicheliang.this, sumfee.getText().toString(), "临时缴费", outoder, getString(R.string.tingchejiaofei_url));
                                    zhifubaolei.payV2(getWindow().getDecorView());
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

    private void zhifubaozhifuyuekajiaofei() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("uid", uid).add("carNo", String.valueOf(license_plate.getText())).add("money", "150").add("paytype", "支付宝").build();
                Request request = new Request.Builder().url(getString(R.string.yuekachongzhi_url)).post(formBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("错误", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            final String outoder = jsonObject.getString("data");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    zhifubaolei zhifubaolei = new zhifubaolei(tingche_linshicheliang.this, tingche_linshicheliang.this, "150", "月卡充值", outoder, getString(R.string.yuekachongzhi_url));
                                    zhifubaolei.payV2(getWindow().getDecorView());
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

    private void weixinzhifu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("uid", uid).add("carNo", String.valueOf(license_plate.getText())).add("money", sumfee.getText().toString()).add("paytype", "微信").build();
                Request request = new Request.Builder().url(getString(R.string.tingchejiaofei_url)).post(formBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("错误", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.e("success", responseData);
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            final String outoder = jsonObject.getString("data");
                            weixinzhifu weixinzhifu = new weixinzhifu(tingche_linshicheliang.this, outoder, sumfee.getText().toString(),getString(R.string.tingchejiaofei_url));
                            weixinzhifu.tongyixiadan();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    private void weixinzhifuyuekajiaofei() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("uid", uid).add("carNo", String.valueOf(license_plate.getText())).add("money", "150").add("paytype", "微信").build();
                Request request = new Request.Builder().url(getString(R.string.yuekachongzhi_url)).post(formBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("错误", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            final String outoder = jsonObject.getString("data");
                            weixinzhifu weixinzhifu = new weixinzhifu(tingche_linshicheliang.this, outoder, "0.01",getString(R.string.yuekachongzhi_url));
                            weixinzhifu.tongyixiadan();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

}
