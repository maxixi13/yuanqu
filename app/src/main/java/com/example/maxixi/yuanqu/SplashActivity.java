package com.example.maxixi.yuanqu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asplash);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏


       final TextView  textView = (TextView) findViewById(R.id.tv);
//        Message message = handler.obtainMessage(1);
//        handler.sendMessageDelayed(message, 1000);
//
        Button button=(Button)findViewById(R.id.tvb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        });

        CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(millisUntilFinished/1000 + "s");
            }

            @Override
            public void onFinish() {

                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                SplashActivity.this.finish();

            }
        }.start();


    }

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    time--;
//                    textView.setText(time + "s");
//                    if (time > 0) {
//                        Message message = handler.obtainMessage(1);
//                        handler.sendMessageDelayed(message, 1000);      // send message
//                    } else {
//                        //跳转到主界面
//                        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
//                        startActivity(mainIntent);
//                        SplashActivity.this.finish();
//                    }
//            }
//            super.handleMessage(msg);
//        }
//    };


}
