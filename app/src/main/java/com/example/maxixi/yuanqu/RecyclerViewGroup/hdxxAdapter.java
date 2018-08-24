package com.example.maxixi.yuanqu.RecyclerViewGroup;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maxixi.yuanqu.R;

import java.util.List;


public class hdxxAdapter extends RecyclerView.Adapter<hdxxAdapter.ViewHolder>{

    private List<hdxx> myhdxxList;

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
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        hdxx hdxx = myhdxxList.get(position);
        holder.hdxxImage.setImageResource(hdxx.getimageId());
        holder.hdxxText.setText(hdxx.getname());
        holder.hdxxTexttext.setText(hdxx.getnametext());
    }

    @Override
    public int getItemCount(){
        return myhdxxList.size();
    }

}