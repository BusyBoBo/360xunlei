package com.czg.xunlei.widget;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * @author ：czg
 * @class ：XOnRefreshListener.class
 * @date ：2017/9/19.
 * @describe ：XOnRefreshListener
 */
public interface XOnRefreshListener  extends SwipeRefreshLayout.OnRefreshListener {
    void onLoadMore();
}
