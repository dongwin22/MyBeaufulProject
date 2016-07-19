package com.example.dllo.mybeaufulproject.ui.fragment;

import android.os.Bundle;
import android.widget.ListView;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.GuideReuseBean;
import com.example.dllo.mybeaufulproject.model.bean.LocalGuideReuseLvBean;
import com.example.dllo.mybeaufulproject.model.net.VolleyInstance;
import com.example.dllo.mybeaufulproject.model.net.VolleyPort;
import com.example.dllo.mybeaufulproject.ui.adapter.GuideReuseFmLvAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/12.
 * 这里是复用(第一页)fragment
 */
public class GdReuseFragment extends AbsBaseFragment implements VolleyPort {
    private String reuseUrl;
    //初始化集合
    List<LocalGuideReuseLvBean> localGuideReuseLvBeenArray;
    private GuideReuseFmLvAdapter lvAdapter;
    private ListView gdReuseListView;

    // 对外提供一个静态的 返回fragment对象的方法
    public static GdReuseFragment getReuseFragments(String url){
        // 初始化fragment
        GdReuseFragment gdReuseFragment = new GdReuseFragment();
        Bundle bundle = new Bundle(); // 初始化bundle
        bundle.putString("url",url); // 加入参数中的url
        gdReuseFragment.setArguments(bundle); // 通过setArgunments方法传bundle
        return gdReuseFragment; // 返回framgent
    }


    @Override
    protected int setLayout() {
        return R.layout.fragment_gd_reuse;
    }

    @Override
    protected void initViews() {
        gdReuseListView = byView(R.id.guide_reuse_lv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        this.reuseUrl = bundle.getString("url");
        VolleyInstance.getInstance(context).stratStringRequest(reuseUrl,this);

    }

    @Override
    public void stringSuccess(String str) {
        Gson gson = new Gson();
        GuideReuseBean guideReuseBean = gson.fromJson(str,GuideReuseBean.class);
        List<GuideReuseBean.DataBean.ItemsBean> lvData = guideReuseBean.getData().getItems();
        localGuideReuseLvBeenArray = new ArrayList<>();
        for (int i = 0; i < lvData.size(); i++) {
            LocalGuideReuseLvBean bean = new LocalGuideReuseLvBean();
            bean.setLikesCount(String.valueOf(lvData.get(i).getLikes_count())).
                    setTitle(lvData.get(i).getTitle()).
                    setImageUrl(lvData.get(i).getCover_image_url()).
                    setNickName(lvData.get(i).getAuthor().getNickname()).
                    setAvatarUrl(lvData.get(i).getAuthor().getAvatar_url()).
                    setShortTitle(lvData.get(i).getColumn().getTitle()).
                    setCategory(lvData.get(i).getColumn().getCategory());
            localGuideReuseLvBeenArray.add(bean);
        }
        lvAdapter = new GuideReuseFmLvAdapter(context);
        lvAdapter.setDatas(localGuideReuseLvBeenArray);
        gdReuseListView.setAdapter(lvAdapter);

    }

    @Override
    public void stringFailure() {

    }
}
