package com.example.maxixi.yuanqu.perkservice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class parksevice_yuanei_zixun extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bctivity_parksevice_yuanei_zixun);

        Toolbar toolbar = (Toolbar) findViewById(R.id.yuannei_zixun_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView yuanneizixundianhua = (TextView) findViewById(R.id.yuannei_zixun_dianhuatext);
        SpannableString spannableString = new SpannableString("如果您有问题要解决，请拨打021-57885000");
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#09affb")), 13, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        yuanneizixundianhua.setText(spannableString);

        Button tel = (Button) findViewById(R.id.yuannei_zixun_lijilianxi);
        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到拨号界面
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.tel)));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


    }
}
