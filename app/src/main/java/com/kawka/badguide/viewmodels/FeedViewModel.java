package com.kawka.badguide.viewmodels;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.kawka.badguide.data.Repository;
import com.kawka.badguide.data.model.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class FeedViewModel extends ViewModel implements LifecycleObserver {

    private static final String TAG = FeedViewModel.class.getName();

    private Repository repository;
    private MutableLiveData<List<NewsItem>> feed = new MutableLiveData<>();

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void init(){
        Log.d(TAG, "onInit()");
        if(feed == null) feed = new MutableLiveData<>();
        repository = Repository.getInstance();
        getFeed(0, 10);
    }

    private void getFeed(int size, int offset){
        //TODO get from service
//        if(repository != null)
//            feed = repository.getFeed(size , offset);
        List<NewsItem> list = new ArrayList<>();
        NewsItem item = new NewsItem();
        item.setText("text");
        item.setTitle("title");
        item.setImgUrl("https://image");
        list.add(item);
        feed.setValue(list);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    NewsItem item = new NewsItem();
                    item.setText("text2");
                    item.setTitle("title2");
                    item.setImgUrl("https://image2");
                    list.add(item);
                    feed.postValue(list);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public MutableLiveData<List<NewsItem>> getDataFeed(){
        return feed;
    }



}
