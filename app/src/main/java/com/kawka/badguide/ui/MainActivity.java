package com.kawka.badguide.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

import com.kawka.badguide.R;
import com.kawka.badguide.data.model.NewsItem;
import com.kawka.badguide.data.model.Person;
import com.kawka.badguide.data.room.dao.PersonDao;
import com.kawka.badguide.data.room.db.AppDatabase;
import com.kawka.badguide.data.room.db.FeedDataBase;
import com.kawka.badguide.ui.bottombarview.BottomBarView;
import com.kawka.badguide.viewmodels.FeedViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_bar) BottomBarView bottomBarView;

    private static final String TAG = MainActivity.class.getName();

    private FeedViewModel feedViewModel;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        feedViewModel = new FeedViewModel(getApplication());
        getLifecycle().addObserver(feedViewModel);

        bottomBarView.setEventListener(new BottomBarView.BottomBarStateListener() {
            @Override
            public void onChangeState(BottomBarView.State state) {
                Log.d(TAG, "stateBottomBar is " + state.name());
            }
        });

        feedViewModel.getDataFeed().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(List<NewsItem> newsItems) {
                Log.d(TAG, "feed ViewModel changed");
            }
        });

        NewsItem n = new NewsItem();
        n.setTitle("dsdsadada");
        n.setText("dsadasdadad");
        n.setImgUrl("dsadadaddsaaaaaaaaaaaaa");

//        FeedDataBase db = FeedDataBase.getInstance(getApplicationContext());
//        FeedDao feedDao = db.feedDao();
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        PersonDao d = db.getPersonDao();
        Person p = new Person();
        p.setAge(323);
        p.setName("dsadadada");


       new Thread(new Runnable() {
           @Override
           public void run() {
               //d.insertAll(p);
               List<Person> ds = d.getAllPeople();
               int sda = 3132;
           }
       }).start();

    }
}