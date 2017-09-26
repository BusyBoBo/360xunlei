package com.czg.xunlei.fragment;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.czg.xunlei.R;
import com.czg.xunlei.activity.CarToonDetailActivity;
import com.czg.xunlei.base.BaseAdapter;
import com.czg.xunlei.base.BaseFragment;
import com.czg.xunlei.base.OnItemClickListener;
import com.czg.xunlei.base.OnItemLongClickListener;
import com.czg.xunlei.gen.Dao;
import com.czg.xunlei.model.CarToonBean;
import com.czg.xunlei.viewholder.CartoonListViewHolder;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;

public class CollectCartoonFragment extends BaseFragment {
    @Bind(R.id.rec_cartoon)
    XRecyclerView rec_cartoon;
    private BaseAdapter<CarToonBean> mBaseAdapter;

    private Dao mInstance;
    private List<CarToonBean> mCarToonBeen;

    @Override
    protected void initData() {
        mInstance = Dao.getInstance(getContext().getApplicationContext());
        mCarToonBeen = mInstance.getCarToonBeanDao().loadAll();
        mBaseAdapter.setData(mCarToonBeen);
    }

    @Override
    protected void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rec_cartoon.setLayoutManager(gridLayoutManager);
        mBaseAdapter = new BaseAdapter(getActivity(), CartoonListViewHolder.class, R.layout.item_cartoon);
        rec_cartoon.setAdapter(mBaseAdapter);
        mBaseAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position > 0) {
                    CarToonBean carToonBean = mBaseAdapter.getItem(position - 1);
                    CarToonDetailActivity.startCarToonDetailActivity(getActivity(), carToonBean.getApi());
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
                                    CarToonBean carToonBean = mBaseAdapter.getItem(position - 1);
                                    Dao.getInstance(getContext().getApplicationContext()).getCarToonBeanDao().delete(carToonBean);
                                    Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                                    initData();
                                }
                            }
                        })
                        .setNegativeButton("否", null)
                        .setMessage("是否删除").show();
                return false;
            }
        });

        rec_cartoon.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initData();
                rec_cartoon.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                rec_cartoon.loadMoreComplete();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect_cartoon;
    }
}
