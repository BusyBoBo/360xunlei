package com.czg.xunlei.activity;

import android.content.Intent;
import android.os.Handler;

import com.czg.xunlei.MainActivity;
import com.czg.xunlei.R;
import com.czg.xunlei.base.BaseActivity;

public class LauncherActivity extends BaseActivity {


    @Override
    protected void initData() {
        setTitle("");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }

    @Override
    protected boolean isHaveBackIcon() {
        return false;
    }

    @Override
    protected boolean isHaveSettingIcon() {
        return false;
    }
}
