package com.czg.xunlei.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.czg.xunlei.R;
import com.czg.xunlei.base.BaseAdapter;
import com.czg.xunlei.base.BaseFragment;
import com.czg.xunlei.base.OnItemClickListener;
import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.request.CarToonListRequest;
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
    private BaseAdapter<CarToonModel.CarToonBean> mBaseAdapter;
    private List<CarToonModel.CarToonPage> mPages;

    @Override
    protected void initData() {
        loadData();
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
//                if (position > 0) {
//                    ThumbModel thumbModel = mBaseAdapter.getItem(position - 1);
//                    startActivity(new Intent(getActivity(), DetailActivity.class).putExtra("ID", thumbModel.getApi()));
//                }
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
        sendHttp(new CarToonListRequest("shaonvmanhua", ""), new CallBack<CarToonModel>() {
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

        if(page==mPages.size()) {
            rec_cartoon.setNoMore(true);
            return;
        }
        sendHttp(new CarToonListRequest("shaonvmanhua", mPages.get(page).getApi()), new CallBack<CarToonModel>() {
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
