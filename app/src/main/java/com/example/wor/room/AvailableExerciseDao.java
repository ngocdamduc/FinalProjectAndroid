package com.example.wor.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface AvailableExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AvailableExerciseDao availableExerciseItem);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(AvailableExerciseDao availableExerciseItem);

    @Delete
    void delete(AvailableExerciseDao availableExerciseItem);

}
