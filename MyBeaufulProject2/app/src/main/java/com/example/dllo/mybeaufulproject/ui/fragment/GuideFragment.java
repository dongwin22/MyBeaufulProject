package com.example.dllo.mybeaufulproject.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.GuideRollTitleBean;
import com.example.dllo.mybeaufulproject.model.net.RunnableDocumentBean;
import com.example.dllo.mybeaufulproject.model.net.VolleyInstance;
import com.example.dllo.mybeaufulproject.model.net.VolleyPort;
import com.example.dllo.mybeaufulproject.ui.activity.LoginActivity;
import com.example.dllo.mybeaufulproject.ui.adapter.FragmentGuideAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/11.
 */
public class GuideFragment extends AbsBaseFragment implements VolleyPort {
    private ImageView imageViewLogin;
    private String rollUrl = RunnableDocumentBean.TL_TITLE_URL;
    private ArrayList<String> rollTitleArray;
    private ArrayList<Fragment> fragments;
    private FragmentGuideAdapter fragmentGuideAdapter;
    private ViewPager guideFmViewpager;
    private TabLayout guideFmTablayout;

    @Override
    protected int setLayout() {
        return R.layout.fragment_guide;
    }

    @Override
    protected void initViews() {
        guideFmViewpager = byView(R.id.guide_fragment_viewpager);
        guideFmTablayout = byView(R.id.guide_fragment_tablayout);
        imageViewLogin = byView(R.id.guide_title_one);

    }

    @Override
    protected void initDatas() {
        // 网络获取Tablayout
        VolleyInstance.getInstance(context).stratStringRequest(rollUrl,this);
        // 初始化viewpager

        fragments = new ArrayList<>();
        rollTitleArray = new ArrayList<>();
        imageViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go2(GuideFragment.this.context, LoginActivity.class);
            }
        });

    }
    @Override
    public void stringSuccess(String str) {
        Gson gson = new Gson();
        // 解析
        GuideRollTitleBean bean = gson.fromJson(str,GuideRollTitleBean.class);
        List<GuideRollTitleBean.DataBean.ChannelsBean> datas = bean.getData().getChannels();

        for (int i = 0; i < datas.size(); i++) {
            String title = datas.get(i).getName();
            rollTitleArray.add(title);
        }

        for (int i = 0; i < rollTitleArray.size(); i++) {
            if (i ==0 ){
                fragments.add(new GdFristFragment());
            }else{
                fragments.add(new GdReuseFragment());
            }

        }
        // tabLayout滑动属性
        guideFmTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        fragmentGuideAdapter = new FragmentGuideAdapter(getChildFragmentManager());
        guideFmViewpager.setAdapter(fragmentGuideAdapter);
        fragmentGuideAdapter.setTitle(rollTitleArray,fragments);
        guideFmTablayout.setupWithViewPager(guideFmViewpager);
    }

    @Override
    public void stringFailure() {

    }
}
