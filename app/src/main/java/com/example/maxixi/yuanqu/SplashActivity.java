package com.example.maxixi.yuanqu;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asplash);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏


        final TextView textView = (TextView) findViewById(R.id.tv);


        Button button = (Button) findViewById(R.id.tvb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(SplashActivity.this, Loginpage.class);
                startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        });


        CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(millisUntilFinished / 1000 + "s");
                if (SplashActivity.this.isFinishing()) {
                    cancel();
                }
            }

            @Override
            public void onFinish() {

                Intent mainIntent = new Intent(SplashActivity.this, Loginpage.class);
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
