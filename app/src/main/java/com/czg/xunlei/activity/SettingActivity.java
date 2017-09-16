package com.czg.xunlei.activity;

import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.czg.xunlei.Config;
import com.czg.xunlei.R;
import com.czg.xunlei.base.BaseActivity;
import com.czg.xunlei.utils.LogUtils;
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
    EditText ed;
    @Override
    protected void initData() {
        ed = (EditText)findViewById(R.id.ed);
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                for (int i=0;i<s.length();i++){
                    int in = s.charAt(i);
                    Log.e("aaaa","asii_i"+in);
                    Log.e("aaaa","asii_c"+(char)in);

                }

                LogUtils.e(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
     //   startActivity(new Intent(this, SettingsActivity.class));
    }


    @Override
    protected void initView() {
        int theme = SharedPreferenceUtils.get(this, Config.THEME, Config.THEME_LIGHT);
        mSwitchTheme.setChecked(theme == Config.THEME_DARK);
        mSwitchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferenceUtils.put(SettingActivity.this, Config.THEME, isChecked ? Config.THEME_DARK : Config.THEME_LIGHT);
                recreate();
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
