package com.example.dllo.mybeaufulproject.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.ui.adapter.CiFmVpAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/11.
 * 这里是分类(第三页)的fragment
 */
public class ClassifyFragment extends AbsBaseFragment{
    private TabLayout ciFmTabLayout;
    private ViewPager ciFmViewpager;
    private CiFmVpAdapter adapter;
    private List<Fragment> fragments;
    private TextView ciFmTitleTv;


    @Override
    protected int setLayout() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initViews() {
        ciFmTabLayout = byView(R.id.cIFm_tabLayout);
        ciFmViewpager = byView(R.id.cIFm_viewPager);
        ciFmTitleTv = byView(R.id.cIFm_XlTv);

    }

    @Override
    protected void initDatas() {
        adapter = new CiFmVpAdapter(getChildFragmentManager());
        fragments = new ArrayList<>();
        fragments.add(new CiStrategyFragment());
        fragments.add(new CiSingleFragment());
        adapter.setFragments(fragments);
        ciFmViewpager.setAdapter(adapter);
        ciFmTabLayout.setupWithViewPager(ciFmViewpager);

        if (ciFmTabLayout.getSelectedTabPosition() == 0) {
            ciFmTitleTv.setVisibility(View.GONE);
        } else {
            ciFmTitleTv.setVisibility(View.VISIBLE);
        }

        ciFmTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (ciFmTabLayout.getSelectedTabPosition() == 0) {
                    ciFmTitleTv.setVisibility(View.GONE);
                } else {
                    ciFmTitleTv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
            }



    }


