package com.kawka.badguide.data;

import androidx.lifecycle.MutableLiveData;

import com.kawka.badguide.data.model.FeedResponse;
import com.kawka.badguide.data.model.NewsItem;
import com.kawka.badguide.data.net.IFeed;
import com.kawka.badguide.data.net.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static Repository repository;
    private IFeed feed;

    public static Repository getInstance(){
        if(repository == null) repository = new Repository();
        return repository;
    }

    public Repository(){
        feed = RetrofitService.cteateService(IFeed.class);
    }

    public MutableLiveData<List<NewsItem>> getFeed(int size, int offset){
        MutableLiveData<List<NewsItem>> feedData = new MutableLiveData<>();
        feed.getFeed(size, offset).enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                if (response.isSuccessful()){
                    feedData.setValue(response.body().getList());
                }
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                feedData.setValue(null);
            }
        });
        return feedData;
    }

}
