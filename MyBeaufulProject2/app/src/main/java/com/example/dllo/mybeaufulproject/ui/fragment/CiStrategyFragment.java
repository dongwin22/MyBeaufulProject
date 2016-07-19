package com.example.dllo.mybeaufulproject.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.CiFmStrategyRvBean;
import com.example.dllo.mybeaufulproject.model.bean.CiStrategyGvBean;
import com.example.dllo.mybeaufulproject.model.net.RunnableDocumentBean;
import com.example.dllo.mybeaufulproject.model.net.VolleyInstance;
import com.example.dllo.mybeaufulproject.model.net.VolleyPort;
import com.example.dllo.mybeaufulproject.ui.adapter.CiFmStartgyRvAdapter;
import com.example.dllo.mybeaufulproject.ui.adapter.CiFmStrategyGvAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/15.
 * 这里是第三页攻略的Fragment
 */
public class CiStrategyFragment extends AbsBaseFragment implements VolleyPort {
    private int type;//网络解析数据分析页
    private RecyclerView sortRecycleView;
    private CiFmStartgyRvAdapter rvAdapter;
    private String rvurl = RunnableDocumentBean.CI_FM_ST_RV_URL;
    // 下面3个Gv相关
    private String gvUrl = RunnableDocumentBean.CI_FM_ST_GV_URL;
    // 3个Gv对象
    private GridView classGridView,styleGridView,peopleGridView;
    private Gson gson;
    private CiFmStrategyGvAdapter gvAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_ci_strategy;
    }

    @Override
    protected void initViews() {
        sortRecycleView = byView(R.id.ciFmStrategy_recyclerView);
        classGridView = byView(R.id.ciStrategyClassGv);
        styleGridView = byView(R.id.ciStrategyStyleGv);
        peopleGridView = byView(R.id.ciStrategyPeopleGv);

    }

    @Override
    protected void initDatas() {
        type = 0;
        VolleyInstance.getInstance(context).stratStringRequest(rvurl,this);


    }

    @Override
    public void stringSuccess(String str) {
        Toast.makeText(context, "解析成功", Toast.LENGTH_SHORT).show();
        switch (type){
            case 0:

                gson = new Gson();
        List<CiFmStrategyRvBean.DataBean.ColumnsBean> datas =new ArrayList<>();
        CiFmStrategyRvBean bean = gson.fromJson(str,CiFmStrategyRvBean.class);

        for (int i = 0; i < bean.getData().getColumns().size(); i++) {
            CiFmStrategyRvBean.DataBean.ColumnsBean columnsBean = bean.getData().getColumns().get(i);
            datas.add(columnsBean);
        }
        rvAdapter = new CiFmStartgyRvAdapter(context);
        rvAdapter.setDatas(datas);
        sortRecycleView.setAdapter(rvAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        sortRecycleView.setLayoutManager(gridLayoutManager);
                type = 1;
                VolleyInstance.getInstance(context).stratStringRequest(gvUrl,this);
                break;
            case 1:
                List<CiStrategyGvBean.DataBean.ChannelGroupsBean.ChannelsBean> classbeanArray = new ArrayList<>();
                List<CiStrategyGvBean.DataBean.ChannelGroupsBean.ChannelsBean> stylebeanArray = new ArrayList<>();
                List<CiStrategyGvBean.DataBean.ChannelGroupsBean.ChannelsBean> peopleArray = new ArrayList<>();
                CiStrategyGvBean gvBean = gson.fromJson(str,CiStrategyGvBean.class);
                for (int i = 0; i < gvBean.getData().getChannel_groups().size(); i++) {
                   CiStrategyGvBean.DataBean.ChannelGroupsBean data = gvBean.getData().getChannel_groups().get(i);
                    //这里每次都要初始化适配器 因为3个布局共用一个适配器 所以这里一定要初始化适配器
                    switch (i){
                        case 0:
                            for (int i1 = 0; i1 < data.getChannels().size(); i1++) {
                                classbeanArray.add(data.getChannels().get(i1));
                            }
                            gvAdapter = new CiFmStrategyGvAdapter(context);
                            gvAdapter.setDatas(classbeanArray);
                            classGridView.setAdapter(gvAdapter);
                            break;
                        case 1:
                            for (int i1 = 0; i1 < data.getChannels().size(); i1++) {
                                stylebeanArray.add(data.getChannels().get(i1));
                            }
                            gvAdapter = new CiFmStrategyGvAdapter(context);
                            gvAdapter.setDatas(stylebeanArray);
                            styleGridView.setAdapter(gvAdapter);

                            break;
                        case 2:
                            for (int i3 = 0; i3 < data.getChannels().size(); i3++) {
                                peopleArray.add(data.getChannels().get(i3));
                            }
                            gvAdapter = new CiFmStrategyGvAdapter(context);
                            gvAdapter.setDatas(peopleArray);
                            peopleGridView.setAdapter(gvAdapter);
                            break;
                    }


                }



                break;
        }

    }

    @Override
    public void stringFailure() {
    }
}
