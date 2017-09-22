package com.czg.xunlei.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.czg.xunlei.base.BaseFragment;
import com.czg.xunlei.model.ApiModel;

import java.util.List;

/**
 * Created by 78101 on 2017/9/18.
 */

public class HomeAdapter extends FragmentPagerAdapter {
    List<ApiModel> data;
    List<BaseFragment> list;

    public HomeAdapter(FragmentManager fm, List<ApiModel> data,List<BaseFragment> list) {
        super(fm);
        this.data = data;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getName();
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
