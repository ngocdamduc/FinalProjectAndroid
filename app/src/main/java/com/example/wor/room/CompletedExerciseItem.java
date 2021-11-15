package com.example.wor.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDate;

import java.util.List;

@Entity(tableName = "completed_exercise_table")
public class CompletedExerciseItem {
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
    //Empty Constructor
    public CompletedExerciseItem(){}
    //Constructor for   completed Exercises
    public CompletedExerciseItem(ExerciseType type, String exerciseName, LocalDate exerciseDate, List<Session> listOfSessions, String note){
        mExerciseType = type;
        mExerciseName = exerciseName;
        mExerciseDate = exerciseDate;
        mListOfSessions = listOfSessions;
        mNote = note;
        mIsChecked = false;
    }
    // Deep copy constructor
    public  CompletedExerciseItem(CompletedExerciseItem anotherItem){
        mId = anotherItem.getId();
        mExerciseType = anotherItem.getExerciseType();
        mExerciseName = anotherItem.getExerciseName();
        mExerciseDate = anotherItem.getExerciseDate();
        mListOfSessions = anotherItem.getListOfSessions();
        mNote = anotherItem.getNote();
        mIsChecked = anotherItem.IsChecked();
    }
    //Getter and Setter
    public ExerciseType getExerciseType() {
        return mExerciseType;
    }

    public void setExerciseType(ExerciseType mExerciseType) {
        this.mExerciseType = mExerciseType;
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

    public String getExerciseName() {
        return mExerciseName;
    }

    public void setExerciseName(String mExerciseName) {
        this.mExerciseName = mExerciseName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public boolean IsChecked() {
        return mIsChecked;
    }

    public void setIsChecked(Boolean mIsChecked) {
        this.mIsChecked = mIsChecked;
    }
    public LocalDate getExerciseDate() {
        return mExerciseDate;
    }

    public void setExerciseDate(LocalDate exerciseDate) {
        mExerciseDate = exerciseDate;
    }
    // Other methods
    public boolean compareListOfSessions(List<Session> anotherListOfSessions) {
        if (mListOfSessions.size() != anotherListOfSessions.size()) {
            return false;
        }
        for (int i = 0; i< mListOfSessions.size(); i++) {
            Session thisSession = mListOfSessions.get(i);
            Session thatSession = anotherListOfSessions.get(i);
            if (thisSession.getType() != thatSession.getType()) {
                // Throw exception --- to do.
                return false;
            } else {
                switch (thisSession.getType()) {
                    case CALISTHENICS:
                    case STRENGTH:
                        if (thisSession.getReps() != thatSession.getReps() || thisSession.getWeight() != thatSession.getWeight()) {
                            return false;
                        }
                        break;
                    case CARDIO:
                        if (thisSession.getDuration() != thatSession.getDuration() && thisSession.getIntensity() != thatSession.getIntensity()) {
                            return false;
                        }
                        break;
                }
            }
        }
        return true;
    }
}
