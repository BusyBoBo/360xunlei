package com.czg.xunlei.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.czg.xunlei.R;
import com.czg.xunlei.activity.DetailActivity;
import com.czg.xunlei.base.BaseAdapter;
import com.czg.xunlei.base.BaseFragment;
import com.czg.xunlei.base.OnItemClickListener;
import com.czg.xunlei.base.OnItemLongClickListener;
import com.czg.xunlei.gen.Dao;
import com.czg.xunlei.model.ThumbModel;
import com.czg.xunlei.viewholder.ThumbViewHolder;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.Bind;

/**
 * @author ：czg
 * @class ：HomeFragment.class
 * @date ：2017/9/18.
 * @describe ：TODO(input describe)
 */
public class ThumbCollectFragment extends BaseFragment {

    @Bind(R.id.rec_home)
    XRecyclerView mRecHome;
    private BaseAdapter<ThumbModel> mBaseAdapter;


    @Override
    protected void initData() {
        loadData();
    }

    @Override
    protected void initView() {


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
        mBaseAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, final int position) {
                new AlertDialog.Builder(getActivity())
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (position > 0) {
                                    ThumbModel thumbModel = mBaseAdapter.getItem(position - 1);
                                    Dao.getInstance(getContext().getApplicationContext()).getThumbModelDao().delete(thumbModel);
                                    Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                                    loadData();
                                }
                            }
                        })
                        .setNegativeButton("否", null)
                        .setMessage("是否删除").show();
                return false;


            }
        });

        mRecHome.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                loadData();
                mRecHome.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mRecHome.loadMoreComplete();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    private void loadData() {
        mRecHome.setNoMore(false);//"vl_genre.php?g=da"
        mBaseAdapter.setData(Dao.getInstance(getContext()).getThumbModelDao().loadAll());
    }


}
