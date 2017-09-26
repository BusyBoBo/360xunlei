package com.czg.xunlei;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.czg.xunlei.adapter.BaseFragmentAdapter;
import com.czg.xunlei.base.BaseActivity;
import com.czg.xunlei.fragment.HomeCartoonTabFragment;
import com.czg.xunlei.fragment.HomeJavTabFragment;
import com.czg.xunlei.model.FragmentTab;

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
    private List<FragmentTab> list = new ArrayList<>();


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tool_bar = tool_bar2;
        tool_bar.setTitleTextColor(Color.WHITE);
        setTitle("Home");
        if (list.isEmpty()) {
            list.add(new FragmentTab(new HomeJavTabFragment(), "JavLibrary"));
            list.add(new FragmentTab(new HomeCartoonTabFragment(), "CarToon"));
        }
        viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), list));
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


