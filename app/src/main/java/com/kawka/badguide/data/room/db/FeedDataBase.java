package com.kawka.badguide.data.room.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.kawka.badguide.data.model.NewsItem;

@Database(entities = {NewsItem.class}, version = 1)
public abstract class FeedDataBase extends RoomDatabase {

    private static volatile FeedDataBase INSTANCE;

    //public abstract FeedDao feedDao();

    public static FeedDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (FeedDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FeedDataBase.class, "feed.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
