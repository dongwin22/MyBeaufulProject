package com.example.dllo.mybeaufulproject.model.bean;

/**
 * Created by dllo on 16/7/14.
 * 首页复用的内部类
 */
public class LocalGuideReuseLvBean {
    String likesCount,nickName,category,title,shortTitle,imageUrl,avatarUrl;

    public LocalGuideReuseLvBean(String likesCount, String nickName, String category, String title, String shortTitle, String imageUrl, String avatarUrl) {
        this.likesCount = likesCount;
        this.nickName = nickName;
        this.category = category;
        this.title = title;
        this.shortTitle = shortTitle;
        this.imageUrl = imageUrl;
        this.avatarUrl = avatarUrl;
    }

    public LocalGuideReuseLvBean() {
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public LocalGuideReuseLvBean setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
        return this;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public LocalGuideReuseLvBean setLikesCount(String likesCount) {
        this.likesCount = likesCount;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public LocalGuideReuseLvBean setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public LocalGuideReuseLvBean setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public LocalGuideReuseLvBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocalGuideReuseLvBean setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public LocalGuideReuseLvBean setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }
}
