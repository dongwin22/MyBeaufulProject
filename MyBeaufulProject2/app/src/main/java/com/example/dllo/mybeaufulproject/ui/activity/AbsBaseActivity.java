package com.example.dllo.mybeaufulproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by dllo on 16/7/11.
 */
public abstract class AbsBaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在setContentView上方 去掉信息栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 加入一些进入和退出的动画
        //overridePendingTransition();
        // 绑定布局
        setContentView(setLayout());
        // 订制流程
        // 1.初始化组件
        initViews();
        // 2.初始化数据
        initDatas();
    }

    /**
     * 设置xml的布局文件
     * (protected修饰;子类的同包可以访问
     * 返回值是int 因为xml文件R.layout是int类型
     * @return layout布局文件 eg.R.layout.activity_main
     */
    protected abstract int setLayout();

    /**
     * 初始化组件
     */
    // 初始化组件
    protected abstract  void initViews();

    // 初始化数据
    protected  abstract void initDatas();

    /**
     * 简化findViewById
     * T 泛型:泛指一系列的类型 T指的是view的子类
     */
    protected <T extends View> T ByView(int resId){
        T t = (T) findViewById(resId);
        return t;
    }

    /**
     * 简化intent跳转
     * @param from intent 里的xx.this
     *             intent 里的xx.class ?号为类 要求为基类的子类
     *             这里是限制天剑:要是我们写的基类的子类
     * @param to
     */
    protected void  goTo(Context from, Class<? extends AbsBaseActivity> to){
        Intent intent = new Intent(from,to);
        startActivity(intent);
    }

    /**
     * 代值跳转
     * @param from
     * @param to
     * @param values Bundle类型的值
     *               Bundle是一个轻量级存储的类 存储的形式为key-value
     */
    protected void goTo(Context from,Class<? extends AbsBaseActivity> to,Bundle values){
        Intent intent = new Intent(from,to);
        // 传值们 打包一起传值用的
        intent.putExtras(values);
        startActivity(intent);
    }


    //    protected void goTo(String action, String uri){
//        Intent intent = new Intent(action);
//        intent.setData(Uri.parse(uri));
//        startActivity(intent);
//
//    }

    // 设置监听
    protected abstract void  setListeners();



}

