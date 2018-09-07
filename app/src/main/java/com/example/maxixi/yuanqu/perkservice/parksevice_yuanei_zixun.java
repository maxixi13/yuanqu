package com.example.maxixi.yuanqu.perkservice;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
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

        TextView yuanneizixundianhua=(TextView)findViewById(R.id.yuannei_zixun_dianhuatext);
        SpannableString spannableString = new SpannableString("如果您有问题要解决，请拨打201-66828839");
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#09affb")), 13,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        yuanneizixundianhua.setText(spannableString);
    }
}
