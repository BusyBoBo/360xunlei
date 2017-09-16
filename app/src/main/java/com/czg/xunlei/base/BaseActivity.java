package com.czg.xunlei.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.czg.xunlei.Config;
import com.czg.xunlei.R;
import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.client.HttpClient;
import com.czg.xunlei.http.request.ApiRequest;
import com.czg.xunlei.utils.SharedPreferenceUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.anim_loading_view)
    AVLoadingIndicatorView mAnimLoadingView;
    @Bind(R.id.fl_loading_view)
    FrameLayout mFlLoadingView;
    @Bind(R.id.fl_fail_view)
    FrameLayout mFlFailView;
    private ViewGroup mRootview;
    private View mDataView;

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
        mDataView = LayoutInflater.from(this).inflate(getLayoutId(), mRootview, true);
        setContentView(mRootview);
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
    }

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
}
