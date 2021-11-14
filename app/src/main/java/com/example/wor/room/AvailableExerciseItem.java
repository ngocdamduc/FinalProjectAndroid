package com.example.wor.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDate;

import java.util.List;

@Entity(tableName = "availavle_exercise_table")
public class AvailableExerciseItem {

    @PrimaryKey(autoGenerate = true)
    private int mId; // Look into this later -> Can't find getter for mId
    @ColumnInfo(name = "exercise_type")
    private ExerciseType mExerciseType;
    @ColumnInfo(name = "exercise_name")
    private String mExerciseName;
    @ColumnInfo(name = "exercise_date")
    private org.threeten.bp.LocalDate mExerciseDate;
    @ColumnInfo(name = "list_of_session")
    private List<Session> mListOfSessions;
    @ColumnInfo(name = "note")
    private String mNote;
    @Ignore
    private boolean mIsChecked;

    //Empty constructor
    public AvailableExerciseItem() {};

    //constructor to available exercise
    public AvailableExerciseItem(ExerciseType type, String exerciseName, LocalDate exerciseDate, List<Session> listOfSessions, String note){
        mExerciseType = type;
        mExerciseName = exerciseName;
        mExerciseDate = exerciseDate;
        mListOfSessions = listOfSessions;
        mNote = note;
        mIsChecked = false;
    }

    public  AvailableExerciseItem(CompletedExerciseItem anotherItem){
        mId = anotherItem.getId();
        mExerciseType = anotherItem.getExerciseType();
        mExerciseName = anotherItem.getExerciseName();
        mExerciseDate = anotherItem.getExerciseDate();
        mListOfSessions = anotherItem.getListOfSessions();
        mNote = anotherItem.getNote();
        mIsChecked = anotherItem.IsChecked();
    }

    //getter and setter
    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public ExerciseType getExerciseType() {
        return mExerciseType;
    }

    public void setExerciseType(ExerciseType mExerciseType) {
        this.mExerciseType = mExerciseType;
    }

    public String getExerciseName() {
        return mExerciseName;
    }

    public void setExerciseName(String mExerciseName) {
        this.mExerciseName = mExerciseName;
    }

    public LocalDate getExerciseDate() {
        return mExerciseDate;
    }

    public void setExerciseDate(LocalDate mExerciseDate) {
        this.mExerciseDate = mExerciseDate;
    }

    public List<Session> getListOfSessions() {
        return mListOfSessions;
    }

    public void setListOfSessions(List<Session> mListOfSessions) {
        this.mListOfSessions = mListOfSessions;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String mNote) {
        this.mNote = mNote;
    }

    public boolean isIsChecked() {
        return mIsChecked;
    }

    public void setIsChecked(boolean mIsChecked) {
        this.mIsChecked = mIsChecked;
    }

}
