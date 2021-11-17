package com.example.wor.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "stats")
public class Stats {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "stats_date")
    private StatsDate mStastDate;
    @ColumnInfo(name = "weight")
    private  String weight;
    @ColumnInfo(name = "height")
    private  String height;

    public StatsDate getmStastDate() {
        return mStastDate;
    }

    public void setmStastDate(StatsDate mStastDate) {
        this.mStastDate = mStastDate;
    }

    public Stats (String weight, String height){
        this.weight= weight;
        this.height= height;
    }


    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void Stast(Stats stast) {
        weight = stast.weight();
        height = stast.height();
        mStastDate = stast.mStastDate();
    }

    public StatsDate mStastDate() {
        return mStastDate;
    }

    public String height() {
        return height;
    }

    public String weight() {
        return weight;
    }

    StatsDate getStastDate() {
        return mStastDate;
    }
    public void setStatsDate(StatsDate statsDate) {
        this.mStastDate = statsDate;
    }

    public Stats(StatsDate mStastDate, String weight, String height) {
        this.mStastDate = mStastDate;
        this.weight = weight;
        this.height = height;
    }
}
