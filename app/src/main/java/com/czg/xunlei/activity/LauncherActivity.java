package com.czg.xunlei.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;

import com.czg.aoputils.permission.Permission;
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
                getPermission();
            }
        }, 1500);
    }

    @Permission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    private void getPermission() {
        Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
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
