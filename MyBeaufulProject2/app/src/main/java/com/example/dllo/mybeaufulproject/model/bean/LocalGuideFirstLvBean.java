package com.example.dllo.mybeaufulproject.model.bean;

/**
 * Created by dllo on 16/7/13.
 * 首页的内部类
 */
public class LocalGuideFirstLvBean {
    String title,likesCount,imageUrl;

    public LocalGuideFirstLvBean(String title, String likesCount, String imageUrl) {
        this.title = title;
        this.likesCount = likesCount;
        this.imageUrl = imageUrl;
    }

    public LocalGuideFirstLvBean() {
    }

    public String getTitle() {
        return title;
    }

    public LocalGuideFirstLvBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public LocalGuideFirstLvBean setLikesCount(String likesCount) {
        this.likesCount = likesCount;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocalGuideFirstLvBean setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
