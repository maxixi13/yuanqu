package com.example.maxixi.yuanqu.diancan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.diancan.imp.ShopCartImp;
import com.example.maxixi.yuanqu.diancan.model.Dish;
import com.example.maxixi.yuanqu.diancan.model.ShopCart;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuerenAdapter extends RecyclerView.Adapter{

    private static String TAG = "QuerenAdapter";
    private ShopCart shopCart;
    private Context context;
    private int itemCount;
    private ArrayList<Dish> dishList;
    private ShopCartImp shopCartImp;

    public QuerenAdapter(Context context, ShopCart shopCart){
        this.shopCart = shopCart;
        this.context = context;
        this.itemCount = shopCart.getDishAccount();
        this.dishList = new ArrayList<>();
        dishList.addAll(shopCart.getShoppingSingleMap().keySet());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ectivity_diancan_queren_item, parent, false);
        DishViewHolder viewHolder = new DishViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DishViewHolder dishholder = (DishViewHolder)holder;
        final Dish dish = getDishByPosition(position);
        if(dish!=null) {
            dishholder.right_dish_name_tv.setText(dish.getDishName());
            dishholder.right_dish_price_tv.setText(dish.getDishPrice() + "");
            Glide.with(dishholder.itemView).load(dish.getDishImage()).into(((DishViewHolder) holder).right_dish_imgage);
            int num = shopCart.getShoppingSingleMap().get(dish);
            dishholder.right_dish_account_tv.setText(num+"");
        }
    }

    @Override
    public int getItemCount() {
        return this.itemCount;
    }

    public Dish getDishByPosition(int position){
        return dishList.get(position);
    }


    private class DishViewHolder extends RecyclerView.ViewHolder{
        private TextView right_dish_name_tv;
        private TextView right_dish_price_tv;
        private TextView right_dish_account_tv;
        private CircleImageView right_dish_imgage;

        public DishViewHolder(View itemView) {
            super(itemView);
            right_dish_name_tv = (TextView)itemView.findViewById(R.id.queren_dish_name);
            right_dish_price_tv = (TextView)itemView.findViewById(R.id.queren_dish_price);
            right_dish_account_tv = (TextView) itemView.findViewById(R.id.queren_dish_amount);
            right_dish_imgage=(CircleImageView)itemView.findViewById(R.id.diancan_queren_dish_imgage);
        }

    }
}