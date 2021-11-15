package com.example.wor.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AvailableExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AvailableExerciseItem availableExerciseItem);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(AvailableExerciseDao availableExerciseItem);

    @Delete
    void delete(AvailableExerciseDao availableExerciseItem);

    @Delete
    void deleteAllAvailableExercise();

    @Query("SELECT * FROM available_exercise_table")
    LiveData<List<AvailableExerciseItem>> getAllAvailableExercises();

}
