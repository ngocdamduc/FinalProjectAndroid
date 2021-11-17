package com.example.wor.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "available_exercise_table")
public class AvailableExerciseItem {

    // Fields
    @ColumnInfo(name = "exercise_type")
    private ExerciseType mExerciseType;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "exercise_name")
    private String mExerciseName = "";

    @ColumnInfo(name = "favorite")
    private boolean mFavorite;

    @ColumnInfo(name = "custom")
    private boolean mCustom;

    @Ignore
    private boolean mIsChecked;

    // Empty constructor
    public AvailableExerciseItem() {
    }

    // General constructor for available exercise item
    public AvailableExerciseItem(ExerciseType type, @NonNull String exerciseName, boolean favorite, boolean custom) {
        mExerciseType = type;
        mExerciseName = exerciseName;
        mFavorite = favorite;
        mCustom = custom;
        mIsChecked = false;
    }

    // Copy constructor
    public AvailableExerciseItem(AvailableExerciseItem anotherItem) {
        mExerciseType = anotherItem.getMExerciseType();
        mExerciseName = anotherItem.getMExerciseName();
        mFavorite = anotherItem.isFavorite();
        mCustom = anotherItem.isCustom();
        mIsChecked = anotherItem.isChecked();
    }

    // Getters and setters for fields
    ExerciseType getMExerciseType() {
        return mExerciseType;
    }

    public void setMExerciseType(ExerciseType exerciseType) {
        this.mExerciseType = exerciseType;
    }

    public void setMExerciseName(String exerciseName) {
        this.mExerciseName = exerciseName;
    }

    public String getMExerciseName() {
        return mExerciseName;
    }

    public boolean isFavorite() {
        return mFavorite;
    }

    public void setFavorite(boolean favorite) {
        mFavorite = favorite;
    }

    public boolean isCustom() {
        return mCustom;
    }

    public void setCustom(boolean custom) { mCustom = custom; }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean isChecked) {
        mIsChecked = isChecked;
    }
    
// Other methods
}