package com.hzh.border.pager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.hzh.borderviewpager.adapter
 * FileName: ViewPagerAdapter
 * Date: on 2017/12/29  上午11:03
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<Fragment>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void setDataAndNotify(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }
}