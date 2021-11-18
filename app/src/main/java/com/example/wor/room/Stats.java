package com.example.wor.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "stats")
public class Stats {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "stats_date")
    public StatsDate mStatsDate;
    @ColumnInfo(name = "weight")
    private String weight;
    @ColumnInfo(name = "height")
    private String height;

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


    public Stats (String weight, String height){
        this.weight= weight;
        this.height= height;
    }

    public StatsDate getmStatsDate() {
        return mStatsDate;
    }

    public void setmStatsDate(StatsDate mStatsDate) {
        this.mStatsDate = mStatsDate;
    }


//    public String getWeight() {
//        return weight;
//    }
//
//    public void setWeight(String weight) {
//        this.weight = weight;
//    }
//
//    public String getHeight() {
//        return height;
//    }
//
//    public void setHeight(String height) {
//        this.height = height;
//    }

//    public void Stast(Stats stast) {
//        weight = stast.weight();
//        height = stast.height();
//        mStastDate = stast.mStastDate();
//    }

//    public Stats(StatsDate mStastDate, String weight, String height) {
//        this.mStastDate = mStastDate;
//        this.weight = weight;
//        this.height = height;
//    }
}
