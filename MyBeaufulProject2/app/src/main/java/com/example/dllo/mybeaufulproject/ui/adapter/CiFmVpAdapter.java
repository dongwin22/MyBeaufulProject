package com.example.dllo.mybeaufulproject.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/15.
 * 这里是第三页的首页的适配器
 */
public class CiFmVpAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;
    private List<String> title;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public CiFmVpAdapter(FragmentManager fm) {
        super(fm);
        title = new ArrayList<>();
        title.add("攻略");
        title.add("单品");
    }

    @Override
    public Fragment getItem(int position) {

        return fragments != null ? fragments.get(position):null;
    }

    @Override
    public int getCount() {
        return fragments != null ?fragments.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
