package com.example.maxixi.yuanqu.personal.yunfuwushenqingfuwu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maxixi.yuanqu.R;

import java.util.List;

public class Yunfuwujiluadapter extends RecyclerView.Adapter<Yunfuwujiluadapter.ViewHolder>{

    private List<yunfuwujilulei> myList;

    //点击事件
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView zhuangtai;
        TextView xiangmu;
        TextView time;

        public ViewHolder(View view){
            super(view);
            title=(TextView)view.findViewById(R.id.yfuwu_title);
            zhuangtai=(TextView)view.findViewById(R.id.yfuwu_zhuangtai);
            xiangmu=(TextView)view.findViewById(R.id.yfuwu_xiangmu);
            time=(TextView)view.findViewById(R.id.yfuwu_time);
        }
    }


    public Yunfuwujiluadapter(List<yunfuwujilulei> yunfuwujiluleiList){myList=yunfuwujiluleiList;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int postion){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dctivity_yunfuwushenqingfuwu_item,parent,false);
        ViewHolder holder=new ViewHolder(view);

        return  holder;
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position){
        yunfuwujilulei yunfuwujilulei=myList.get(position);
        holder.title.setText(yunfuwujilulei.getTitle());
        holder.zhuangtai.setText(yunfuwujilulei.getZhuangtai());
        holder.xiangmu.setText(yunfuwujilulei.getXiangmu());
        holder.time.setText(yunfuwujilulei.getTime());

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
