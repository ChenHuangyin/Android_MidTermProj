package com.dehe.android_midtermproj;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout ll_people;
    private LinearLayout ll_store;
    private LinearLayout ll_history;

    private ImageView iv_people;
    private ImageView iv_store;
    private ImageView iv_history;

    private TextView tv_people;
    private TextView tv_store;
    private TextView tv_history;

    private Fragment homeFragment;
    private Fragment historyFragment;
    private Fragment storeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initFragment(0);

    }
    private void initFragment(int index){
        // 由于是引用了V4包下的Fragment，所以这里的管理器要用getSupportFragmentManager获取
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 隐藏所有Fragment
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.main, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (storeFragment == null) {
                    storeFragment = new StoreFragment();
                    transaction.add(R.id.main, storeFragment);
                } else {
                    transaction.show(storeFragment);
                }

                break;
            case 2:
                if (historyFragment == null) {
                    historyFragment = new HistoryFragment();
                    transaction.add(R.id.main, historyFragment);
                } else {
                    transaction.show(historyFragment);
                }

                break;
            default:
                break;
        }
        // 提交事务
        transaction.commit();
    }
    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (storeFragment != null) {
            transaction.hide(storeFragment);
        }
        if (historyFragment != null) {
            transaction.hide(historyFragment);
        }

    }
    private void initEvent() {
        // 设置按钮监听
        ll_people.setOnClickListener(this);
        ll_store.setOnClickListener(this);
        ll_history.setOnClickListener(this);

    }
    private void initView(){
        ll_people=(LinearLayout)findViewById(R.id.ll_people);
        ll_store=(LinearLayout)findViewById(R.id.ll_store);
        ll_history=(LinearLayout)findViewById(R.id.ll_history);
        iv_people=(ImageView)findViewById(R.id.iv_people);
        iv_store=(ImageView)findViewById(R.id.iv_store);
        iv_history=(ImageView)findViewById(R.id.iv_history);
        tv_people=(TextView) findViewById(R.id.tv_people);
        tv_store=(TextView)findViewById(R.id.tv_store);
        tv_history=(TextView)findViewById(R.id.tv_history);
    }
    @Override
    public void onClick(View v) {
        // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
        restartBotton();
        // ImageView和TetxView置为绿色，页面随之跳转
        switch (v.getId()) {
            case R.id.ll_people:
                iv_people.setImageResource(R.drawable.full_mainpage);
                tv_people.setTextColor(Color.rgb(0,0,0));
                initFragment(0);
                break;
            case R.id.ll_store:
                iv_store.setImageResource(R.drawable.full_star);
                tv_store.setTextColor(Color.rgb(0,0,0));
                initFragment(1);
                break;
            case R.id.ll_history:
                iv_history.setImageResource(R.drawable.full_foot);
                tv_history.setTextColor(Color.rgb(0,0,0));
                initFragment(2);
                break;

            default:
                break;
        }
    }

    private void restartBotton() {
        // ImageView置为灰色
        iv_people.setImageResource(R.drawable.empty_mainpage);
        iv_store.setImageResource(R.drawable.empty_star);
        iv_history.setImageResource(R.drawable.empty_foot);
        // TextView置为白色
        tv_people.setTextColor(Color.rgb(216,213,213));
        tv_store.setTextColor(Color.rgb(216,213,213));
        tv_history.setTextColor(Color.rgb(216,213,213));
    }
}
