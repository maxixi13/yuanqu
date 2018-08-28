package com.example.maxixi.yuanqu.cloud;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class cloud_chuangye extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Fragmentchuangyechuangye fragmentchuangyechuangye;
    private Fragmentchuangyeluyan fragmentchuangyeluyan;


    //navigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.chuangy_navigation_chuangye:
                    if (fragmentchuangyechuangye == null){
                        fragmentchuangyechuangye = new Fragmentchuangyechuangye();
                    }
                    switchFragment(fragmentchuangyechuangye);
                    return true;
                case R.id.chuangye_navigation_luyan:
                    if (fragmentchuangyeluyan == null){
                        fragmentchuangyeluyan = new Fragmentchuangyeluyan();
                    }
                    switchFragment(fragmentchuangyeluyan);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_chuangye);

        Toolbar toolbar=(Toolbar)findViewById(R.id.cloud_chuangye_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //页面切换
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.chuangye_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentchuangyechuangye = new Fragmentchuangyechuangye();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction().add(R.id.chuangye_fragment, fragmentchuangyechuangye);
        fragment = fragmentchuangyechuangye;
        transaction.commit();
    }

    //切换fragment

    private void switchFragment(Fragment fg){
        if (fragment != fg){
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            if (!fg.isAdded()){
                transaction.hide(fragment).add(R.id.chuangye_fragment,fg);
            }else {
                transaction.hide(fragment).show(fg);
            }
            fragment = fg;
            transaction.commit();
        }
    }
}
