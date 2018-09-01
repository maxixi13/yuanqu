package com.example.maxixi.yuanqu;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class HeadAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private Context ctx;
    private List<ItemBeam> list;

    public HeadAdapter(Context context, List<ItemBeam> list) {
        this.ctx = context;
        this.list = list;
    }

    //初始化head布局，这里只是为了实现功能，项目中还是要用ViewHolder封装下更节约资源
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(ctx);
        tv.setText(list.get(position).getValue());
        tv.setBackgroundColor(Color.RED);
        return tv;
    }

    //获取headView的id
    @Override
    public long getHeaderId(int position) {
        return list.get(position).getId();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //这里只是为了实现功能，项目中还是要用ViewHolder封装下更节约资源
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(ctx);
        tv.setText(list.get(position).getValue());
        return tv;
    }
}