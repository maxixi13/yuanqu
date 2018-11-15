package com.example.maxixi.yuanqu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.maxixi.yuanqu.fragment.Glogindenglufg;
import com.example.maxixi.yuanqu.fragment.Gloginzhucefg;

public class Loginpage extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Glogindenglufg glogindenglufg;
    private Gloginzhucefg gloginzhucefg;
    private TextView denglubutton;
    private TextView zhucebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

//        CountDownTimer countDownTimer=new CountDownTimer(60*1000,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        }.start();


        denglubutton=(TextView)findViewById(R.id.login_denglutext);
        zhucebutton=(TextView)findViewById(R.id.login_zhucetext);
        denglubutton.setSelected(true);
        denglubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (glogindenglufg == null) {
                    glogindenglufg = new Glogindenglufg();
                }
                switchFragment(glogindenglufg);
                denglubutton.setSelected(true);
                zhucebutton.setSelected(false);
            }
        });

        zhucebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gloginzhucefg == null){
                    gloginzhucefg=new Gloginzhucefg();
                }
                switchFragment(gloginzhucefg);
                denglubutton.setSelected(false);
                zhucebutton.setSelected(true);
            }
        });




        glogindenglufg = new Glogindenglufg();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction().add(R.id.login_fg, glogindenglufg);
        fragment = glogindenglufg;
        transaction.commit();

//        SharedPreferences sharedPreferences=getSharedPreferences("userdata",MODE_PRIVATE);
//        String uid=sharedPreferences.getString("uid","null");
//        if (uid!=("null")){
//            Intent intent = new Intent(Loginpage.this, MainActivity.class);
//            finish();
//            startActivity(intent);
//        }

    }



    private void switchFragment(Fragment fg) {
        if (fragment != fg) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!fg.isAdded()) {
                transaction.hide(fragment).add(R.id.login_fg, fg);
            } else {
                transaction.hide(fragment).show(fg);
            }
            fragment = fg;
            transaction.commit();
        }
    }
}
