package com.example.maxixi.yuanqu;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.maxixi.yuanqu.fragment.Fragmentcloud;
import com.example.maxixi.yuanqu.fragment.Fragmentnavigation;
import com.example.maxixi.yuanqu.fragment.Fragmentpark;
import com.example.maxixi.yuanqu.fragment.Fragmentpersonal;

public class MainActivity extends AppCompatActivity {

    //fragment
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Fragmentnavigation fragmentnavigation;
    private Fragmentpark fragmentpark;
    private Fragmentcloud fragmentcloud;
    private Fragmentpersonal fragmentpersonal;



    //底部导航栏菜单
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (fragmentnavigation == null){
                        fragmentnavigation = new Fragmentnavigation();
                    }
                    switchFragment(fragmentnavigation);
                    return true;
                case R.id.navigation_park:
                    if (fragmentpark == null){
                        fragmentpark = new Fragmentpark();
                    }
                    switchFragment(fragmentpark);
                    return true;
                case R.id.navigation_cloud:
                    if (fragmentcloud == null){
                        fragmentcloud = new Fragmentcloud();
                    }
                    switchFragment(fragmentcloud);
                    return true;
                case R.id.navigation_personal:
                    if (fragmentpersonal == null){
                        fragmentpersonal = new Fragmentpersonal();
                    }
                    switchFragment(fragmentpersonal);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //页面切换
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentnavigation = new Fragmentnavigation();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction().add(R.id.main_layout, fragmentnavigation);
        fragment = fragmentnavigation;
        transaction.commit();


     /*   //banner2
        Banner banner1=(Banner)findViewById(R.id.banner1);
        banner1.setImageLoader(new GlideImageLoader());
        Integer[] imgages1={R.drawable.personal_center_icon,R.drawable.textimage};
        banner1.setImages(Arrays.asList(imgages1));
        banner1.start();*/

    }

    //Fragment切换
    private void switchFragment(Fragment fg){
        if (fragment != fg){
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            if (!fg.isAdded()){
                transaction.hide(fragment).add(R.id.container,fg);
            }else {
                transaction.hide(fragment).show(fg);
            }
            fragment = fg;
            transaction.commit();
        }
    }


}
