package com.czg.xunlei.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.czg.xunlei.Config;
import com.czg.xunlei.R;
import com.czg.xunlei.activity.SettingActivity;
import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.client.HttpClient;
import com.czg.xunlei.http.request.ApiRequest;
import com.czg.xunlei.utils.SharedPreferenceUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BaseActivity extends AppCompatActivity {

    AVLoadingIndicatorView mAnimLoadingView;
    FrameLayout mFlLoadingView;
    private View mDataView;
    FrameLayout mFlFailView;

    private ViewGroup mRootview;

    protected static final String THEME_CHANGE = "theme_change";
    private Toolbar tool_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //  setTranslucentStatus(this, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        //  tintManager.setStatusBarTintEnabled(true);
        // 使用颜色资源
        int theme = SharedPreferenceUtils.get(this, Config.THEME, Config.THEME_LIGHT);
        setTheme(theme == Config.THEME_LIGHT ? R.style.AppTheme : R.style.AppTheme_Dark);
        mRootview = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.root_view, null);
        ViewGroup contentView = (ViewGroup) mRootview.findViewById(R.id.fl_content_view);
        mDataView = LayoutInflater.from(this).inflate(getLayoutId(), mRootview, false);
        contentView.addView(mDataView);
        setContentView(mRootview);
        tool_bar = (Toolbar) findViewById(R.id.tool_bar);
        tool_bar.setTitleTextColor(Color.WHITE);
        if (isHaveToolBar()) {
            setSupportActionBar(tool_bar);
            initNavigation();
        } else {
            tool_bar.setVisibility(View.GONE);
        }



        mFlFailView = (FrameLayout) findViewById(R.id.fl_fail_view);
        mFlLoadingView = (FrameLayout) findViewById(R.id.fl_loading_view);
        mAnimLoadingView = (AVLoadingIndicatorView) findViewById(R.id.anim_loading_view);
        ButterKnife.bind(this);
        mFlFailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
        showDataView();
        initView();
        initData();
        IntentFilter filter = new IntentFilter();
        filter.addAction(THEME_CHANGE);
        LocalBroadcastManager.getInstance(this).registerReceiver(themeChangeReceiver, filter);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_setting:
                    Intent intent = new Intent(BaseActivity.this, SettingActivity.class);
                    startActivity(intent);
                    break;
            }
            return true;
        }
    };


    private void initNavigation() {
        if (isHaveBackIcon()) {
            tool_bar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
            tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        if (isHaveSettingIcon()) {
            tool_bar.setOnMenuItemClickListener(onMenuItemClick);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!isHaveSettingIcon()) {
            return super.onCreateOptionsMenu(menu);
        }
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    protected boolean isHaveBackIcon() {
        return true;
    }

    protected boolean isHaveSettingIcon() {
        return true;
    }

    protected boolean isHaveToolBar() {
        return true;
    }

    private BroadcastReceiver themeChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            recreate();
        }
    };

    protected void showDataView() {
        mDataView.setVisibility(View.VISIBLE);
        mFlFailView.setVisibility(View.GONE);
        mFlLoadingView.setVisibility(View.GONE);
    }

    protected void showErrorView() {
        mDataView.setVisibility(View.GONE);
        mFlFailView.setVisibility(View.VISIBLE);
        mFlLoadingView.setVisibility(View.GONE);
    }

    protected void showLoadingView() {
        mDataView.setVisibility(View.GONE);
        mFlFailView.setVisibility(View.GONE);
        mFlLoadingView.setVisibility(View.VISIBLE);
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected final <T> void sendHttp(final ApiRequest<T> request, final CallBack<T> callBack) {
        HttpClient.getInstances().send(request, callBack);
    }

    @TargetApi(19)
    private void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @OnClick(R.id.fl_fail_view)
    public void onViewClicked() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(themeChangeReceiver);
    }

    @Override
    public void setTitle(CharSequence title) {
        tool_bar.setTitle(title);
        setSupportActionBar(tool_bar);
        initNavigation();
    }
}
