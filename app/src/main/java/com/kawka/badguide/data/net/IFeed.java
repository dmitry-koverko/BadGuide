package com.kawka.badguide.data.net;

import com.kawka.badguide.data.model.FeedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IFeed {

    @GET("/feed/")
    Call<FeedResponse> getFeed(@Query("size") int size, @Query("offset") int offset);

}
