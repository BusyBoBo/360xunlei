package com.czg.xunlei.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.czg.xunlei.R;
import com.czg.xunlei.activity.CarToonDetailActivity;
import com.czg.xunlei.base.BaseAdapter;
import com.czg.xunlei.base.BaseFragment;
import com.czg.xunlei.base.OnItemClickListener;
import com.czg.xunlei.base.OnItemLongClickListener;
import com.czg.xunlei.gen.CarToonBeanDao;
import com.czg.xunlei.gen.Dao;
import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.request.CarToonListRequest;
import com.czg.xunlei.model.CarToonBean;
import com.czg.xunlei.model.CarToonModel;
import com.czg.xunlei.viewholder.CartoonListViewHolder;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;

/**
 * @author ：czg
 * @class ：CartoonFragment.class
 * @date ：2017/9/22.
 * @describe ：TODO(input describe)
 */
public class CartoonListFragment extends BaseFragment {
    @Bind(R.id.rec_cartoon)
    XRecyclerView rec_cartoon;
    private BaseAdapter<CarToonBean> mBaseAdapter;
    private List<CarToonModel.CarToonPage> mPages;
    private String mApi;

    public static CartoonListFragment getInstance(String api) {
        CartoonListFragment cartoonListFragment = new CartoonListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("API", api);
        cartoonListFragment.setArguments(bundle);
        return cartoonListFragment;
    }

    @Override
    protected void initData() {
        loadData();
        showLoadingView();
    }

    @Override
    protected void initView() {
        mApi = getArguments().getString("API");
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
                                    List<CarToonBean> list = Dao.getInstance(getContext().getApplicationContext()).getCarToonBeanDao().queryBuilder().where(CarToonBeanDao.Properties.Api.eq(carToonBean.getApi())).build().list();
                                    if (list.isEmpty()) {
                                        Dao.getInstance(getContext().getApplicationContext()).getCarToonBeanDao().save(carToonBean);
                                        Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getActivity(), "已添加到收藏", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            }
                        })
                        .setNegativeButton("否", null)
                        .setMessage("是否添加到收藏").show();
                return false;
            }
        });


        rec_cartoon.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        return R.layout.fragment_cartoon;
    }

    int page = 0;

    private void loadData() {
        page = 0;
        rec_cartoon.setNoMore(false);//"vl_genre.php?g=da"
        sendHttp(new CarToonListRequest(mApi, ""), new CallBack<CarToonModel>() {
            @Override
            public void onSuccess(CarToonModel response) {
                if (mPages == null) {
                    mPages = response.getPages();
                }
                if (response.getCarToonBeen().isEmpty()) {
                    rec_cartoon.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.empty_thumb, null));
                } else {
                    mBaseAdapter.setData(response.getCarToonBeen());
                }
                rec_cartoon.refreshComplete();
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
        if (mPages == null) {
            return;
        }
        page++;

        if (page == mPages.size()) {
            rec_cartoon.setNoMore(true);
            return;
        }
        sendHttp(new CarToonListRequest(mApi, mPages.get(page).getApi()), new CallBack<CarToonModel>() {
            @Override
            public void onSuccess(CarToonModel response) {
                if (!response.getCarToonBeen().isEmpty()) {
                    mBaseAdapter.addData(response.getCarToonBeen());
                    rec_cartoon.loadMoreComplete();
                } else {
                    rec_cartoon.setNoMore(true);
                }

            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
    }
}
