package com.example.wor.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

@Entity(tableName = "stat_table")
public class Stat {

    // Fields
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "stat_date")
    private LocalDateTime mDate = LocalDateTime.now();

    @ColumnInfo(name = "weight")
    private int mWeight;

    @ColumnInfo(name = "height")
    private int mHeight;

    // Empty constructor
    @Ignore
    public Stat() {
    }

    // General constructor
    public Stat(@NonNull LocalDateTime date, int weight, int height) {
        mDate = date;
        mWeight = weight;
        mHeight = height;
    }

    // Getters and setters
    public LocalDateTime getMDate() {
        return mDate;
    }

    public void setMDate(LocalDateTime mStatDate) {
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
