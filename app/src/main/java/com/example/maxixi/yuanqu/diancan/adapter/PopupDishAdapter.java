package com.example.maxixi.yuanqu.diancan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.diancan.imp.ShopCartImp;
import com.example.maxixi.yuanqu.diancan.model.Dish;
import com.example.maxixi.yuanqu.diancan.model.ShopCart;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PopupDishAdapter extends RecyclerView.Adapter{

    private static String TAG = "PopupDishAdapter";
    private ShopCart shopCart;
    private Context context;
    private int itemCount;
    private ArrayList<Dish> dishList;
    private ShopCartImp shopCartImp;

    public PopupDishAdapter(Context context, ShopCart shopCart){
        this.shopCart = shopCart;
        this.context = context;
        this.itemCount = shopCart.getDishAccount();
        this.dishList = new ArrayList<>();
        dishList.addAll(shopCart.getShoppingSingleMap().keySet());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ediancan_right_dish_item, parent, false);
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

            dishholder.right_dish_add_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(shopCart.addShoppingSingle(dish)) {
                        notifyItemChanged(position);
                        if(shopCartImp!=null)
                            shopCartImp.add(view,position);
                    }
                }
            });

            dishholder.right_dish_remove_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(shopCart.subShoppingSingle(dish)){
                        dishList.clear();
                        dishList.addAll(shopCart.getShoppingSingleMap().keySet());
                        itemCount = shopCart.getDishAccount();;
                        notifyDataSetChanged();
                        if(shopCartImp!=null)
                            shopCartImp.remove(view,position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.itemCount;
    }

    public Dish getDishByPosition(int position){
        return dishList.get(position);
    }

    public ShopCartImp getShopCartImp() {
        return shopCartImp;
    }

    public void setShopCartImp(ShopCartImp shopCartImp) {
        this.shopCartImp = shopCartImp;
    }

    private class DishViewHolder extends RecyclerView.ViewHolder{
        private TextView right_dish_name_tv;
        private TextView right_dish_price_tv;
        private LinearLayout right_dish_layout;
        private ImageView right_dish_remove_iv;
        private ImageView right_dish_add_iv;
        private TextView right_dish_account_tv;
        private CircleImageView right_dish_imgage;

        public DishViewHolder(View itemView) {
            super(itemView);
            right_dish_name_tv = (TextView)itemView.findViewById(R.id.right_dish_name);
            right_dish_price_tv = (TextView)itemView.findViewById(R.id.right_dish_price);
            right_dish_layout = (LinearLayout)itemView.findViewById(R.id.right_dish_item);
            right_dish_remove_iv = (ImageView)itemView.findViewById(R.id.right_dish_remove);
            right_dish_add_iv = (ImageView)itemView.findViewById(R.id.right_dish_add);
            right_dish_account_tv = (TextView) itemView.findViewById(R.id.right_dish_account);
            right_dish_imgage=(CircleImageView)itemView.findViewById(R.id.diancan_dish_imgage);
        }

    }
}
