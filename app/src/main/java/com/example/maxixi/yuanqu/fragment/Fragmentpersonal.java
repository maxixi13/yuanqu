package com.example.maxixi.yuanqu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.personal.renzhengqiye;
import com.example.maxixi.yuanqu.personal.tingche.tingchejilujilu;
import com.example.maxixi.yuanqu.personal.waimaijilu;


public class Fragmentpersonal extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.dctivity_personal, container, false);



        ImageButton tingchejilu=(ImageButton)view.findViewById(R.id.personal_tingchejilu);
        tingchejilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),tingchejilujilu.class);
                startActivity(intent);
            }
        });




        ImageButton waimaijilu = (ImageButton) view.findViewById(R.id.personal_waimaijilu);
        waimaijilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), waimaijilu.class);
                startActivity(intent);
            }
        });


        TextView chengweiqiye = (TextView) view.findViewById(R.id.personal_chengweiqiye);
        chengweiqiye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), renzhengqiye.class);
                startActivity(intent);
            }
        });


        return view;
    }
}