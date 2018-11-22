package com.example.maxixi.yuanqu.personal.tingche;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maxixi.yuanqu.R;

import java.util.List;

import static android.support.constraint.Constraints.TAG;
import static com.example.maxixi.yuanqu.R.color.colorAccent;
import static com.example.maxixi.yuanqu.R.color.top_blue;


public class TingchecheliangAdapter extends RecyclerView.Adapter<TingchecheliangAdapter.ViewHolder> {

    //    监听事件
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    private List<Tingchecheliang> myList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView type;
        TextView carname;
        TextView model;
        TextView platenum;
        TextView time;
        TextView state;
        TextView shengyu;



        public ViewHolder(View view) {
            super(view);
            type = (TextView) view.findViewById(R.id.tingche_type);
            carname = (TextView) view.findViewById(R.id.tingche_carname);
            model = (TextView) view.findViewById(R.id.tingche_model);
            platenum = (TextView) view.findViewById(R.id.tingche_platenum);
            time = (TextView) view.findViewById(R.id.tingche_time);
            state = (TextView) view.findViewById(R.id.tingche_state);
            shengyu=(TextView)view.findViewById(R.id.tingchejilu_item_shengyu);

        }

    }

    public TingchecheliangAdapter(List<Tingchecheliang> tingcheList, Context context) {
        this.myList = tingcheList;
        this.context=context;
    }

    @Override
    public TingchecheliangAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dctivity_tingchejilu_item, parent, false);
        final TingchecheliangAdapter.ViewHolder holder = new TingchecheliangAdapter.ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Tingchecheliang tingchecheliang = myList.get(position);
        holder.type.setText(tingchecheliang.getType());
        holder.carname.setText(tingchecheliang.getCarname());
        holder.model.setText(tingchecheliang.getModel());
        holder.platenum.setText(tingchecheliang.getPlatenum());
//        holder.time.setText(tingchecheliang.getTime());
        holder.state.setText(tingchecheliang.getState());
        if (tingchecheliang.getState().contains("中")) holder.state.setTextColor(context.getColor(R.color.red));
        if (tingchecheliang.getTime().contains("0"))holder.time.setTextColor(context.getColor(R.color.red));

        String gettimelinshi=tingchecheliang.getTime()+"次";
        String gettimeyueka=tingchecheliang.getTime()+"天";
        if (tingchecheliang.getType().contains("临")){
            holder.shengyu.setText("停车次数");
            holder.time.setText(gettimelinshi);
        }else if(tingchecheliang.getType().contains("月")){
            holder.time.setText(gettimeyueka);
        }


        //判断是否设置了监听器 点击事件
        if (mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


}
