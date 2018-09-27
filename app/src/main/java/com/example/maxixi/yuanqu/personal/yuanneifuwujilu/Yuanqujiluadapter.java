package com.example.maxixi.yuanqu.personal.yuanneifuwujilu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maxixi.yuanqu.R;

import java.util.List;

public class Yuanqujiluadapter extends RecyclerView.Adapter<Yuanqujiluadapter.ViewHolder>{

    private List<Yuanqujilulei> myList;

    //点击事件
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView time;

        public ViewHolder(View view){
            super(view);
            name=(TextView)view.findViewById(R.id.yuanqufujilu_recycler_name);
            time=(TextView)view.findViewById(R.id.yuanqufujilu_recycler_time);
        }
    }


    public Yuanqujiluadapter(List<Yuanqujilulei> yuanqujiluleiList){myList=yuanqujiluleiList;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int postion){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dctivity_yuanqufujilu_recycleritem,parent,false);
        ViewHolder holder=new ViewHolder(view);

        return  holder;
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position){
        Yuanqujilulei yuanqujilulei=myList.get(position);
        holder.name.setText(yuanqujilulei.getName());
        holder.time.setText(yuanqujilulei.getTime());

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
    public int getItemCount(){return myList.size();}
}
