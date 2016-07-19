package com.example.dllo.mybeaufulproject.model.net;

import android.graphics.Bitmap;

/**
 * Created by dllo on 16/7/12.
 * 这个接口的成功失败方法 Volley的网络获取
 */
public interface VolleyPort {
    // 成功 返回一个String
    void stringSuccess(String str);
    //失败
    void stringFailure();

}
