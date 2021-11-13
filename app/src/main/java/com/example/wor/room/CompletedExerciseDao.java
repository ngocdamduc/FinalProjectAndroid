package com.example.wor.room;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

public interface CompletedExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CompletedExerciseItem completedExerciseItem);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(CompletedExerciseItem completedExerciseItem);
    @Delete
    void delete(CompletedExerciseItem completedExerciseItem);
}
