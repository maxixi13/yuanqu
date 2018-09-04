package com.example.maxixi.yuanqu.cloud;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.cloud.fragment.Fragmentjingrongfengxian;
import com.example.maxixi.yuanqu.cloud.fragment.Fragmentjinrongdaikuan;

public class cloud_jinrong extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Fragmentjingrongfengxian fragmentjinrongfengxian;
    private Fragmentjinrongdaikuan fragmentjinrongdaikuan;
    private Button topleft;
    private Button topright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_jinrong);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_jirong_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //顶部导航兰
        topleft = (Button)findViewById(R.id.jinrong_navigation_left);
        topleft.setSelected(true);
        topleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentjinrongfengxian == null) {
                    fragmentjinrongfengxian = new Fragmentjingrongfengxian();
                }
                switchFragment(fragmentjinrongfengxian);
                topleft.setSelected(true);
                topright.setSelected(false);
            }
        });
        topright = (Button)findViewById(R.id.jinrong_navigation_right);
        topright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentjinrongdaikuan == null) {
                    fragmentjinrongdaikuan = new Fragmentjinrongdaikuan();
                }
                switchFragment(fragmentjinrongdaikuan);
                topleft.setSelected(false);
                topright.setSelected(true);
            }
        });

        fragmentjinrongfengxian = new Fragmentjingrongfengxian();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction().add(R.id.jirong_fragment, fragmentjinrongfengxian);
        fragment = fragmentjinrongfengxian;
        transaction.commit();
    }



    private void switchFragment(Fragment fg) {
        if (fragment != fg) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!fg.isAdded()) {
                transaction.hide(fragment).add(R.id.jirong_fragment, fg);
            } else {
                transaction.hide(fragment).show(fg);
            }
            fragment = fg;
            transaction.commit();
        }
    }
}
