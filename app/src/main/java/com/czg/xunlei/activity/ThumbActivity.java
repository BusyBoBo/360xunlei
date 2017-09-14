package com.czg.xunlei.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.czg.xunlei.R;
import com.czg.xunlei.base.BaseActivity;
import com.czg.xunlei.base.BaseAdapter;
import com.czg.xunlei.base.OnItemClickListener;
import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.request.ThumbRequest;
import com.czg.xunlei.model.ThumbModel;
import com.czg.xunlei.viewholder.ThumbViewHolder;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;

public class ThumbActivity extends BaseActivity {


    @Bind(R.id.xrecyclerview)
    XRecyclerView mXrecyclerview;
    private BaseAdapter<ThumbModel> mBaseAdapter;

    @Override
    protected void initData() {
        loadData();
    }

    private int page = 1;

    private void loadData() {
        page = 1;
        mXrecyclerview.setNoMore(false);
        sendHttp(new ThumbRequest("vl_genre.php?g=da", page), new CallBack<List<ThumbModel>>() {
            @Override
            public void onSuccess(List<ThumbModel> response) {
                mBaseAdapter.setData(response);
                mXrecyclerview.refreshComplete();
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
    }

    private void loadMoreData() {
        page++;
        sendHttp(new ThumbRequest("vl_genre.php?g=da", page), new CallBack<List<ThumbModel>>() {
            @Override
            public void onSuccess(List<ThumbModel> response) {
                if (!response.isEmpty()) {
                    mBaseAdapter.addData(response);
                    mXrecyclerview.loadMoreComplete();
                } else {
                    mXrecyclerview.setNoMore(true);
                }

            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
    }

    @Override
    protected void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mXrecyclerview.setLayoutManager(gridLayoutManager);
        mBaseAdapter = new BaseAdapter(this, ThumbViewHolder.class, R.layout.item_thumb);
        mXrecyclerview.setAdapter(mBaseAdapter);
        mBaseAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position > 0) {
                    ThumbModel thumbModel = mBaseAdapter.getItem(position - 1);
                    startActivity(new Intent(ThumbActivity.this, DetailActivity.class).putExtra("ID", thumbModel.getApi()));
                }
            }
        });
        mXrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                loadData();
            }

            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thumb;
    }


}
