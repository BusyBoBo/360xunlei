package com.czg.xunlei.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.czg.xunlei.R;
import com.czg.xunlei.activity.DetailActivity;
import com.czg.xunlei.base.BaseAdapter;
import com.czg.xunlei.base.BaseFragment;
import com.czg.xunlei.base.OnItemClickListener;
import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.request.HomeRequest;
import com.czg.xunlei.model.ThumbModel;
import com.czg.xunlei.viewholder.ThumbViewHolder;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;

/**
 * @author ：czg
 * @class ：HomeFragment.class
 * @date ：2017/9/18.
 * @describe ：TODO(input describe)
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.rec_home)
    XRecyclerView mRecHome;
    private BaseAdapter<ThumbModel> mBaseAdapter;
    String mApi;

    public static HomeFragment getInstance(String api) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("API", api);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }


    @Override
    protected void initData() {
        showLoadingView();
        loadData();
    }

    @Override
    protected void initView() {
        mApi = getArguments().getString("API");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecHome.setLayoutManager(gridLayoutManager);
        mBaseAdapter = new BaseAdapter(getActivity(), ThumbViewHolder.class, R.layout.item_thumb);
        mRecHome.setAdapter(mBaseAdapter);
        mBaseAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position > 0) {
                    ThumbModel thumbModel = mBaseAdapter.getItem(position - 1);
                    startActivity(new Intent(getActivity(), DetailActivity.class).putExtra("ID", thumbModel.getApi()));
                }
            }
        });
        mRecHome.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        return R.layout.fragment_home;
    }

    int page = -1;

    private void loadData() {
        page = 1;
        mRecHome.setNoMore(false);//"vl_genre.php?g=da"
        sendHttp(new HomeRequest(mApi, page), new CallBack<List<ThumbModel>>() {
            @Override
            public void onSuccess(List<ThumbModel> response) {
                if (response.isEmpty()) {
                    mRecHome.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.empty_thumb, null));
                } else {
                    mBaseAdapter.setData(response);
                    mRecHome.refreshComplete();
                }
                showDataView();
            }

            @Override
            public void onFail(Throwable throwable) {
                throwable.printStackTrace();
                showErrorView();
            }
        });
    }

    private void loadMoreData() {
        page++;
        sendHttp(new HomeRequest(mApi, page), new CallBack<List<ThumbModel>>() {
            @Override
            public void onSuccess(List<ThumbModel> response) {
                if (!response.isEmpty()) {
                    mBaseAdapter.addData(response);
                    mRecHome.loadMoreComplete();
                } else {
                    mRecHome.setNoMore(true);
                }

            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
    }

}
