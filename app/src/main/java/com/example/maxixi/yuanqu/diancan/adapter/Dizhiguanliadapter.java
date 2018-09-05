package com.example.maxixi.yuanqu.diancan.adapter;


import android.content.Intent;
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

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class Dizhiguanliadapter extends RecyclerView.Adapter<Dizhiguanliadapter.ViewHolder> {

    private List<Dizhiguanlilei> mydizhiguanliList;
    private int selector = -1;


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView dianhua;
        TextView dizhi;
        LinearLayout linearLayout;
        View morendizhiselect;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.diancan_queren_dizhiguanli_name);
            dianhua = (TextView) view.findViewById(R.id.diancan_queren_dizhiguanli_dianhua);
            dizhi = (TextView) view.findViewById(R.id.diancan_queren_dizhiguanli_dizhi);
            linearLayout = (LinearLayout) view.findViewById(R.id.diancan_queren_dizhiguanli_bianji);
            morendizhiselect = view;
            imageView = (ImageView) view.findViewById(R.id.diancan_queren_dizhiguanli_selectorimage);

        }
    }

    public Dizhiguanliadapter(List<Dizhiguanlilei> dizhiguanlileiList) {
        mydizhiguanliList = dizhiguanlileiList;
    }


    @Override
    public Dizhiguanliadapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ectivity_diancan_queren_dizhiguanli_item, parent, false);
        final Dizhiguanliadapter.ViewHolder holder = new Dizhiguanliadapter.ViewHolder(view);


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), diancan_tianjiadizhi.class);
                parent.getContext().startActivity(intent);
            }
        });


//        int position =holder.getAdapterPosition();
//        holder.imageView.setSelected(false);
//
//        if (selector==position){
//            notifyDataSetChanged();
//        }
//        holder.morendizhiselect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                Dizhiguanlilei dizhiguanlilei = mydizhiguanliList.get(position);
//                holder.imageView.setSelected(true);
//                selector=position;
//
//            }
//        });


        int position = holder.getAdapterPosition();
        holder.imageView.setSelected(false);

        if (selector == position) {

            holder.morendizhiselect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Dizhiguanlilei dizhiguanlilei = mydizhiguanliList.get(position);
                    holder.imageView.setSelected(true);
                    selector = position;

                }
            });

        }


        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Dizhiguanlilei dizhiguanlilei = mydizhiguanliList.get(position);
        holder.name.setText(dizhiguanlilei.getName());
        holder.dianhua.setText(dizhiguanlilei.getDianhua());
        holder.dizhi.setText(dizhiguanlilei.getDizhi());
    }

    @Override
    public int getItemCount() {
        return mydizhiguanliList.size();
    }
}
