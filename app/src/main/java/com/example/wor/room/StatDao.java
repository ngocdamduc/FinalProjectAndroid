package com.example.wor.room;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.time.LocalDate;
import java.util.List;

public interface StatDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Stat stat);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Stat stat);

    @Delete
    void delete(Stat stat);

    @Query("SELECT * FROM stat_table")
    LiveData<List<Stat>> getAllstats();

    @Query("SELECT * FROM stat_table WHERE stat_date = :mDate")
    LiveData<List<Stat>> getStatByDate(LocalDate mDate);

    @Query("DELETE FROM stat_table WHERE stat_date = :mDate")
    void deleteStatByDate(LocalDate mDate);
}
