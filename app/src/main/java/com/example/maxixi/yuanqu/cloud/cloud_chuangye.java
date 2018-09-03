package com.example.maxixi.yuanqu.cloud;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.perkservice.Parkservice_yuannei;

public class cloud_chuangye extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Fragmentchuangyechuangye fragmentchuangyechuangye;
    private Fragmentchuangyeluyan fragmentchuangyeluyan;
    private Button topleft;
    private Button topright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_chuangye);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_chuangye_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //顶部导航兰
        topleft = (Button)findViewById(R.id.chuangye_navigation_left);
        topleft.setSelected(true);
        topleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentchuangyechuangye == null) {
                    fragmentchuangyechuangye = new Fragmentchuangyechuangye();
                }
                switchFragment(fragmentchuangyechuangye);
                topleft.setSelected(true);
                topright.setSelected(false);
            }
        });
        topright = (Button)findViewById(R.id.chuangye_navigation_right);
        topright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentchuangyeluyan == null) {
                    fragmentchuangyeluyan = new Fragmentchuangyeluyan();
                }
                switchFragment(fragmentchuangyeluyan);
                topleft.setSelected(false);
                topright.setSelected(true);
            }
        });


        fragmentchuangyechuangye = new Fragmentchuangyechuangye();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction().add(R.id.chuangye_fragment, fragmentchuangyechuangye);
        fragment = fragmentchuangyechuangye;
        transaction.commit();


    }

    //切换fragment

    private void switchFragment(Fragment fg) {
        if (fragment != fg) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!fg.isAdded()) {
                transaction.hide(fragment).add(R.id.chuangye_fragment, fg);
            } else {
                transaction.hide(fragment).show(fg);
            }
            fragment = fg;
            transaction.commit();
        }
    }
}
