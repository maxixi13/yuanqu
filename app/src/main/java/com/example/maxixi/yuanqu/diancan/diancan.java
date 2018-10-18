package com.example.maxixi.yuanqu.diancan;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;
import com.example.maxixi.yuanqu.diancan.adapter.LeftMenuAdapter;
import com.example.maxixi.yuanqu.diancan.adapter.RightDishAdapter;
import com.example.maxixi.yuanqu.diancan.imp.ShopCartImp;
import com.example.maxixi.yuanqu.diancan.model.Dish;
import com.example.maxixi.yuanqu.diancan.model.DishMenu;
import com.example.maxixi.yuanqu.diancan.model.ShopCart;
import com.example.maxixi.yuanqu.diancan.wiget.FakeAddImageView;
import com.example.maxixi.yuanqu.diancan.wiget.PointFTypeEvaluator;
import com.example.maxixi.yuanqu.diancan.wiget.ShopCartDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class diancan extends AppCompatActivity implements LeftMenuAdapter.onItemSelectedListener, ShopCartImp, ShopCartDialog.ShopCartDialogImp {
    private RecyclerView leftMenu;//左侧菜单栏
    private RecyclerView rightMenu;//右侧菜单栏
    private TextView headerView;
    private LinearLayout headerLayout;//右侧菜单栏最上面的菜单
    private LinearLayout bottomLayout;
    private DishMenu headMenu;
    private LeftMenuAdapter leftAdapter;
    private RightDishAdapter rightAdapter;
    private ArrayList<DishMenu> dishMenuList;//数据源
    private boolean leftClickType = false;//左侧菜单点击引发的右侧联动
    private ShopCart shopCart;
    //    private FakeAddImageView fakeAddImageView;
    private ImageView shoppingCartView;
    private FrameLayout shopingCartLayout;
    private TextView totalPriceTextView;
    private TextView totalPriceNumTextView;
    private RelativeLayout mainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ectivity_diancan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.diancan_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Button tijiaodingdanButton = (Button) findViewById(R.id.diancan_dish_tijiaodingdan);
        tijiaodingdanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tijiaodingdanIntent = new Intent();
                tijiaodingdanIntent.setClass(diancan.this, diancan_queren.class);
                tijiaodingdanIntent.putExtra("shopcart", shopCart );
                startActivity(tijiaodingdanIntent);
            }
        });

        //initData();
        initView();
        sendRequestWithOkHttp();
        //initAdapter(); 异步执行


    }

    private void initView() {
        mainLayout = (RelativeLayout) findViewById(R.id.diancan_main_layout);
        leftMenu = (RecyclerView) findViewById(R.id.left_menu);
        rightMenu = (RecyclerView) findViewById(R.id.right_menu);
        headerView = (TextView) findViewById(R.id.right_menu_tv);
        headerLayout = (LinearLayout) findViewById(R.id.right_menu_item);
//        fakeAddImageView = (FakeAddImageView)findViewById(R.id.right_dish_fake_add);
        bottomLayout = (LinearLayout) findViewById(R.id.shopping_cart_bottom);
        shoppingCartView = (ImageView) findViewById(R.id.shopping_cart);
        shopingCartLayout = (FrameLayout) findViewById(R.id.shopping_cart_layout);
        totalPriceTextView = (TextView) findViewById(R.id.shopping_cart_total_tv);
        totalPriceNumTextView = (TextView) findViewById(R.id.shopping_cart_total_num);


        leftMenu.setLayoutManager(new LinearLayoutManager(this));
        rightMenu.setLayoutManager(new LinearLayoutManager(this));

        rightMenu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1) == false) {//无法下滑
                    showHeadView();
                    return;
                }
                View underView = null;
                if (dy > 0)
                    underView = rightMenu.findChildViewUnder(headerLayout.getX(), headerLayout.getMeasuredHeight() + 1);
                else
                    underView = rightMenu.findChildViewUnder(headerLayout.getX(), 0);
                if (underView != null && underView.getContentDescription() != null) {
                    int position = Integer.parseInt(underView.getContentDescription().toString());
                    DishMenu menu = rightAdapter.getMenuOfMenuByPosition(position);

                    if (leftClickType || !menu.getMenuName().equals(headMenu.getMenuName())) {
                        if (dy > 0 && headerLayout.getTranslationY() <= 1 && headerLayout.getTranslationY() >= -1 * headerLayout.getMeasuredHeight() * 4 / 5 && !leftClickType) {// underView.getTop()>9
                            int dealtY = underView.getTop() - headerLayout.getMeasuredHeight();
                            headerLayout.setTranslationY(dealtY);
//                            Log.e("diancan", "onScrolled: "+headerLayout.getTranslationY()+"   "+headerLayout.getBottom()+"  -  "+headerLayout.getMeasuredHeight() );
                        } else if (dy < 0 && headerLayout.getTranslationY() <= 0 && !leftClickType) {
                            headerView.setText(menu.getMenuName());
                            int dealtY = underView.getBottom() - headerLayout.getMeasuredHeight();
                            headerLayout.setTranslationY(dealtY);
//                            Log.e("diancan", "onScrolled: "+headerLayout.getTranslationY()+"   "+headerLayout.getBottom()+"  -  "+headerLayout.getMeasuredHeight() );
                        } else {
                            headerLayout.setTranslationY(0);
                            headMenu = menu;
                            headerView.setText(headMenu.getMenuName());
                            for (int i = 0; i < dishMenuList.size(); i++) {
                                if (dishMenuList.get(i) == headMenu) {
                                    leftAdapter.setSelectedNum(i);
                                    break;
                                }
                            }
                            if (leftClickType) leftClickType = false;
                            //Log.e(TAG, "onScrolled: " + menu.getMenuName());
                        }
                    }
                }
            }
        });

        shopingCartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCart(view);
            }
        });
    }


    private void initAdapter() {
        leftAdapter = new LeftMenuAdapter(this, dishMenuList);
        rightAdapter = new RightDishAdapter(this, dishMenuList, shopCart);
        rightMenu.setAdapter(rightAdapter);
        leftMenu.setAdapter(leftAdapter);
        leftAdapter.addItemSelectedListener(this);
        rightAdapter.setShopCartImp(this);
        initHeadView();
    }

    private void initHeadView() {
        headMenu = rightAdapter.getMenuOfMenuByPosition(0);
        headerLayout.setContentDescription("0");
        headerView.setText(headMenu.getMenuName());
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        leftAdapter.removeItemSelectedListener(this);
//    }

    private void showHeadView() {
        headerLayout.setTranslationY(0);
        View underView = rightMenu.findChildViewUnder(headerView.getX(), 0);
        if (underView != null && underView.getContentDescription() != null) {
            int position = Integer.parseInt(underView.getContentDescription().toString());
            DishMenu menu = rightAdapter.getMenuOfMenuByPosition(position + 1);
            headMenu = menu;
            headerView.setText(headMenu.getMenuName());
            for (int i = 0; i < dishMenuList.size(); i++) {
                if (dishMenuList.get(i) == headMenu) {
                    leftAdapter.setSelectedNum(i);
                    break;
                }
            }
        }
    }

    @Override
    public void onLeftItemSelected(int position, DishMenu menu) {
        int sum = 0;
        for (int i = 0; i < position; i++) {
            sum += dishMenuList.get(i).getDishList().size() + 1;
        }
        LinearLayoutManager layoutManager = (LinearLayoutManager) rightMenu.getLayoutManager();
        layoutManager.scrollToPositionWithOffset(sum, 0);
        leftClickType = true;
    }

    @Override
    public void add(View view, int position) {
        int[] addLocation = new int[2];
        int[] cartLocation = new int[2];
        int[] recycleLocation = new int[2];
        view.getLocationInWindow(addLocation);
        shoppingCartView.getLocationInWindow(cartLocation);
        rightMenu.getLocationInWindow(recycleLocation);

        PointF startP = new PointF();
        PointF endP = new PointF();
        PointF controlP = new PointF();

        startP.x = addLocation[0];
        startP.y = addLocation[1] - recycleLocation[1];
        endP.x = cartLocation[0];
        endP.y = cartLocation[1] - recycleLocation[1];
        controlP.x = endP.x;
        controlP.y = startP.y;

        final FakeAddImageView fakeAddImageView = new FakeAddImageView(this);
        mainLayout.addView(fakeAddImageView);
        fakeAddImageView.setImageResource(R.drawable.ic_add_circle_blue_700_36dp);
        fakeAddImageView.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
        fakeAddImageView.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
        fakeAddImageView.setVisibility(View.VISIBLE);
        ObjectAnimator addAnimator = ObjectAnimator.ofObject(fakeAddImageView, "mPointF",
                new PointFTypeEvaluator(controlP), startP, endP);
        addAnimator.setInterpolator(new AccelerateInterpolator());
        addAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                fakeAddImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                fakeAddImageView.setVisibility(View.GONE);
                mainLayout.removeView(fakeAddImageView);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        ObjectAnimator scaleAnimatorX = new ObjectAnimator().ofFloat(shoppingCartView, "scaleX", 0.6f, 1.0f);
        ObjectAnimator scaleAnimatorY = new ObjectAnimator().ofFloat(shoppingCartView, "scaleY", 0.6f, 1.0f);
        scaleAnimatorX.setInterpolator(new AccelerateInterpolator());
        scaleAnimatorY.setInterpolator(new AccelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleAnimatorX).with(scaleAnimatorY).after(addAnimator);
        animatorSet.setDuration(800);
        animatorSet.start();

        showTotalPrice();
    }

    @Override
    public void remove(View view, int position) {
        showTotalPrice();
    }

    private void showTotalPrice() {
        if (shopCart != null && shopCart.getShoppingTotalPrice() > 0) {
            totalPriceTextView.setVisibility(View.VISIBLE);
            totalPriceTextView.setText("￥ " + shopCart.getShoppingTotalPrice());
            totalPriceNumTextView.setVisibility(View.VISIBLE);
            totalPriceNumTextView.setText("" + shopCart.getShoppingAccount());

        } else {
            totalPriceTextView.setVisibility(View.GONE);
            totalPriceNumTextView.setVisibility(View.GONE);
        }
    }

    private void showCart(View view) {
        if (shopCart != null && shopCart.getShoppingAccount() > 0) {
            final ShopCartDialog dialog = new ShopCartDialog(this, shopCart, R.style.cartdialog);
            Window window = dialog.getWindow();
            dialog.setShopCartDialogImp(this);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            dialog.show();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.BOTTOM;
            params.dimAmount = 0.5f;
            window.setAttributes(params);

        }
    }

    @Override
    public void dialogDismiss() {
        showTotalPrice();
        rightAdapter.notifyDataSetChanged();
    }



    private void sendRequestWithOkHttp() {
        shopCart = new ShopCart();
        dishMenuList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(getString(R.string.waimaishouye_url)).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        Iterator<String> keys = jsonObject1.keys();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            JSONArray array = jsonObject1.getJSONArray(key);
                            ArrayList<Dish> dish = new ArrayList<>();

                            for (int i = 0; i < array.length(); ++i) {
                                JSONObject jsonObjectchild = array.getJSONObject(i);
                                dish.add(new Dish(jsonObjectchild.getString("name"), jsonObjectchild.getDouble("price"), jsonObjectchild.getInt("hot"),getString(R.string.waimaishouye_image_url)+jsonObjectchild.getString("food_img")));
                            }
                            DishMenu menu = new DishMenu(key, dish);
                            dishMenuList.add(menu);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    diancan.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initAdapter();
                        }
                    });

                } catch (
                        Exception e)

                {
                    e.printStackTrace();
                }
            }
        }).start();
    }




}



