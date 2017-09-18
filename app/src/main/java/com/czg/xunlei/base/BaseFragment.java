package com.czg.xunlei.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.czg.xunlei.R;
import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.client.HttpClient;
import com.czg.xunlei.http.request.ApiRequest;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    AVLoadingIndicatorView mAnimLoadingView;
    private ViewGroup mRootView;
    FrameLayout mFlLoadingView;
    private View mDataView;
    FrameLayout mFlFailView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {

            mRootView = (ViewGroup) inflater.inflate(R.layout.content_root_view, container, false);
            mDataView = inflater.inflate(getLayoutId(), mRootView, false);
            mRootView.addView(mDataView);
            mFlFailView = (FrameLayout) mRootView.findViewById(R.id.fl_fail_view);
            mFlLoadingView = (FrameLayout) mRootView.findViewById(R.id.fl_loading_view);
            mAnimLoadingView = (AVLoadingIndicatorView) mRootView.findViewById(R.id.anim_loading_view);
            mFlFailView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initData();
                }
            });

            ButterKnife.bind(this, mRootView);
            initView();
            showDataView();
            initData();
        }
        return mRootView;
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected final <T> void sendHttp(final ApiRequest<T> request, final CallBack<T> callBack) {
        HttpClient.getInstances().send(request, callBack);
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
}
