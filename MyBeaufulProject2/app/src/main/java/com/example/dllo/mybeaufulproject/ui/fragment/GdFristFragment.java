package com.example.dllo.mybeaufulproject.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.BannerBean;
import com.example.dllo.mybeaufulproject.model.bean.GuideFirstLvBean;
import com.example.dllo.mybeaufulproject.model.bean.GuideFristRvBeam;
import com.example.dllo.mybeaufulproject.model.bean.LocalGuideFirstLvBean;
import com.example.dllo.mybeaufulproject.model.net.RunnableDocumentBean;
import com.example.dllo.mybeaufulproject.model.net.VolleyInstance;
import com.example.dllo.mybeaufulproject.model.net.VolleyPort;
import com.example.dllo.mybeaufulproject.ui.activity.JumpWebActivity;
import com.example.dllo.mybeaufulproject.ui.activity.LoginActivity;
import com.example.dllo.mybeaufulproject.ui.activity.MainActivity;
import com.example.dllo.mybeaufulproject.ui.activity.SecondaryJumpActivity;
import com.example.dllo.mybeaufulproject.ui.adapter.GdFmRvOnClick;
import com.example.dllo.mybeaufulproject.ui.adapter.GuideFirstFmAdapter;
import com.example.dllo.mybeaufulproject.ui.adapter.GuideFirstLvAdapter;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/7/12.
 * 这里是第一页精品的fragment
 */
public class GdFristFragment extends AbsBaseFragment implements VolleyPort, Banner.OnBannerClickListener, GdFmRvOnClick, AdapterView.OnItemClickListener {
    //设置一个状态 区分网络对象
    private int type;
    private List<String> rvUrlArray;//装rvUrl的集合
    private GuideFirstFmAdapter guideFirstFmAdapter;
    private GuideFirstLvAdapter guideFirstLvAdapter;
    private RecyclerView guideFirstFmRv;
    // 初始化这个Banner
    private Banner rollImgBanner;
    private String bannersUrl= RunnableDocumentBean.GD_BANNER_URL;
    // 初始化recyleView接口
    private String rvUrl = RunnableDocumentBean.GD_FIRST_RV_URL;
    private String lvUrl = RunnableDocumentBean.GD_FIRST_LV_URL;
    private ListView guideFirstFmLv;



    //图片的url
    private String[] imageUrls ;
    private  String[] bannerJupUrls;
    private String[] titles;
    private Gson gson;
    private GuideFirstLvBean lvBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_gd_frist;
    }

    @Override
    protected void initViews() {
        rollImgBanner = byView(R.id.rollImg_banner);
        guideFirstFmRv = byView(R.id.guideFirstFm_rv);
        guideFirstFmLv = byView(R.id.guide_no_listview);

    }

    @Override
    protected void initDatas() {
        type = 0;
        VolleyInstance.getInstance(context).stratStringRequest(bannersUrl,this);

        // 给Listview加点击事件
        guideFirstFmLv.setOnItemClickListener(this);





    }
    public void showBanner(){
        // 设置小圆点
        rollImgBanner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        // 设置位置
        rollImgBanner.setIndicatorGravity(Banner.CENTER);
        // 设置轮播时间
        rollImgBanner.setDelayTime(3000);
        // 设置图片
        rollImgBanner.setImages(imageUrls);
        // 加监听事件
        rollImgBanner.setOnBannerClickListener(this);

    }


    public void showBannerData(){

    }

    @Override
    public void stringSuccess(String str) {
        gson = new Gson();
        switch (type){
            case 0:
                //解析
                BannerBean bean = gson.fromJson(str,BannerBean.class);
                List<BannerBean.DataBean.BannersBean> datas = bean.getData().getBanners();
                imageUrls = new String[datas.size()];
                bannerJupUrls = new String[datas.size()];
                titles = new String[datas.size()];
                for (int i = 0; i < datas.size(); i++) {
                    imageUrls[i] = datas.get(i).getImage_url();
                    bannerJupUrls[i] ="http://api.liwushuo.com/v2/collections/"+String.valueOf(datas.get(i).getTarget_id())+"/posts?gender=1&generation=2&limit=20&offset=0";

                }
                showBanner();
                type = 1;
                VolleyInstance.getInstance(context).stratStringRequest(rvUrl,this);
                showBanner();
                break;
            case 1:
                GuideFristRvBeam rvBean = gson.fromJson(str,GuideFristRvBeam.class);

                guideFirstFmAdapter = new GuideFirstFmAdapter(context);
                guideFirstFmAdapter.setRvBeam(rvBean);
                guideFirstFmRv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                guideFirstFmRv.setAdapter(guideFirstFmAdapter);
                guideFirstFmAdapter.setGdFmRvOnClick(this);

                type = 2;
                VolleyInstance.getInstance(context).stratStringRequest(lvUrl,this);
                break;
            case 2:
                // 解析lv数据
                gson = new Gson();
                lvBean = gson.fromJson(str,GuideFirstLvBean.class);

                guideFirstLvAdapter = new GuideFirstLvAdapter(context);
                guideFirstLvAdapter.setDatas(lvBean);
                guideFirstFmLv.setAdapter(guideFirstLvAdapter);
                break;
        }
    }

    @Override
    public void stringFailure() {

    }

    /**
     * 轮播图的监听事件
     * @param view
     * @param position
     */
    @Override
    public void OnBannerClick(View view, int position) {
        Bundle bundle = new Bundle();
        String url =bannerJupUrls[position];
        bundle.putString("url",url);
        goTo(context, SecondaryJumpActivity.class,bundle);
        Toast.makeText(context, "~别乱点我~", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickListener(int pos) {
        Toast.makeText(context, "啦啦啦", Toast.LENGTH_SHORT).show();
    }

    /**
     * listview的点击事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString("url",lvBean.getData().getItems().get(position).getUrl());
        goTo(context, JumpWebActivity.class,bundle);
    }
}
