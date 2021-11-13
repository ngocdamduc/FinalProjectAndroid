package com.example.wor.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.wor.room.CompletedExerciseDao;
import com.example.wor.room.CompletedExerciseItem;

import java.util.List;

public class ExerciseRepository {
    //Fields
    private CompletedExerciseDao mCompletedExerciseDao;
    private LiveData<List<CompletedExerciseItem>> mAllCompletedExercises;
    // Constructor
    public ExerciseRepository(Application application){
    }
}
