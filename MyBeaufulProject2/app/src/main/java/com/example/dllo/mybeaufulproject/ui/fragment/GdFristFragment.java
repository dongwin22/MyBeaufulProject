package com.example.dllo.mybeaufulproject.ui.fragment;

import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.BannerBean;
import com.example.dllo.mybeaufulproject.model.bean.GuideFirstLvBean;
import com.example.dllo.mybeaufulproject.model.bean.GuideFristRvBeam;
import com.example.dllo.mybeaufulproject.model.bean.LocalGuideFirstLvBean;
import com.example.dllo.mybeaufulproject.model.net.RunnableDocumentBean;
import com.example.dllo.mybeaufulproject.model.net.VolleyInstance;
import com.example.dllo.mybeaufulproject.model.net.VolleyPort;
import com.example.dllo.mybeaufulproject.ui.activity.LoginActivity;
import com.example.dllo.mybeaufulproject.ui.activity.MainActivity;
import com.example.dllo.mybeaufulproject.ui.adapter.GuideFirstFmAdapter;
import com.example.dllo.mybeaufulproject.ui.adapter.GuideFirstLvAdapter;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/12.
 */
public class GdFristFragment extends AbsBaseFragment implements VolleyPort{
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
    private List<LocalGuideFirstLvBean> lvBeanArray;
    private ListView guideFirstFmLv;


    //图片的url
    private String[] imageUrls ;
    private Gson gson;

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
                for (int i = 0; i < datas.size(); i++) {
                    imageUrls[i] = datas.get(i).getImage_url();

                }
                showBanner();
                type = 1;
                VolleyInstance.getInstance(context).stratStringRequest(rvUrl,this);
                showBanner();
                break;
            case 1:
                GuideFristRvBeam rvBean = gson.fromJson(str,GuideFristRvBeam.class);
                List<GuideFristRvBeam.DataBean.SecondaryBannersBean> rvData = rvBean.getData().getSecondary_banners();
                rvUrlArray = new ArrayList<>();
                for (int i = 0; i < rvData.size(); i++) {
                    String url = rvData.get(i).getImage_url();
                    rvUrlArray.add(url);
                }
                // 初始化适配器
                guideFirstFmAdapter = new GuideFirstFmAdapter(context);
                guideFirstFmAdapter.setImageUrls(rvUrlArray);
                guideFirstFmRv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                guideFirstFmRv.setAdapter(guideFirstFmAdapter);

                type = 2;
                VolleyInstance.getInstance(context).stratStringRequest(lvUrl,this);
                break;
            case 2:
                // 解析lv数据
                gson = new Gson();
                GuideFirstLvBean lvBean = gson.fromJson(str,GuideFirstLvBean.class);
                List<GuideFirstLvBean.DataBean.ItemsBean> lvData = lvBean.getData().getItems();
                lvBeanArray = new ArrayList<>();
                for (int i = 0; i < lvData.size(); i++) {

                    LocalGuideFirstLvBean localGuideFirstLvBean = new LocalGuideFirstLvBean();
                    localGuideFirstLvBean.setImageUrl(lvData.get(i).getCover_image_url()).setTitle(lvData.get(i)
                            .getTitle()).setLikesCount(String.valueOf(lvData.get(i).getLikes_count()));
                    lvBeanArray.add(localGuideFirstLvBean);
                }
                guideFirstLvAdapter = new GuideFirstLvAdapter(context);
                guideFirstLvAdapter.setDatas(lvBeanArray);
                guideFirstFmLv.setAdapter(guideFirstLvAdapter);
                break;
        }
    }

    @Override
    public void stringFailure() {

    }



}
