package com.example.maxixi.yuanqu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.maxixi.yuanqu.MainActivity;
import com.example.maxixi.yuanqu.R;

import static com.example.maxixi.yuanqu.R.color.white;

public class Glogindenglufg extends Fragment {

    private CountDownTimer countDownTimer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.activity_loginpage_denglu, container, false);

        Button denglu = (Button) view.findViewById(R.id.login_denglubutton);
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        final Button yanzhengma = (Button) view.findViewById(R.id.login_yanzhengmabutton);
        yanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jishiqi(yanzhengma);
            }
        });


        return view;
    }


    private void jishiqi(final Button button) {

        if(countDownTimer!=null) {
            countDownTimer.cancel(); //防止new出多个导致时间跳动加速
            countDownTimer=null;
        }
        countDownTimer = new CountDownTimer(60 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                button.setText(millisUntilFinished / 1000 + "s");
                if(getActivity()==null){
                    cancel();
                }
            }

            @Override
            public void onFinish() {
                button.setText("获取验证码");

            }
        }.start();
    }

}
