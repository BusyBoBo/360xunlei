package com.czg.xunlei.model;

import android.support.v4.app.Fragment;

/**
 * Created by czg on 2017/9/26.
 */

public class FragmentTab {
    public Fragment mFragment;
    public CharSequence title;

    public FragmentTab(Fragment fragment, CharSequence title) {
        mFragment = fragment;
        this.title = title;
    }
}
