package com.czg.xunlei.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.czg.xunlei.Config;
import com.czg.xunlei.R;
import com.czg.xunlei.adapter.BaseFragmentAdapter;
import com.czg.xunlei.base.BaseFragment;
import com.czg.xunlei.model.ApiModel;
import com.czg.xunlei.model.FragmentTab;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by czg on 2017/9/26.
 */

public class HomeJavTabFragment extends BaseFragment {
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    private List<FragmentTab> list = new ArrayList<>();

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        if (list.isEmpty()) {
            for (ApiModel apiModel : Config.API) {
                HomeFragment homeFragment = HomeFragment.getInstance(apiModel.getApi());
                list.add(new FragmentTab(homeFragment, apiModel.getName()));
            }
            list.add(new FragmentTab(new ThumbCollectFragment(), "收藏"));
        }
        BaseFragmentAdapter homeAdapter = new BaseFragmentAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(homeAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homejavtab;
    }
}
