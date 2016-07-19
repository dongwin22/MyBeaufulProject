package com.example.dllo.mybeaufulproject.model.net;

import android.content.Context;
import android.widget.ImageView;

import com.example.dllo.mybeaufulproject.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by dllo on 16/7/15.
 * 自己写的单例 牛逼完了 啊啊啊啊啊啊啊
 */
public class SingleImageLoader {

   private static SingleImageLoader imageLoader;
    /**
     * 私有化的构造方法~~~
     */
    private SingleImageLoader(){

    }

    /**
     * 这里我们对外提供一个对象的方法
     * 这里要用context 外部传入要用的
     * 然后返回一直对象
     * @param
     * @return
     */
    public static SingleImageLoader getImageLoader(Context context){
        if (imageLoader == null){
            synchronized (SingleImageLoader.class){
                if (imageLoader == null){
                    imageLoader = new SingleImageLoader();
                }
            }
        }
        return imageLoader;
    }
    // UniversalImageloader 解析图片
    public static DisplayImageOptions options = new DisplayImageOptions.Builder().
            cacheInMemory(true).cacheInMemory(true).
            showImageForEmptyUri(R.mipmap.icon_progress_bar_refresh).
            showImageOnFail(R.mipmap.icon_progress_bar_refresh).build();

    public static void loaderImage(String url, ImageView imageView, Context context){
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(context);
        ImageLoader.getInstance().init(configuration);
        ImageLoader.getInstance().displayImage(url,imageView,options);
    }
}
