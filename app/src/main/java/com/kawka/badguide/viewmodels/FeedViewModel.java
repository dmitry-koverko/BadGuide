package com.kawka.badguide.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.kawka.badguide.data.Repository;
import com.kawka.badguide.data.model.NewsItem;
import com.kawka.badguide.data.room.db.FeedDataBase;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FeedViewModel extends AndroidViewModel implements LifecycleObserver {

    private static final String TAG = FeedViewModel.class.getName();
    NewsItem mItem;

    private Repository repository;
    private MutableLiveData<List<NewsItem>> feed = new MutableLiveData<>();

    public FeedViewModel(@NonNull Application application) {
        super(application);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void init() {
        Log.d(TAG, "onInit()");
        if (feed == null) feed = new MutableLiveData<>();
        repository = Repository.getInstance();
        getFeed(0, 10);
    }

    private void getFeed(int size, int offset) {
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


//        FeedDataBase.getInstance(getApplication()).userDao().insertUser(item).observeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.newThread()).subscribe(new CompletableObserver() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "onSubscribe");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError");
//            }
//        });

//        FeedDataBase.getInstance(getApplication()).feedDao().getItem().observeOn(Schedulers.io()).subscribe(new FlowableSubscriber<NewsItem>() {
//            @Override
//            public void onSubscribe(Subscription s) {
//                Log.d(TAG, "onSubscribe " + s.toString());
//            }
//
//            @Override
//            public void onNext(NewsItem newsItem) {
//                Log.d(TAG, "onNext " + newsItem.getTitle().toString());
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                Log.d(TAG, "onError " + t.toString());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete ");
//            }
//        });
    }

    public MutableLiveData<List<NewsItem>> getDataFeed() {
        return feed;
    }

    private void setItem(){

    }

}
