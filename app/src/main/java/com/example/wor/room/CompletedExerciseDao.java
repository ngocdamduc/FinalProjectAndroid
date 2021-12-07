package com.example.wor.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.threeten.bp.LocalDateTime;

import java.util.List;

@Dao
public interface CompletedExerciseDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(CompletedExerciseItem completedExerciseItem);

    @Update (onConflict = OnConflictStrategy.REPLACE)
    void update(CompletedExerciseItem completedExerciseItem);

    @Delete
    void delete(CompletedExerciseItem completedExerciseItem);

    @Query("DELETE FROM completed_exercise_table WHERE exercise_date = :date")
    void deleteAllCompletedExerciseByDate(LocalDateTime date);

    @Query("DELETE FROM completed_exercise_table")
    void deleteAllCompletedExercises();

    @Query("SELECT * FROM completed_exercise_table WHERE exercise_date = :date")
    LiveData<List<CompletedExerciseItem>> getCompletedExerciseByDate(LocalDateTime date);

    @Query("SELECT * FROM completed_exercise_table")
    LiveData<List<CompletedExerciseItem>> getAllCompletedExercises();

}
