package com.czg.xunlei.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.czg.xunlei.R;
import com.czg.xunlei.activity.ThumbActivity;
import com.czg.xunlei.base.BaseAdapter;
import com.czg.xunlei.base.BaseFragment;
import com.czg.xunlei.base.OnItemClickListener;
import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.request.TypeRequest;
import com.czg.xunlei.model.TypeModel;
import com.czg.xunlei.viewholder.TypeMenuViewHolder;

import java.util.List;

import butterknife.Bind;

/**
 * @author ：czg
 * @class ：TypeMenuFragment.class
 * @date ：2017/9/14.
 * @describe ：TypeMenuFragment
 */
public class TypeMenuFragment extends BaseFragment implements OnItemClickListener {
    @Bind(R.id.rec_type_menu)
    RecyclerView mRecTypeMenu;
    private BaseAdapter<TypeModel> mAdapter;

    @Override
    protected void initData() {
        sendHttp(new TypeRequest(), new CallBack<List<TypeModel>>() {
            @Override
            public void onSuccess(List<TypeModel> response) {
                mAdapter.setData(response);
            }

            @Override
            public void onFail(Throwable throwable) {
                Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initView() {
        mRecTypeMenu.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mAdapter = new BaseAdapter<>(getActivity(), TypeMenuViewHolder.class, R.layout.item_type_menu);
        mRecTypeMenu.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_type_menu;
    }


    @Override
    public void onItemClick(View view, int position) {
        ThumbActivity activity = (ThumbActivity) getActivity();
        activity.setApi(mAdapter.getItem(position).getApi());
    }
}
