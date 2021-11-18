package com.example.wor.room;

public enum ExerciseType {
    CARDIO(0),
    STRENGTH(1),
    CALISTHENICS(2);
    //Fields
    private final int mCategory;

    //Constructor
    ExerciseType (int category){this.mCategory = category;}
    //Getter

    public int getCategory() {
        return mCategory;
    }
    public String getCategoryName(){
        String name = null;
        switch (mCategory){
            case 0:
                name = "Cardio";
                break;
            case 1:
                name = "Strength";
                break;
            case 2:
                name = "Calisthenics";
        }
        return name;
    }
}
