package com.example.maxixi.yuanqu.personal;

import android.app.AlertDialog;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

import java.util.Objects;

public class tingchejilu extends AppCompatActivity {

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

        RelativeLayout tianjiacheliangbutton=(RelativeLayout)findViewById(R.id.tingchejilu_tianjiacheliang);
        tianjiacheliangbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tianjiacheliangDialog();
            }
        });
    }



    private void tianjiacheliangDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final AlertDialog dialog = builder.create();

            View view = View.inflate(this, R.layout.dctivity_tingchejilu_tianjiaitem,null);
//        View view=LayoutInflater.from(this).inflate(R.layout.dctivity_tingchejilu_tianjiaitem,null);
            // dialog.setView(view);// 将自定义的布局文件设置给dialog
            dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题
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

            dialog.show();
        Toast.makeText(this,"hahah"+view.getHeight()+view.getWidth(),Toast.LENGTH_LONG).show();

    }



}
