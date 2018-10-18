package com.example.maxixi.yuanqu.cloud.qiyefuwu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maxixi.yuanqu.R;

import java.util.List;

public class Cloud_qiyefuwu_adapter extends RecyclerView.Adapter<Cloud_qiyefuwu_adapter.ViewHolder> {

    private List<Cloud_qiyefuwu_bean> myqiyefuwulist;

    //点击事件
    private OnItemClickListener mOnItemClickListener;
    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        TextView qid;
        TextView average_cost;
        TextView park_use_rate;
        TextView average_time;


        public ViewHolder(View view) {
            super(view);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.first_gongshangzhuce);
            qid = (TextView) view.findViewById(R.id.qiyefuwu_qid_text);
            average_cost = (TextView) view.findViewById(R.id.qiyefuwu_average_cost_text);
            park_use_rate = (TextView) view.findViewById(R.id.qiyefuwu_park_use_rate_text);
            average_time = (TextView) view.findViewById(R.id.qiyefuwu_average_time_text);
        }
    }

    public Cloud_qiyefuwu_adapter(List<Cloud_qiyefuwu_bean> qiyefuwulist) {
        myqiyefuwulist = qiyefuwulist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cctivity_cloud_qiyefuwu_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Cloud_qiyefuwu_bean cloud_qiyefuwu_bean = myqiyefuwulist.get(position);
        holder.relativeLayout.setBackgroundResource(cloud_qiyefuwu_bean.getImage());
        holder.qid.setText(cloud_qiyefuwu_bean.getQid());
        holder.average_cost.setText(cloud_qiyefuwu_bean.getAverage_cost());
        holder.park_use_rate.setText(cloud_qiyefuwu_bean.getPark_use_rate());
        holder.average_time.setText(cloud_qiyefuwu_bean.getAverage_time());

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
    }

    @Override
    public int getItemCount() {
        return myqiyefuwulist.size();
    }
}
