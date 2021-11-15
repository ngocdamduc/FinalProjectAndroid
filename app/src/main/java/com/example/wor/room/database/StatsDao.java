package com.example.wor.room.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.wor.room.Stats;

import java.util.List;

@Dao
public interface StatsDao {
    @Insert
    void insertStats(Stats stats);
    @Query("select * from stats")
    List<Stats> getListStats();
    @Query("select * from stats where weight = :weight")
    List<Stats> checkStats(String weight);
}