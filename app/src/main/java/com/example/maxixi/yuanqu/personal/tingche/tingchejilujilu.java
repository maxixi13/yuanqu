package com.example.maxixi.yuanqu.personal.tingche;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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


        //view
        TextView typetx=(TextView)findViewById(R.id.personal_tingchejilujilu_type_text);
        TextView carnametx=(TextView)findViewById(R.id.personal_tingchejilujilu_carname_text);
        TextView modeltx=(TextView)findViewById(R.id.personal_tingchejilujilu_model_text);
        TextView platenumtx=(TextView)findViewById(R.id.personal_tingchejilujilu_platenum_text);
        TextView timetx=(TextView)findViewById(R.id.personal_tingchejilujilu_time_text);
        TextView statustx=(TextView)findViewById(R.id.personal_tingchejilujilu_status_text);

        Toolbar toolbar = (Toolbar) findViewById(R.id.personal_tingchejilujilu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tingchejilujilubenyuefg = new Tingchejilujilubenyuefg();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction().add(R.id.tingchejilujilu_fg, tingchejilujilubenyuefg);
        fragment = tingchejilujilubenyuefg;
        transaction.commit();

        navigationleft = (Button) findViewById(R.id.tingchejilu_navigation_left);
        navigationleft.setSelected(true);
        navigationleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tingchejilujilubenyuefg == null) {
                    tingchejilujilubenyuefg = new Tingchejilujilubenyuefg();
                }
                switchFragment(tingchejilujilubenyuefg);
                navigationleft.setSelected(true);
                navigationmid.setSelected(false);
                navigationright.setSelected(false);
            }
        });
        navigationmid = (Button) findViewById(R.id.tingchejilu_navigation_mid);
        navigationmid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tingchejilujilubenyuefg1 == null) {
                    tingchejilujilubenyuefg1 = new Tingchejilujilubenyuefg1();
                }
                switchFragment(tingchejilujilubenyuefg1);
                navigationleft.setSelected(false);
                navigationmid.setSelected(true);
                navigationright.setSelected(false);
            }
        });
        navigationright = (Button) findViewById(R.id.tingchejilu_navigation_right);
        navigationright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tingchejilujilubenyuefg2 == null) {
                    tingchejilujilubenyuefg2 = new Tingchejilujilubenyuefg2();
                }
                switchFragment(tingchejilujilubenyuefg2);
                navigationleft.setSelected(false);
                navigationmid.setSelected(false);
                navigationright.setSelected(true);
            }
        });

        Intent intent = getIntent();
        String platenum = intent.getStringExtra("pid");
        String type = intent.getStringExtra("type");
        String carname = intent.getStringExtra("carname");
        String model = intent.getStringExtra("model");
        String time = intent.getStringExtra("time");
        String status = intent.getStringExtra("status");

        typetx.setText(type);
        carnametx.setText(carname);
        modeltx.setText(model);
        platenumtx.setText(platenum);
        timetx.setText(time);
        statustx.setText(status);

        if (statustx.getText().toString().contains("停车中"))
            statustx.setTextColor(Color.parseColor("#FFF13D46"));
        if (timetx.getText().toString().contains("0"))
            timetx.setTextColor(Color.parseColor("#FFF13D46"));
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
