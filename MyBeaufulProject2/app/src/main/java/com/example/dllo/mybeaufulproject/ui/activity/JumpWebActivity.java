package com.example.dllo.mybeaufulproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.dllo.mybeaufulproject.R;

/**
 * Created by dllo on 16/7/19.
 * 跳转后进入Web网页的Activity  攻略详情页
 */
public class JumpWebActivity extends AbsBaseActivity implements View.OnClickListener {
    private WebView webView;
    private String webUrl;
    private ImageView webBack;
  //  @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected int setLayout() {
        return R.layout.activity_jump_web;
    }

    @Override
    protected void initViews() {
        webView = ByView(R.id.webActivity_webView);
        webBack = ByView(R.id.webActivity_back);

    }

    @Override
    protected void initDatas() {
        //接受listview的Web  Url;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.webUrl = bundle.getString("url");
            WebSettings webSettings = webView.getSettings();
            // 和javaScript交互
            webSettings.setJavaScriptEnabled(true);
            // 设置可以被显示的屏幕控制
            webSettings.setDisplayZoomControls(true);
            // 支持当前屏幕(适配)
            webSettings.setSupportZoom(true);
            //
            webSettings.setBuiltInZoomControls(true);
            // 默认字体大小
            webSettings.setDefaultFontSize(20);
            //
            webSettings.setUseWideViewPort(true);
            // 让手机不能
            webSettings.setLoadWithOverviewMode(true);
            // 缓存 (加载缓存或者从网络加载)
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            // 默认启动手机浏览器关闭, 在webView里启动网页
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
        webView.loadUrl(webUrl);
    }


    @Override
    protected void setListeners() {
        webBack.setOnClickListener(this);
    }


    /**
     * 设置回退 覆盖Activity的类 onKeyDown的方法
     * @param keyCode 按钮(哪一个)
     * @param event 返回时间
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK&&webView.canGoBack()){
        webView.goBack();//goBack表示返回webview的上一界面
        return true;
    }
    finish();
    return true;
}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.webActivity_back:
                finish();
                break;
        }
    }
}
