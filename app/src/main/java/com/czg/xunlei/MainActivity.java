package com.czg.xunlei;

import android.content.Intent;

import com.czg.xunlei.activity.ThumbActivity;
import com.czg.xunlei.base.BaseActivity;


public class MainActivity extends BaseActivity {


    @Override
    protected void initData() {
        Intent intent = new Intent(this, ThumbActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initView() {
        showLoadingView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


}


