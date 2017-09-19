package com.czg.xunlei.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author ：czg
 * @class ：XSwipeRefreshLayout.class
 * @date ：2017/9/19.
 * @describe ：XSwipeRefreshLayout
 */
public class XSwipeRefreshLayout extends SwipeRefreshLayout {
    private View mContentView;

    public XSwipeRefreshLayout(Context context) {
        super(context);
    }

    public XSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mContentView == null) {
            if (getChildCount() > 1) {
                mContentView = getChildAt(1);
                init();
            }
        }
    }

    private void init() {
        if (mContentView instanceof RecyclerView) {
            initRecyclerView((RecyclerView) mContentView);
        }


    }

    private boolean isLoading;

    private void initRecyclerView(RecyclerView contentView) {
        final RecyclerView.LayoutManager layoutManager = contentView.getLayoutManager();
        contentView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.Adapter adapter = recyclerView.getAdapter();

                if (adapter != null) {
                    int lastVisibleItemPosition ;
                    if (layoutManager instanceof LinearLayoutManager) {
                        lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else {
                        lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    }
                    if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                        Log.d("test", "loading executed");

                        boolean isRefreshing = isRefreshing();
                        if (isRefreshing) {
                            adapter.notifyItemRemoved(adapter.getItemCount());
                            return;
                        }
                        if (!isLoading) {
                            isLoading = true;
                            postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (mlistener != null) {
                                        mlistener.onLoadMore();
                                    }
                                    isLoading = false;
                                }
                            }, 1000);
                        }
                    }
                }

            }
        });


    }

    private XOnRefreshListener mlistener;

    public void setXOnRefreshListener(XOnRefreshListener listener) {
        super.setOnRefreshListener(listener);
        mlistener = listener;
    }
}
