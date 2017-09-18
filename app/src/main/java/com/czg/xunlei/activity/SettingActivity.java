package com.czg.xunlei.activity;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.czg.xunlei.Config;
import com.czg.xunlei.R;
import com.czg.xunlei.base.BaseActivity;
import com.czg.xunlei.utils.SharedPreferenceUtils;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * @author ：czg
 * @class ：SettingActivity.class
 * @date ：2017/9/15.
 * @describe ：SettingActivity
 */
public class SettingActivity extends BaseActivity {
    @Bind(R.id.switch_theme)
    Switch mSwitchTheme;
    @Bind(R.id.tv_ad_host)
    AppCompatTextView mTvAdHost;
    @Bind(R.id.tv_switch_host)
    AppCompatTextView mTvSwitchHost;
    @Bind(R.id.tv_clear_cache)
    AppCompatTextView mTvClearCache;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        int theme = SharedPreferenceUtils.get(this, Config.THEME, Config.THEME_LIGHT);
        mSwitchTheme.setChecked(theme == Config.THEME_DARK);
        mSwitchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferenceUtils.put(SettingActivity.this, Config.THEME, isChecked ? Config.THEME_DARK : Config.THEME_LIGHT);
                Intent intent = new Intent();
                intent.setAction(THEME_CHANGE);
                LocalBroadcastManager.getInstance(SettingActivity.this).sendBroadcast(intent);

        }
    });

}

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }


    @OnClick({R.id.tv_ad_host, R.id.tv_switch_host, R.id.tv_clear_cache})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_ad_host:
                break;
            case R.id.tv_switch_host:
                break;
            case R.id.tv_clear_cache:
                break;
        }
    }

}
