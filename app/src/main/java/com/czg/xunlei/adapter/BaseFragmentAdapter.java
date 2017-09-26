package com.czg.xunlei.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.czg.xunlei.model.FragmentTab;

import java.util.List;

/**
 * Created by 78101 on 2017/9/18.
 */

public class BaseFragmentAdapter extends FragmentPagerAdapter {
    List<FragmentTab> list;

    public BaseFragmentAdapter(FragmentManager fm, List<FragmentTab> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position).mFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).title;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
