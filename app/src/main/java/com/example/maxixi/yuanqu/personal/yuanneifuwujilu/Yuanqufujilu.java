package com.example.maxixi.yuanqu.personal.yuanneifuwujilu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class Yuanqufujilu extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Yuantousufg yuantousufg;
    private Yuanqubaoxiufg yuanqubaoxiufg;
    private Button topletf;
    private Button topright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_yuanqufujilu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.yuanqufuwujilu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        yuantousufg = new Yuantousufg();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction().add(R.id.yuanqufuwujilu_fg, yuantousufg);
        fragment = yuantousufg;
        transaction.commit();

        topletf = (Button) findViewById(R.id.navigation_tousushenqing);
        topright = (Button) findViewById(R.id.navigation_baoxiushenqing);
        topletf.setSelected(true);
        topletf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (yuantousufg == null) {
                    yuantousufg = new Yuantousufg();
                }
                switchFragment(yuantousufg);
                topletf.setSelected(true);
                topright.setSelected(false);
            }
        });
        topright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yuanqubaoxiufg == null) {
                    yuanqubaoxiufg = new Yuanqubaoxiufg();
                }
                switchFragment(yuanqubaoxiufg);
                topletf.setSelected(false);
                topright.setSelected(true);
            }
        });


    }


    private void switchFragment(Fragment fg) {
        if (fragment != fg) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!fg.isAdded()) {
                transaction.hide(fragment).add(R.id.yuanqufuwujilu_fg, fg);
            } else {
                transaction.hide(fragment).show(fg);
            }
            fragment = fg;
            transaction.commit();
        }
    }
}
