package com.example.wor.room;

import com.example.wor.MainActivity;

public class Session {
    //Static Fields
    private static final int BODY_WEIGHT = -2;
    private static int sId = 0;
    //Non-static Fields
    private final ExerciseType mType;
    private int mId;
    private int mReps;
    private int mWeight;
    private int mDuration;
    private int mIntensity;
    private boolean mIsEmpty;
    private boolean mIsChecked;
    private boolean mIsReadOnly;

    //Empty Session Constructor
    public Session(ExerciseType exerciseType) {
        mType = exerciseType;
        mIsEmpty = true;
        mIsChecked = false;
        mIsReadOnly = false;
        mId = sId++;
        switch (mType) {
            case CALISTHENICS:
                mReps = MainActivity.EMPTY;
                mWeight = BODY_WEIGHT;
                break;
            case CARDIO:
                mDuration = MainActivity.EMPTY;
                mIntensity = MainActivity.EMPTY;
                break;
            case STRENGTH:
                mReps = MainActivity.EMPTY;
                mWeight = MainActivity.EMPTY;
        }
    }
    // Session constructor
    public Session(ExerciseType exerciseType, int param1, int param2){
        mType = exerciseType;
        switch (mType){
            case CALISTHENICS:
                mReps = param1;
                mWeight = BODY_WEIGHT;
                break;
            case CARDIO:
                mDuration = param1;
                mIntensity = param2;
                break;
            case STRENGTH:
                mReps = param1;
                mWeight = param2;
                break;
        }
        mIsEmpty =false;
        mIsChecked = false;
        mIsReadOnly = false;
        mId = sId++;
    }
    //Deep Copy Constructor
    public Session(Session anotherSession){
        mType = anotherSession.getType();
        mId = anotherSession.getId();
        switch (mType){
            case CALISTHENICS:
                mReps = anotherSession.getReps();
                mWeight = BODY_WEIGHT;
                break;
            case CARDIO:
                mDuration = anotherSession.getDuration();
                mIntensity = anotherSession.getIntensity();
            case STRENGTH:
                mReps = anotherSession.getReps();
                mWeight = anotherSession.getWeight();
        }
        mIsChecked = anotherSession.isChecked();
        mIsEmpty = anotherSession.isEmpty();
        mIsReadOnly = anotherSession.isReadOnly();
    }
    // Setters and Getters
    public ExerciseType getType() {
        return mType;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public int getReps() {
        return mReps;
    }

    public void setReps(int mReps) {
        this.mReps = mReps;
    }

    public int getWeight() {
        return mWeight;
    }

    public void setWeight(int mWeight) {
        this.mWeight = mWeight;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public int getIntensity() {
        return mIntensity;
    }

    public void setIntensity(int mIntensity) {
        this.mIntensity = mIntensity;
    }

    public boolean isEmpty() {
        return mIsEmpty;
    }

    public void setEmpty(boolean mIsEmpty) {
        this.mIsEmpty = mIsEmpty;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean mIsChecked) {
        this.mIsChecked = mIsChecked;
    }

    public boolean isReadOnly() {
        return mIsReadOnly;
    }

    public void setReadOnly(boolean mIsReadOnly) {
        this.mIsReadOnly = mIsReadOnly;
    }
    //Other methods
    public boolean compareSession(Session anotherSession) {
        if (this.getType() != anotherSession.getType()) {
            // Throw exception --- to do.
            return false;
        } else {
            switch (this.getType()) {
                case CALISTHENICS:
                case STRENGTH:
                    if (this.getReps() != anotherSession.getReps() || this.getWeight() != anotherSession.getWeight() ||
                            this.isChecked() != anotherSession.isChecked() || this.isReadOnly() != anotherSession.isReadOnly()) {
                        return false;
                    }
                    break;
                case CARDIO:
                    if (this.getDuration() != anotherSession.getDuration() || this.getIntensity() != anotherSession.getIntensity() ||
                            this.isChecked() != anotherSession.isChecked() || this.isReadOnly() != anotherSession.isReadOnly()) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
}
