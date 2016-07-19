package com.example.dllo.mybeaufulproject.ui.fragment;

import android.graphics.Color;
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
 * 这里是第一页总体的fragment
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
        VolleyInstance.getInstance(context).stratStringRequest(rollUrl, this);
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
            GuideRollTitleBean bean = gson.fromJson(str, GuideRollTitleBean.class);
            List<GuideRollTitleBean.DataBean.ChannelsBean> datas = bean.getData().getChannels();

            for (int i = 0; i < datas.size(); i++) {
                String title = datas.get(i).getName();
                rollTitleArray.add(title);
        }


        // 添加Fragment

        fragments.add(new GdFristFragment());
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_CYSH_URL)); // 穿搭 没有
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_HT_URL)); // 海淘
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SNP_URL)); // 送男票
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SJY_URL)); // 礼物 没有
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SGM_URL)); // 送闺蜜
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SBM_URL)); // 送爸妈
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_STS_URL)); // 送同事
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SJY_URL)); // 送机油
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SBB_URL)); // 送宝贝
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SGM_URL)); // 手工  没有
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SJG_URL)); // 设计感
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_CYSH_URL)); // 创意生活
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_WYF_URL)); // 文艺风
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_KJF_URL)); // 科技范
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_MMD_URL)); // 萌萌哒
        fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_QPGG_URL)); // 奇葩搞怪


        // tabLayout滑动属性
        guideFmTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        fragmentGuideAdapter = new FragmentGuideAdapter(getChildFragmentManager());
        guideFmViewpager.setAdapter(fragmentGuideAdapter);
        fragmentGuideAdapter.setTitle(rollTitleArray, fragments);
        guideFmTablayout.setupWithViewPager(guideFmViewpager);
        guideFmTablayout.setTabTextColors(Color.BLACK,Color.RED);
        guideFmTablayout.setSelectedTabIndicatorColor(Color.RED);
        // viewPager 设置加载模式
        guideFmViewpager.setOffscreenPageLimit(16);
    }

    @Override
    public void stringFailure() {

    }
}
