package com.example.dllo.mybeaufulproject.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.HotFmGvBean;
import com.example.dllo.mybeaufulproject.model.bean.LocalHotFmGvBean;
import com.example.dllo.mybeaufulproject.model.net.RunnableDocumentBean;
import com.example.dllo.mybeaufulproject.model.net.VolleyInstance;
import com.example.dllo.mybeaufulproject.model.net.VolleyPort;
import com.example.dllo.mybeaufulproject.ui.activity.JumpbabyDatailsActivity;
import com.example.dllo.mybeaufulproject.ui.adapter.HotFmGvAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/7/11.
 * 这里是热门的Fragment
 */
public class HotFragment extends AbsBaseFragment implements VolleyPort, AdapterView.OnItemClickListener {
    private GridView hotFmGridView;
    private String gvUrl = RunnableDocumentBean.HOT_FM_GV_URL;
    private HotFmGvAdapter gvAdapter;
    private HotFmGvBean hotFmGvBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initViews() {
        hotFmGridView = byView(R.id.hotFragment_gridView);
    }

    @Override
    protected void initDatas() {
        hotFmGridView.setOnItemClickListener(this);
        VolleyInstance.getInstance(context).stratStringRequest(gvUrl,this);

    }

    @Override
    public void stringSuccess(String str) {
        Gson gson = new Gson();
        hotFmGvBean = gson.fromJson(str,HotFmGvBean.class);

        gvAdapter = new HotFmGvAdapter(context);
        gvAdapter.setDatas(hotFmGvBean);
        hotFmGridView.setAdapter(gvAdapter);


    }

    @Override
    public void stringFailure() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString("url",hotFmGvBean.getData().getItems().get(position).getData().getUrl());
        goTo(context, JumpbabyDatailsActivity.class,bundle);
    }
}
