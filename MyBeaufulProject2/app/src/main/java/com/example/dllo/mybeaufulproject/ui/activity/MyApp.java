package com.example.dllo.mybeaufulproject.ui.activity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by dllo on 16/7/11.
 */
public class MyApp extends Application{
    private static Context context;
    private static SharedPreferences sp;
    private static List<AbsBaseActivity> activities;


    public static void addActvity(AbsBaseActivity a){
        activities.add(a);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //在这里初始化
        context =  getApplicationContext();
        sp = context.getSharedPreferences("MyDemo",MODE_PRIVATE);
    }
    //对外提供的get方法
    public static Context getContext() {
        return context;
    }

    public static SharedPreferences getSp() {
        return sp;
    }
    public void removeActivity(AppCompatActivity a){
        activities.remove(a);
    }
}
