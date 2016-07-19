package com.example.dllo.mybeaufulproject.ui.fragment;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.CiFmSingleLvBean;
import com.example.dllo.mybeaufulproject.model.net.RunnableDocumentBean;
import com.example.dllo.mybeaufulproject.model.net.VolleyInstance;
import com.example.dllo.mybeaufulproject.model.net.VolleyPort;
import com.example.dllo.mybeaufulproject.ui.adapter.CiSingleGvAdapter;
import com.example.dllo.mybeaufulproject.ui.adapter.CiSingleLeftLvAdapter;
import com.example.dllo.mybeaufulproject.ui.adapter.CiSingleRightLvAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/15.
 * 这里是第三页单品的fragment
 * 这里面有2个Listview都是联动的
 */
public class CiSingleFragment extends AbsBaseFragment implements VolleyPort {
    private TextView ciFmTitle;
    private String singleUrl = RunnableDocumentBean.CI_FM_SINGLE_URL;
    private ListView leftListView,rightListView;
    private CiSingleLeftLvAdapter leftLvAdapter;
    private CiSingleRightLvAdapter rightLvAdapter;
    private CiSingleGvAdapter gvAdapter;
    private GridView myCustomGirdView;
    //实验
    private View clickSource,touchSource;
    private int offset = 0;


    @Override
    protected int setLayout() {
        return R.layout.fragment_ci_single;
    }

    @Override
    protected void initViews() {
        ciFmTitle = byView(R.id.cIFm_XlTv);
        leftListView = byView(R.id.left_listView);
        rightListView = byView(R.id.right_listView);

    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance(context).stratStringRequest(singleUrl,this);

        /**
         * listView滑动事件  右侧listView滑动 左侧listView根据位置跳到相应位置
         */
        rightListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (touchSource == null) {
                    touchSource = v;
                }
                if (v == touchSource) {
                    leftListView.dispatchTouchEvent(event);
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        clickSource = v;
                        touchSource = null;
                    }
                }
                return false;
            }
        });

        /**
         * listView点击事件
         * 双listView联动 点击左侧listView 右侧跳到相应位置
         */
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 右侧listView跳到当前左侧listView的位置
                rightListView.setSelection(position);
                // 通知适配器刷新位置
                rightLvAdapter.notifyDataSetInvalidated();
            }
        });
    }




    //解析开始
    @Override
    public void stringSuccess(String str) {
        Gson gson = new Gson();
        List<CiFmSingleLvBean.DataBean.CategoriesBean> titleDatas = new ArrayList<>();
        List<CiFmSingleLvBean.DataBean.CategoriesBean.SubcategoriesBean> contentDatas = new ArrayList<>();
        CiFmSingleLvBean bean = gson.fromJson(str,CiFmSingleLvBean.class);
        for (int i = 0; i < bean.getData().getCategories().size(); i++) {
            titleDatas.add(bean.getData().getCategories().get(i));
        }

        leftLvAdapter = new CiSingleLeftLvAdapter(context);
        leftLvAdapter.setDatas(titleDatas);
        leftListView.setAdapter(leftLvAdapter);
        rightLvAdapter = new CiSingleRightLvAdapter(context);
        rightLvAdapter.setDatas(titleDatas);
        rightListView.setAdapter(rightLvAdapter);


    }



    @Override
    public void stringFailure() {

    }
}
