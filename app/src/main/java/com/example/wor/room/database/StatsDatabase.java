package com.example.wor.room.database;

import android.content.Context;

import androidx.room.Database;
import com.example.wor.room.Stats;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Stats.class}, version = 1)


public abstract class StatsDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "stats.db";
    private static StatsDatabase instance;
    public static synchronized StatsDatabase getInstance(Context context){
        if(instance == null){
            instance= Room.databaseBuilder(context.getApplicationContext(), StatsDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract StatsDao statsDao();
}
