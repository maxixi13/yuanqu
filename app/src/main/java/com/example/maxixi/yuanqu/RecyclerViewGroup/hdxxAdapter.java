package com.example.maxixi.yuanqu.RecyclerViewGroup;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxixi.yuanqu.R;

import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class hdxxAdapter extends RecyclerView.Adapter<hdxxAdapter.ViewHolder>{

    private List<hdxx> myhdxxList;

    //点击事件
    private OnItemClickListener mOnItemClickListener;
//    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

//    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
//        this.mOnItemLongClickListener = mOnItemLongClickListener;
//    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

//    public interface OnItemLongClickListener{
//        void onItemLongClick(View view,int position);
//    }



    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hdxxImage;
        TextView hdxxText;
        TextView hdxxTexttext;


        public ViewHolder(View view){
            super(view);
            hdxxImage=(ImageView)view.findViewById(R.id.hdxx_image);
            hdxxText=(TextView)view.findViewById(R.id.hdxx_name);
            hdxxTexttext=(TextView)view.findViewById(R.id.hdxx_name_text) ;

            //设置图片填充
            hdxxImage.setScaleType(ImageView.ScaleType.FIT_XY);

        }

    }

    public hdxxAdapter(List<hdxx>hdxxList){
        myhdxxList=hdxxList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huodongxinxi,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,int position){
        hdxx hdxx = myhdxxList.get(position);
        holder.hdxxImage.setImageResource(hdxx.getimageId());
        holder.hdxxText.setText(hdxx.getname());
        holder.hdxxTexttext.setText(hdxx.getnametext());

        //判断是否设置了监听器 点击事件
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }
//        if(mOnItemLongClickListener != null) {
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    int position = holder.getLayoutPosition();
//                    mOnItemLongClickListener.onItemLongClick(holder.itemView, position);
//                    //返回true 表示消耗了事件 事件不会继续传递
//                    return true;
//                }
//            });
//        }
//


        }

    @Override
    public int getItemCount(){
        return myhdxxList.size();
    }

}