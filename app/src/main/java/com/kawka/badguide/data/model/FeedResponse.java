package com.kawka.badguide.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedResponse {

    @SerializedName("list")
    private List<NewsItem> list = null;

    public List<NewsItem> getList() {
        return list;
    }

    public void setList(List<NewsItem> list) {
        this.list = list;
    }
}
