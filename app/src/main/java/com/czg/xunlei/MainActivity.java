package com.czg.xunlei;

import android.content.Intent;
import android.os.Bundle;

import com.czg.xunlei.activity.SettingActivity;
import com.czg.xunlei.base.BaseActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    protected void initData() {
        startActivity(new Intent(this, SettingActivity.class));
        showLoadingView();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}


