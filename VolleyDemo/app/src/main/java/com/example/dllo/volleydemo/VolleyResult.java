package com.example.dllo.volleydemo;

/**
 * Created by dllo on 16/7/12.
 */
public interface VolleyResult {
    // 成功 返回一个String
    void success(String str);
    //失败
    void failure();
}
