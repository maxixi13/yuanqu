package com.example.maxixi.yuanqu.personal.waimaijilu;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.maxixi.yuanqu.R;

import java.util.List;

import static com.example.maxixi.yuanqu.R.color.blackgray;

public class CaipinfuAdapter extends RecyclerView.Adapter<CaipinfuAdapter.ViewHolder>{

    private Context context;
    private List<Caipinfubean> mcaipinfubeanList;

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView ctime;
        RecyclerView recyclerView;
        Button querenbutton;
        TextView zhuangtaitext;


        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.waimaijilu_item_yuanqu_text);
            ctime=(TextView) view.findViewById(R.id.waimaijilu_item_ctime_text);
            recyclerView=(RecyclerView)view.findViewById(R.id.waimaijilu_item_recycler);
            querenbutton=(Button)view.findViewById(R.id.waimaijilu_item_queren_button);
            zhuangtaitext=(TextView)view.findViewById(R.id.waimaijilu_item_zhuangtai_text);
        }

    }

    public CaipinfuAdapter(Context context,List<Caipinfubean> caipinfubeanList) {
        this.context=context;
        this.mcaipinfubeanList = caipinfubeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dctivity_waimaijilu_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);


        return holder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Caipinfubean caipinfubean=mcaipinfubeanList.get(position);
        holder.textView.setText(caipinfubean.getTitle());
        holder.ctime.setText(caipinfubean.getCtime());

        CaipinAdapter caipinAdapter=new CaipinAdapter(caipinfubean.getCaipinbeanList());
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(caipinAdapter);


        //判断是否设置了监听器 点击事件
        if (mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.querenbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.querenbutton, position); // 2
                }
            });
        }

        if (caipinfubean.getStatus()==-1){
            holder.querenbutton.setVisibility(View.GONE);
            holder.zhuangtaitext.setText("已取消");
            holder.zhuangtaitext.setTextColor(context.getColor(R.color.gray));
        }else if (caipinfubean.getStatus()==0){
            holder.querenbutton.setVisibility(View.GONE);
            holder.zhuangtaitext.setText("未支付");
            holder.zhuangtaitext.setTextColor(context.getColor(R.color.red));
        }else if (caipinfubean.getStatus()==1){
            holder.querenbutton.setVisibility(View.GONE);
            holder.zhuangtaitext.setText("待接单");
        }else if (caipinfubean.getStatus()==2){
            holder.zhuangtaitext.setText("配送中");
        }else if (caipinfubean.getStatus()==3){
            holder.querenbutton.setBackground(context.getDrawable(R.drawable.personal_yuanjiaojuxingsuosuogray));
            holder.querenbutton.setText("已完成");
            holder.zhuangtaitext.setText("已完成");
            holder.zhuangtaitext.setTextColor(context.getColor(R.color.gray));
        }



    }


    @Override
    public int getItemCount() {
        return mcaipinfubeanList.size();
    }


}
