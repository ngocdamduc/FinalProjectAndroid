package com.example.wor.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDate;

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

    public AvailableExerciseItem(ExerciseType mExerciseType, @NonNull String mExerciseName, boolean mFavorite, boolean mCustom, boolean mIsChecked) {
        this.mExerciseType = mExerciseType;
        this.mExerciseName = mExerciseName;
        this.mFavorite = mFavorite;
        this.mCustom = mCustom;
        this.mIsChecked = false;
    }
// General constructor for available exercise item


    // Empty constructor
    public AvailableExerciseItem() {
    }


    // Copy constructor
    public AvailableExerciseItem(AvailableExerciseItem anotherItem) {
        mExerciseType = anotherItem.getExerciseType();
        mExerciseName = anotherItem.getExerciseName();
        mFavorite = anotherItem.isFavorite();
        mCustom = anotherItem.isCustom();
        mIsChecked = anotherItem.isChecked();
    }

    // Getters and setters for fields
    ExerciseType getExerciseType() {
        return mExerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        this.mExerciseType = exerciseType;
    }

    public void setExerciseName(String exerciseName) {
        this.mExerciseName = exerciseName;
    }

    public String getExerciseName() {
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
}