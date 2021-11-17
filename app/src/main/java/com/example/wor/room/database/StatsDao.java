package com.example.wor.room.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wor.room.AvailableExerciseItem;
import com.example.wor.room.CompletedExerciseItem;
import com.example.wor.room.ExerciseType;
import com.example.wor.room.Stats;

import org.threeten.bp.LocalDate;

import java.util.List;

@Dao
public interface StatsDao {
    @Insert
    void insertStats(Stats stats);
    @Update
    void updateStatts(Stats stats);
    @Delete
    void delete(Stats stats);

    @Query("select * from stats")
    List<Stats> getListStats();

    @Query("select * from stats where weight = :weight")
    List<Stats> checkStats(String weight);

    @Query("DELETE FROM stats")
    void deleteAllStats();

    @Query("DELETE FROM stats WHERE stats_date =:date")
    void deleteAllStatsDateByDate(LocalDate date);


//    deleteStatByDate, deleteAllStats, getStatByDate và getAllStats

}