package com.czg.xunlei;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.czg.xunlei.adapter.HomeAdapter;
import com.czg.xunlei.base.BaseActivity;
import com.czg.xunlei.base.BaseFragment;
import com.czg.xunlei.fragment.CartoonListFragment;
import com.czg.xunlei.fragment.HomeFragment;
import com.czg.xunlei.model.ApiModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class MainActivity extends BaseActivity {
    @Bind(R.id.tool_bar2)
    Toolbar tool_bar2;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    private List<BaseFragment> list = new ArrayList<>();
    ;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tool_bar = tool_bar2;
        tool_bar.setTitleTextColor(Color.WHITE);
        setTitle("Home");
        ArrayList<ApiModel> apiModels = new ArrayList<>();
        apiModels.add(new ApiModel("漫画", ""));
        apiModels.addAll(Config.API);

        if (list.isEmpty()) {
            list.add(new CartoonListFragment());
            for (ApiModel apiModel : Config.API) {
                HomeFragment homeFragment = HomeFragment.getInstance(apiModel.getApi());
                list.add(homeFragment);
            }
        }
        HomeAdapter homeAdapter = new HomeAdapter(getSupportFragmentManager(), apiModels, list);

        viewPager.setAdapter(homeAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isHaveToolBar() {
        return false;
    }

    @Override
    protected boolean isHaveBackIcon() {
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }
}


