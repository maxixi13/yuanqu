package com.example.maxixi.yuanqu.diancan.adapter;


import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.diancan.diancan_tianjiadizhi;
import com.example.maxixi.yuanqu.diancan.model.Dizhiguanlilei;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class Dizhiguanliadapter extends RecyclerView.Adapter<Dizhiguanliadapter.ViewHolder> {

    private List<Dizhiguanlilei> mydizhiguanliList;
    private int sb = 404;

    private OnItemClickListener mOnItemClickListener;
    private OnlinearClickListener mOnlinearClickListener;
    private OnbianjiClickListener mOnbianjiClickListener;

    public void setmOnbianjiClickListener(OnbianjiClickListener mOnbianjiClickListener){
        this.mOnbianjiClickListener=mOnbianjiClickListener;
    }

    public interface OnbianjiClickListener{
        void onbianjiItem(View view,int position);
    }

    public void setOnlinearClickListener(OnlinearClickListener mOnlinearClickListener) {
        this.mOnlinearClickListener = mOnlinearClickListener;
    }

    public interface OnlinearClickListener {
        void onLinearItem(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView dianhua;
        TextView dizhi;
        LinearLayout linearLayout;
        ImageView imageView;
        LinearLayout linearLayout_big;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.diancan_queren_dizhiguanli_name);
            dianhua = (TextView) view.findViewById(R.id.diancan_queren_dizhiguanli_dianhua);
            dizhi = (TextView) view.findViewById(R.id.diancan_queren_dizhiguanli_dizhi);
            linearLayout = (LinearLayout) view.findViewById(R.id.diancan_queren_dizhiguanli_bianji);
            imageView = (ImageView) view.findViewById(R.id.diancan_queren_dizhiguanli_selectorimage);
            linearLayout_big = (LinearLayout) view.findViewById(R.id.diancan_queren_dizhiguanli_item_layout);
        }
    }

    public Dizhiguanliadapter(List<Dizhiguanlilei> dizhiguanlileiList) {
        mydizhiguanliList = dizhiguanlileiList;
    }


    @Override
    public Dizhiguanliadapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ectivity_diancan_queren_dizhiguanli_item, parent, false);
        final Dizhiguanliadapter.ViewHolder holder = new Dizhiguanliadapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Dizhiguanlilei dizhiguanlilei = mydizhiguanliList.get(position);
        holder.name.setText(dizhiguanlilei.getName());
        holder.dianhua.setText(dizhiguanlilei.getDianhua());
        holder.dizhi.setText(dizhiguanlilei.getDizhi());
        sb = mydizhiguanliList.get(position).getStatus();


        //为ItemView设置监听器
        holder.imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition(); // 1
                mOnItemClickListener.onItemClick(holder.imageView, position); // 2
//                    selector=position;
//                    notifyDataSetChanged();
//                    if (selector==position){
//                        holder.imageView.setSelected(true);
//                    }
            }
        });

        holder.linearLayout_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition(); // 1
                mOnlinearClickListener.onLinearItem(holder.linearLayout_big, position); // 2
            }
        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                mOnbianjiClickListener.onbianjiItem(holder.linearLayout,position);
            }
        });


//            holder.imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = holder.getLayoutPosition(); // 1
//                    mOnItemClickListener.onItemClick(holder.linearLayout,position);
//                    Log.e("-----",mydizhiguanliList.get(position).getAid());
//                }
//            });


//        if (selector!=position)
//        holder.imageView.setSelected(false);
        if (sb == 1) {
            holder.imageView.setSelected(true);
        }


    }

    @Override
    public int getItemCount() {
        return mydizhiguanliList.size();
    }

}
