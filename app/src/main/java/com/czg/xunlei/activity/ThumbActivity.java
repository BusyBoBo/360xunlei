package com.czg.xunlei.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
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
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    private BaseAdapter<ThumbModel> mBaseAdapter;
    private String mApi;
    private ActionBarDrawerToggle mDrawerToggle;
    private String title;

    public static void startThumbActivity(Context context, String api, String title) {
        Intent intent = new Intent(context,ThumbActivity.class);
        intent.putExtra("API",api);
        intent.putExtra("TITLE",title);
        context.startActivity(intent);

    }


    @Override
    protected void initData() {
        mApi = getIntent().getStringExtra("API");
        title = getIntent().getStringExtra("TITLE");
        setTitle(title);
        if(mApi!=null) {
            loadData();
        }


    }

    public void setApi(String api,String name) {
        mApi = api;
        setTitle(name);
        loadData();
    }

    private int page = 1;

    private void loadData() {
        showLoadingView();
        page = 1;
        mXrecyclerview.setNoMore(false);//"vl_genre.php?g=da"
        sendHttp(new ThumbRequest(mApi, page), new CallBack<List<ThumbModel>>() {
            @Override
            public void onSuccess(List<ThumbModel> response) {
                if (response.isEmpty()) {
                    mXrecyclerview.setEmptyView(LayoutInflater.from(getApplicationContext()).inflate(R.layout.empty_thumb, null));
                } else {
                    mBaseAdapter.setData(response);
                    mXrecyclerview.refreshComplete();
                }
                showDataView();
            }

            @Override
            public void onFail(Throwable throwable) {
                showErrorView();
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
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer_layout, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();

        drawer_layout.setDrawerListener(mDrawerToggle);


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
