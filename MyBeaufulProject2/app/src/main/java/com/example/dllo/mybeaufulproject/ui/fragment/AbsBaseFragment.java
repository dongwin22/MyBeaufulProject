package com.example.dllo.mybeaufulproject.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.mybeaufulproject.ui.activity.AbsBaseActivity;

/**
 * Created by dllo on 16/7/11.
 */
public abstract class AbsBaseFragment extends Fragment{

    protected Context context;
    //getActivity或者是getcotext
    // 你去找别人要

    /**
     * 当Activity和Fragment关联是会被调用
     * 传入一个Context给你使用
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(),container,false);
    }
    // 这里需要继承抽象类
    protected abstract int setLayout();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //调用初始化组件
        initViews();
    }

    /**
     * 初始化组件
     */
    protected abstract void initViews();

    /**
     * 简化findViewById
     * @param resId
     * @param <T>
     * @return
     */
    protected <T extends View> T byView(int resId){
        return (T) getView().findViewById(resId);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //调用初始化数据
        initDatas();
    }
    /**
     * 初始化数据
     */
    protected  abstract void initDatas();

    /**
     * 跳转传值
     */
    protected void go2(Context from, Class<? extends AbsBaseActivity>to){
        Intent intent = new Intent(from,to);
        context.startActivity(intent);
    }

}
