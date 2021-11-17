package com.example.wor.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "stats")
public class Stats {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private  String weight;
    private  String height;

    public Stats (String weight, String height){
        this.weight= weight;
        this.height= height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
