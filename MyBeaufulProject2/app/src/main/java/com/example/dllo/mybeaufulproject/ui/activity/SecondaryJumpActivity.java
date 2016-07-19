package com.example.dllo.mybeaufulproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.SecondaryJumpBean;
import com.example.dllo.mybeaufulproject.model.net.VolleyInstance;
import com.example.dllo.mybeaufulproject.model.net.VolleyPort;
import com.example.dllo.mybeaufulproject.ui.adapter.SecondaryJumpAdapter;
import com.example.dllo.mybeaufulproject.ui.fragment.GuideFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/18.
 * 轮播图的2级界面 这里是一个activity url是拼接的
 * 这里的Activity是一个复写activity被多个点击时间做为2级跳转
 */
public class SecondaryJumpActivity extends AbsBaseActivity implements VolleyPort, View.OnClickListener {
    private  String url;
    private ListView jumpLv;
    private ImageView backImage;
    private ImageView shareImage;
    private PopupWindow popupWindow;
    private TextView titleTv;



    @Override
    protected int setLayout() {
        return R.layout.activity_secondary_jump;
    }

    @Override
    protected void initViews() {
        jumpLv = ByView(R.id.secondaryJump_lv);
        backImage = ByView(R.id.secondaryJump_back);
        shareImage = ByView(R.id.secondaryJump_share);
        titleTv = ByView(R.id.secondaryJump_titleTv);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void setListeners() {
        backImage.setOnClickListener(this);
        shareImage.setOnClickListener(this);
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
        titleTv.setText(bean.getData().getTitle());
        lvadapter.setBean(bean);
        jumpLv.setAdapter(lvadapter);




    }

    @Override
    public void stringFailure() {

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.secondaryJump_back:
               finish();
               break;
           case R.id.secondaryJump_share:
               showPopupWindow();
               popupWindow.showAsDropDown(shareImage,0,1100);
               break;
       }
    }
    public void showPopupWindow(){
        popupWindow = new PopupWindow();
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(600);
        View view = getLayoutInflater().inflate(R.layout.popupwindow_secondary_jump_share,null);
        popupWindow.setBackgroundDrawable(this.getResources().getDrawable(R.mipmap.ic_launcher));
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        // 这句话的效果是让我的popWindows变暗
        // 出现的时候我的背景颜色是半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);

        // 消失后我的背景颜色变回
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }
}