//    private void initData() {
//        shopCart = new ShopCart();
//        dishMenuList = new ArrayList<>();
//        ArrayList<Dish> dishs1 = new ArrayList<>();
//        dishs1.add(new Dish("面包", 3.0, 10, R.drawable.textkele));
//        dishs1.add(new Dish("蛋挞", 1.0, 10, R.drawable.textkele));
//        dishs1.add(new Dish("牛奶", 4.0, 10, R.drawable.textkele));
//        dishs1.add(new Dish("肠粉", 1.0, 10, R.drawable.textkele));
//        dishs1.add(new Dish("绿茶饼", 1.0, 10, R.drawable.textkele));
//        dishs1.add(new Dish("花卷", 1.0, 10, R.drawable.textkele));
//        dishs1.add(new Dish("包子", 1.0, 10, R.drawable.textkele));
//        DishMenu menu1 = new DishMenu("盖浇饭", dishs1);
//
//        ArrayList<Dish> dishs2 = new ArrayList<>();
//        dishs2.add(new Dish("粥", 1.0, 10, R.drawable.textkele));
//        dishs2.add(new Dish("炒饭", 1.0, 10, R.drawable.textkele));
//        dishs2.add(new Dish("炒米粉", 1.0, 10, R.drawable.textkele));
//        dishs2.add(new Dish("炒粿条", 1.0, 10, R.drawable.textkele));
//        dishs2.add(new Dish("炒牛河", 1.0, 10, R.drawable.textkele));
//        dishs2.add(new Dish("炒菜", 1.0, 10, R.drawable.textkele));
//        DishMenu menu2 = new DishMenu("配菜", dishs2);
//
//        ArrayList<Dish> dishs3 = new ArrayList<>();
//        dishs3.add(new Dish("番茄炒蛋", 1.0, 10, R.drawable.textkele));
//        dishs3.add(new Dish("牛肉炒羊肉", 1.0, 10, R.drawable.textkele));
//        dishs3.add(new Dish("牛排", 1.0, 10, R.drawable.textkele));
//        dishs3.add(new Dish("鸡排", 1.0, 10, R.drawable.textkele));
//        dishs3.add(new Dish("猪排", 1.0, 10, R.drawable.textkele));
//        DishMenu menu3 = new DishMenu("炒菜", dishs3);
//
//        ArrayList<Dish> dishs4 = new ArrayList<>();
//        dishs4.add(new Dish("牛肉面", 1.0, 10, R.drawable.textkele));
//        dishs4.add(new Dish("蛋炒饭", 1.0, 10, R.drawable.textkele));
//        dishs4.add(new Dish("馄炖", 1.0, 10, R.drawable.textkele));
//        dishs4.add(new Dish("水饺", 1.0, 10, R.drawable.textkele));
//        dishs4.add(new Dish("馒头", 1.0, 10, R.drawable.textkele));
//        dishs4.add(new Dish("包子", 1.0, 10, R.drawable.textkele));
//        DishMenu menu4 = new DishMenu("主食", dishs4);
//
//        ArrayList<Dish> dishs5 = new ArrayList<>();
//        dishs5.add(new Dish("可乐", 1.0, 10, R.drawable.textkele));
//        dishs5.add(new Dish("雪碧", 1.0, 10, R.drawable.textkele));
//        dishs5.add(new Dish("橙汁", 1.0, 10, R.drawable.textkele));
//        dishs5.add(new Dish("椰奶", 1.0, 10, R.drawable.textkele));
//        dishs5.add(new Dish("啤酒", 1.0, 10, R.drawable.textkele));
//        dishs5.add(new Dish("二锅头", 1.0, 10, R.drawable.textkele));
//        DishMenu menu5 = new DishMenu("饮料", dishs5);
//
//
//        dishMenuList.add(menu1);
//        dishMenuList.add(menu2);
//        dishMenuList.add(menu3);
//        dishMenuList.add(menu4);
//        dishMenuList.add(menu5);
//    }


