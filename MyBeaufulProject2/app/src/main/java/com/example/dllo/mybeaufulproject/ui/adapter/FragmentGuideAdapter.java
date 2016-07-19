package com.example.dllo.mybeaufulproject.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/12.
 * 这里是第一页首页的适配器
 */
public class FragmentGuideAdapter extends FragmentPagerAdapter{
    private ArrayList<String> title;
    private ArrayList<Fragment> guidefm;

    public FragmentGuideAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentGuideAdapter(FragmentManager fm, ArrayList<String> title, ArrayList<Fragment> guidefm) {
        super(fm);
        this.title = title;
        this.guidefm = guidefm;
    }

    public void setTitle(ArrayList<String> title, ArrayList<Fragment> fragments) {
        this.title = title;
        this.guidefm = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return guidefm != null? guidefm.get(position):null;
    }

    @Override
    public int getCount() {
        return guidefm != null ? guidefm.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
