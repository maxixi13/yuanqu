package com.example.maxixi.yuanqu.diancan;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.diancan.adapter.QuerenAdapter;
import com.example.maxixi.yuanqu.diancan.model.Dish;
import com.example.maxixi.yuanqu.diancan.model.ShopCart;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class diancan_queren extends AppCompatActivity {


    private String uid;
    private TextView name;
    private TextView tel;
    private TextView address;
    private EditText beizhu;
    private String aid;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ectivity_diancan_queren);

        SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", "null");

        //view
        name = (TextView) findViewById(R.id.querendingdan_name_text);
        tel = (TextView) findViewById(R.id.querendingdan_tel_text);
        address = (TextView) findViewById(R.id.querendingdan_address_text);
        beizhu=(EditText) findViewById(R.id.querendingdan_beizhu_text);

        Toolbar toolbar = (Toolbar) findViewById(R.id.diancan_queren_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView zhifuText = (TextView) findViewById(R.id.queren_zhifufangshiText);
        zhifuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });


        Intent intent = getIntent();
        ShopCart shopCart = (ShopCart) intent.getSerializableExtra("shopcart");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.qurendingdan_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        QuerenAdapter dishAdapter = new QuerenAdapter(this, shopCart);
        recyclerView.setAdapter(dishAdapter);


        JSONArray jsonArray=new JSONArray();
        Map<Dish, Integer> dishmap = shopCart.getShoppingSingleMap();
        Iterator<Map.Entry<Dish, Integer>> it = dishmap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Dish, Integer> entry = it.next();
            Dish entryKey = entry.getKey();
            JSONObject jsonObjectmenu=new JSONObject();
            try {
                jsonObjectmenu.put("mid",entryKey.getMid());
                jsonObjectmenu.put("price",entryKey.getDishPrice());
                jsonObjectmenu.put("number",entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObjectmenu);
        }
        jsonObject = new JSONObject();
        try {
            jsonObject.put("uid",uid);
            jsonObject.put("stay_park","芈展");
            jsonObject.put("aid",aid);
            jsonObject.put("sum",shopCart.getShoppingTotalPrice());
            jsonObject.put("remark",beizhu.getText());
            jsonObject.put("menu",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("--","=="+jsonObject);

        LinearLayout dizhiguanli = (LinearLayout) findViewById(R.id.querendingdan_dizhilayout);
        dizhiguanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(diancan_queren.this, diancan_queren_dizhiguanli.class);
                intent.putExtra("signal", "1");
                startActivityForResult(intent, 1);
            }
        });


        TextView diancan_queren_totalprice = (TextView) findViewById(R.id.diancan_queren_totalprice);
        diancan_queren_totalprice.setText("￥ " + shopCart.getShoppingTotalPrice());

        Button querenbutton = (Button) findViewById(R.id.querendingdan_queren_button);
        querenbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shengchengdingdan();
            }
        });

        //获取默认地址
        getmorendizhi();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intentdata) {
        //requestCode:启动活动时传入的请求码
        //resultCode:返回数据时传入的处理结果
        //data:携带着返回数据的Intent
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    name.setText(intentdata.getStringExtra("name"));
                    tel.setText(intentdata.getStringExtra("tel"));
                    address.setText(intentdata.getStringExtra("address"));
                    aid = intentdata.getStringExtra("aid");
                }
                break;
            default:
        }
    }

    private void Dialog() {
        final String[] strArray = new String[]{"微信", "支付宝"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择支付方式");
        builder.setSingleChoiceItems(strArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView = (TextView) findViewById(R.id.queren_zhifufangshiText);
                textView.setText(strArray[which]);
                textView.setTextColor(Color.parseColor("#666666"));
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void getmorendizhi() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("uid", uid).build();
                Request requestu = new Request.Builder().url(getString(R.string.waimaidizhiguanli_url)).post(formBody).build();
                Call call = okHttpClient.newCall(requestu);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("---", "错误" + e);
                        Toast.makeText(diancan_queren.this,"地址获取失败请查看网络链接",Toast.LENGTH_SHORT).show();
                        address.setText("地址获取失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); ++i) {
                                JSONObject jsonObjectcl = jsonArray.getJSONObject(i);
                                if (jsonObjectcl.getString("status").equals("1")) {
                                    name.setText(jsonObjectcl.getString("name"));
                                    tel.setText(jsonObjectcl.getString("tel"));
                                    address.setText(jsonObjectcl.getString("address"));
                                    aid=jsonObjectcl.getString("aid");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    private void shengchengdingdan(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
                Request request=new Request.Builder().url(getString(R.string.shengchengdingdan_url)).post(requestBody).build();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("错误", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData=response.body().string();
                        Log.e("--","111"+responseData);
                        try {
                            JSONObject jsonObject=new JSONObject(responseData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //zhifubaozhifu();
                    }
                });
            }
        }).start();
    }

    private void zhifubaozhifu(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder().add("oder","oder").add("money","0.01").build();
                Request request=new Request.Builder().url(getString(R.string.diancanshoufei_url)).post(formBody).build();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("错误", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("success",response.body().string());
                    }
                });
            }
        }).start();
    }

}
