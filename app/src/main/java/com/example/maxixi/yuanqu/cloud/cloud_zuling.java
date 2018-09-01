package com.example.maxixi.yuanqu.cloud;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

public class cloud_zuling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctivity_cloud_zuling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cloud_zuling_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final ImageButton zupcButton = (ImageButton) findViewById(R.id.cloud_zuling_zupc);
        zupcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent zupcIntent=new Intent();
                //zupcIntent.setClass(cloud_zuling.this,cloud_zuling_zupc.class);
                //startActivity(zupcIntent);
                Uri uri = Uri.parse("https://www.baidu.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        final ImageButton mizujieButton = (ImageButton) findViewById(R.id.cloud_zuling_mizujie);
        mizujieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent mizujieInten=new Intent();
                //mizujieInten.setClass(cloud_zuling.this,cloud_zuling_mizu.class);
                //mizujieInten.putExtra("data","http://www.mzujie.com");
                //startActivity(mizujieInten);
                Uri uri = Uri.parse("http://www.mzujie.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }
}
