package com.kawka.badguide.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.kawka.badguide.R;
import com.kawka.badguide.data.model.NewsItem;
import com.kawka.badguide.viewmodels.FeedViewModel;

import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private FeedViewModel feedViewModel;
    MutableLiveData<List<NewsItem>> mFeed = new MutableLiveData<>();

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        feedViewModel = new FeedViewModel();
        getLifecycle().addObserver(feedViewModel);

        feedViewModel.getDataFeed().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(List<NewsItem> newsItems) {
                Log.d(TAG, "feed ViewModel changed");
            }
        });

    }
}