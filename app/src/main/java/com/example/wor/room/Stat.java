package com.example.wor.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDate;

@Entity(tableName = "stat_table")
public class Stat {

    // Fields
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "stat_date")
    private LocalDate mDate = LocalDate.now();

    @ColumnInfo(name = "weight")
    private int mWeight;

    @ColumnInfo(name = "height")
    private int mHeight;

    // Empty constructor
    public Stat() {
    }

    // General constructor
    public Stat(@NonNull LocalDate date, int weight, int height) {
        mDate = date;
        mWeight = weight;
        mHeight = height;
    }

    // Getters and setters
    public LocalDate getMDate() {
        return mDate;
    }

    public void setMDate(LocalDate mStatDate) {
        this.mDate = mStatDate;
    }

    public int getMWeight() {
        return mWeight;
    }

    public void setMWeight(int mWeight) {
        this.mWeight = mWeight;
    }

    public int getMHeight() {
        return mHeight;
    }

    public void setMHeight(int mHeight) {
        this.mHeight = mHeight;
    }

}
