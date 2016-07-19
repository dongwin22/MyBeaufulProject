package com.example.dllo.mybeaufulproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.SecondaryJumpBean;
import com.example.dllo.mybeaufulproject.model.net.VolleyInstance;
import com.example.dllo.mybeaufulproject.model.net.VolleyPort;
import com.example.dllo.mybeaufulproject.ui.adapter.SecondaryJumpAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/18.
 * 轮播图的2级界面 这里是一个activity url是拼接的
 * 这里的Activity是一个复写activity被多个点击时间做为2级跳转
 */
public class SecondaryJumpActivity extends AbsBaseActivity implements VolleyPort {
    private  String url;
    private ListView jumpLv;


    @Override
    protected int setLayout() {
        return R.layout.activity_secondary_jump;
    }

    @Override
    protected void initViews() {
        jumpLv = ByView(R.id.secondaryJump_lv);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void setListeners() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.url = bundle.getString("url");
        VolleyInstance.getInstance(this).stratStringRequest(url,this);

    }

    @Override
    public void stringSuccess(String str) {
        Gson gson = new Gson();
        SecondaryJumpBean bean = gson.fromJson(str,SecondaryJumpBean.class);
        SecondaryJumpAdapter lvadapter = new SecondaryJumpAdapter(this);
        lvadapter.setBean(bean);
        jumpLv.setAdapter(lvadapter);


    }

    @Override
    public void stringFailure() {

    }
}
