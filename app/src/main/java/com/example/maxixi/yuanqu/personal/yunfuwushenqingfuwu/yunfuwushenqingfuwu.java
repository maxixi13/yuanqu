package com.example.maxixi.yuanqu.personal.yunfuwushenqingfuwu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class yunfuwushenqingfuwu extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Yfuwuzhengfufg yfuwuzhengfufg;
    private Yfuwujinrongfg yfuwujinrongfg;
    private Yfuwuchuangyefg yfuwuchuangyefg;
    private Yfuwuqiyefg yfuwuqiyefg;
    private Button zhengfu;
    private Button jinrong;
    private Button chuangye;
    private Button qiye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_yunfuwushenqingfuwu);


        Toolbar toolbar = (Toolbar) findViewById(R.id.yunfuwushenqingfuwu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        zhengfu =(Button)findViewById(R.id.navigation_zhengfuzixun);
        jinrong =(Button)findViewById(R.id.navigation_jinrongfuwu);
        chuangye =(Button)findViewById(R.id.navigation_chuangyefuwu);
        qiye =(Button)findViewById(R.id.navigation_qiyefuwu);

        zhengfu.setSelected(true);
        zhengfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yfuwuzhengfufg == null) {
                    yfuwuzhengfufg = new Yfuwuzhengfufg();
                }
                switchFragment(yfuwuzhengfufg);
                zhengfu.setSelected(true);
                jinrong.setSelected(false);
                chuangye.setSelected(false);
                qiye.setSelected(false);
            }
        });

        jinrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yfuwujinrongfg == null) {
                    yfuwujinrongfg = new Yfuwujinrongfg();
                }
                switchFragment(yfuwujinrongfg);
                zhengfu.setSelected(false);
                jinrong.setSelected(true);
                chuangye.setSelected(false);
                qiye.setSelected(false);
            }
        });

        chuangye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yfuwuchuangyefg == null) {
                    yfuwuchuangyefg = new Yfuwuchuangyefg();
                }
                switchFragment(yfuwuchuangyefg);
                zhengfu.setSelected(false);
                jinrong.setSelected(false);
                chuangye.setSelected(true);
                qiye.setSelected(false);
            }
        });

        qiye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yfuwuqiyefg == null) {
                    yfuwuqiyefg = new Yfuwuqiyefg();
                }
                switchFragment(yfuwuqiyefg);
                zhengfu.setSelected(false);
                jinrong.setSelected(false);
                chuangye.setSelected(false);
                qiye.setSelected(true);
            }
        });



        yfuwuzhengfufg = new Yfuwuzhengfufg();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction().add(R.id.yunfuwushenqingfuwu_fg, yfuwuzhengfufg);
        fragment =yfuwuzhengfufg;
        transaction.commit();




    }

    private void switchFragment(Fragment fg) {
        if (fragment != fg) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!fg.isAdded()) {
                transaction.hide(fragment).add(R.id.yunfuwushenqingfuwu_fg, fg);
            } else {
                transaction.hide(fragment).show(fg);
            }
            fragment = fg;
            transaction.commit();
        }
    }
}
