package com.example.maxixi.yuanqu.personal.tingche;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maxixi.yuanqu.R;

import java.util.ArrayList;
import java.util.List;

public class Tingchejilujilubenyuefg2 extends Fragment{

    private List<tingchejilulei> jilulist=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view =inflater.inflate(R.layout.dctivity_tingchejilu_monthlist2,container,false);


        initjilulist();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.tingche_monthlist_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        tingchejiluadapter tingchejiluadapter = new tingchejiluadapter(jilulist);
        recyclerView.setAdapter(tingchejiluadapter);
        recyclerView.setNestedScrollingEnabled(false);

        return view;
    }

    private void initjilulist(){
        tingchejilulei madada=new tingchejilulei("2999/01/01","20:00-25:00","10000");
        jilulist.add(madada);
        tingchejilulei madada1=new tingchejilulei("2999/01/01","20:00-25:00","10000");
        jilulist.add(madada1);
        tingchejilulei madada2=new tingchejilulei("2999/01/01","20:00-25:00","10000");
        jilulist.add(madada2);
    }
}
