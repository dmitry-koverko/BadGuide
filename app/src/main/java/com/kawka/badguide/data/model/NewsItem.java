package com.kawka.badguide.data.model;

import com.google.gson.annotations.SerializedName;

public class NewsItem {

    @SerializedName("title")
    private String title;

    @SerializedName("text")
    private String text;

    @SerializedName("imgUrl")
    private String imgUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