//    private ArrayList<Dish> dish = new ArrayList<>();
//    private void sendRequestWithOkHttp() {
//        try {
//            JSONObject jsonObject = new JSONObject(jjson);
//            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
//            Iterator<String> keys =  jsonObject1.keys();
//            while (keys.hasNext()) {
//                String key = keys.next();
//                JSONArray array= jsonObject1.getJSONArray(key);
//
//                for (int i = 0; i<array.length(); ++i){
//                    JSONObject jsonObjectchild=array.getJSONObject(i);
//                    dish.add(new Dish(jsonObjectchild.getString("mid"), 1.0, 10, R.drawable.textimage));
//                    Log.e(" "," "+dishMenuList);
//
//                }
//                DishMenu menu=new DishMenu(key,dish);
//                dishMenuList.add(menu);
//            }
////            diancan.this.runOnUiThread(new Runnable() {
////                @Override
////                public void run() {
////                    leftAdapter = new LeftMenuAdapter(diancan.this, dishMenuList);
////                    rightAdapter = new RightDishAdapter(diancan.this, dishMenuList, shopCart);
////                    rightMenu.setAdapter(rightAdapter);
////                    leftMenu.setAdapter(leftAdapter);
////                }
////            });
//            //initAdapter();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ectivity_diancan);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.diancan_toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//    }
//

