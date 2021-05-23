package com.kawka.badguide.data.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kawka.badguide.data.model.NewsItem;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface FeedDao {

    @Query("SELECT * FROM feed LIMIT 1")
    Flowable<NewsItem> getItem();

    @Query("SELECT * FROM feed LIMIT 1")
    List<NewsItem> getAllItem();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(NewsItem item);


    @Query("DELETE FROM feed")
    void deleteAllUItems();
}
