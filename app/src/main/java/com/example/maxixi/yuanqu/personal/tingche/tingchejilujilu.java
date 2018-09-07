package com.example.maxixi.yuanqu.personal.tingche;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class tingchejilujilu extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Tingchejilujilubenyuefg tingchejilujilubenyuefg;
    private Tingchejilujilubenyuefg1 tingchejilujilubenyuefg1;
    private Tingchejilujilubenyuefg2 tingchejilujilubenyuefg2;
    private Button navigationleft;
    private Button navigationmid;
    private Button navigationright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_tingchejilujilu);


        Toolbar toolbar = (Toolbar) findViewById(R.id.personal_tingchejilujilu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tingchejilujilubenyuefg=new Tingchejilujilubenyuefg();
        fragmentManager=getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction().add(R.id.tingchejilujilu_fg,tingchejilujilubenyuefg);
        fragment=tingchejilujilubenyuefg;
        transaction.commit();

        navigationleft=(Button)findViewById(R.id.tingchejilu_navigation_left);
        navigationleft.setSelected(true);
        navigationleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tingchejilujilubenyuefg == null){
                    tingchejilujilubenyuefg=new Tingchejilujilubenyuefg();
                }
                switchFragment(tingchejilujilubenyuefg);
                navigationleft.setSelected(true);
                navigationmid.setSelected(false);
                navigationright.setSelected(false);
            }
        });
        navigationmid=(Button)findViewById(R.id.tingchejilu_navigation_mid);
        navigationmid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tingchejilujilubenyuefg1 == null){
                    tingchejilujilubenyuefg1=new Tingchejilujilubenyuefg1();
                }
                switchFragment(tingchejilujilubenyuefg1);
                navigationleft.setSelected(false);
                navigationmid.setSelected(true);
                navigationright.setSelected(false);
            }
        });
        navigationright=(Button)findViewById(R.id.tingchejilu_navigation_right);
        navigationright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tingchejilujilubenyuefg2 == null){
                    tingchejilujilubenyuefg2=new Tingchejilujilubenyuefg2();
                }
                switchFragment(tingchejilujilubenyuefg2);
                navigationleft.setSelected(false);
                navigationmid.setSelected(false);
                navigationright.setSelected(true);
            }
        });
    }


    private void switchFragment(Fragment fg) {
        if (fragment != fg) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!fg.isAdded()) {
                transaction.hide(fragment).add(R.id.tingchejilujilu_fg, fg);
            } else {
                transaction.hide(fragment).show(fg);
            }
            fragment = fg;
            transaction.commit();
        }
    }
}
