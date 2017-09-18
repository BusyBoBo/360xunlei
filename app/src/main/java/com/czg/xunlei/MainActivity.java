package com.czg.xunlei;

import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.ViewPager;

import com.czg.xunlei.adapter.HomeAdapter;
import com.czg.xunlei.base.BaseActivity;
import com.czg.xunlei.fragment.HomeFragment;
import com.czg.xunlei.model.ApiModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class MainActivity extends BaseActivity {

    @Bind(R.id.ed_search)
    TextInputEditText edSearch;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    private List<HomeFragment> list=new ArrayList<>();;

    @Override
    protected boolean isHaveToolBar() {
        return false;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        if(list.isEmpty()) {
            for (ApiModel apiModel:Config.API){
                HomeFragment homeFragment = HomeFragment.getInstance(apiModel.getApi());
                list.add(homeFragment);
            }
            HomeAdapter homeAdapter = new HomeAdapter(getSupportFragmentManager(), Config.API, list);
            viewPager.setAdapter(homeAdapter);
        }

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }



}


