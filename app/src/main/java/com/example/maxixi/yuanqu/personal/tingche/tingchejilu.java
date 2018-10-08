package com.example.maxixi.yuanqu.personal.tingche;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class tingchejilu extends AppCompatActivity {

    private List<Tingchecheliang> tingchecheliangList=new ArrayList<>();
    private EditText carnametext;
    private EditText modeltext;
    private EditText platenumtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_tingchejilu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.personal_tingchejiaofei_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RelativeLayout tianjiacheliangbutton = (RelativeLayout) findViewById(R.id.tingchejilu_tianjiacheliang);
        tianjiacheliangbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tianjiacheliangDialog();
            }
        });

        inittingchecheliangList();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.tingchejilu_recycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        TingchecheliangAdapter tingchecheliangAdapter=new TingchecheliangAdapter(tingchecheliangList);
        recyclerView.setAdapter(tingchecheliangAdapter);


//        LinearLayout yuekaLinearLayout = (LinearLayout) findViewById(R.id.tingchejilu_yueka_layout);
//        LinearLayout lingshiLinearLayout = (LinearLayout) findViewById(R.id.tingchejilu_lingshi_layout);
//        yuekaLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(tingchejilu.this, tingche_yuekacheliang.class);
//                startActivity(intent);
//            }
//        });
//
//        lingshiLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(tingchejilu.this, tingche_linshicheliang.class);
//                startActivity(intent);
//            }
//        });
    }


    private void tianjiacheliangDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();

        final View view = View.inflate(this, R.layout.dctivity_tingchejilu_tianjiaitem, null);
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题

        TextView quxiao = (TextView) view.findViewById(R.id.tianjia_dialog_quxiao);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView tijiao=(TextView)view.findViewById(R.id.tianjia_dialog_tiaojiao);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registerto();
            }
        });

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);//透明

        dialog.show();


//        dialog.setContentView(view);
//            final EditText etPassword = (EditText) view
//                    .findViewById(R.id.et_password);
//            final EditText etPasswordConfirm = (EditText) view
//                    .findViewById(R.id.et_password_confirm);
//
//            Button btnOK = (Button) view.findViewById(R.id.btn_ok);
//            Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

//            btnOK.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    String password = etPassword.getText().toString();
//                    String passwordConfirm = etPasswordConfirm.getText().toString();
//                    // password!=null && !password.equals("")
//                    if (!TextUtils.isEmpty(password) && !passwordConfirm.isEmpty()) {
//                        if (password.equals(passwordConfirm)) {
//                            // Toast.makeText(HomeActivity.this, "登录成功!",
//                            // Toast.LENGTH_SHORT).show();
//
//                            // 将密码保存起来
//                            mPref.edit()
//                                    .putString("password",
//                                            MD5Utils.encode(password)).commit();
//
//                            dialog.dismiss();
//
//                            // 跳转到手机防盗页
//                            startActivity(new Intent(HomeActivity.this,
//                                    LostFindActivity.class));
//                        } else {
//                            Toast.makeText(HomeActivity.this, "两次密码不一致!",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(HomeActivity.this, "输入框内容不能为空!",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//
//            btnCancel.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();// 隐藏dialog
//                }
//            });


    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder().url("").build();
                    Response response=client.newCall(request).execute();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void inittingchecheliangList(){
        Tingchecheliang madada=new Tingchecheliang("尊敬的月卡会员","法拉利","F430","沪A88888","30天","停车状态：未停车");
        tingchecheliangList.add(madada);
        Tingchecheliang madada1=new Tingchecheliang("尊敬的月卡会员","法拉利","F430","沪A88888","30天","停车状态：未停车");
        tingchecheliangList.add(madada1);
        Tingchecheliang madada2=new Tingchecheliang("尊敬的月卡会员","法拉利","F430","沪A88888","30天","停车状态：停车中");
        tingchecheliangList.add(madada2);
        Tingchecheliang madada3=new Tingchecheliang("尊敬的月卡会员","法拉利","F430","沪A88888","30天","停车状态：停车中");
        tingchecheliangList.add(madada3);
        Tingchecheliang madada4=new Tingchecheliang("尊敬的月卡会员","法拉利","F430","沪A88888","30天","停车状态：未停车");
        tingchecheliangList.add(madada4);
        Tingchecheliang madada5=new Tingchecheliang("尊敬的月卡会员","法拉利","F430","沪A88888","30天","停车状态：未停车");
        tingchecheliangList.add(madada5);
        Tingchecheliang madada6=new Tingchecheliang("尊敬的月卡会员","法拉利","F430","沪A88888","30天","停车状态：停车中");
        tingchecheliangList.add(madada6);
        Tingchecheliang madada7=new Tingchecheliang("尊敬的月卡会员","法拉利","F430","沪A88888","30天","停车状态：未停车");
        tingchecheliangList.add(madada7);

    }

    private void Registerto(){
        carnametext=(EditText)findViewById(R.id.tianjia_carname_text);
        modeltext=(EditText)findViewById(R.id.tianjia_model_text);
        platenumtext=(EditText)findViewById(R.id.tianjia_platenum_text);
        Log.i("----", "Registerto: "+carnametext.getText().toString());


    }



}
