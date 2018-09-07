package com.example.maxixi.yuanqu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.maxixi.yuanqu.perkservice.Parkservice_yuannei;
import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.personal.tingchejilu;

public class Fragmentpark extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.bctivity_parkservice, container, false);

        ImageButton imageButton = (ImageButton) view.findViewById(R.id.parkservice_yuannei);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), Parkservice_yuannei.class);
                startActivity(intent);
            }
        });

        ImageButton tingchejilu = (ImageButton) view.findViewById(R.id.parkservice_tingchejiaofei);
        tingchejilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), com.example.maxixi.yuanqu.personal.tingchejilu.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
