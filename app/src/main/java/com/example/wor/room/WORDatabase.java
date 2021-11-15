package com.example.wor.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

@Database(entities = {CompletedExerciseItem.class, AvailableExerciseItem.class}, version = 1, exportSchema = false)
public abstract class WORDatabase extends RoomDatabase {
    //Fields
    private static WORDatabase sInstance;
    public abstract AvailableExerciseDao availableExerciseDao();
    public abstract CompletedExerciseDao completedExerciseDao();

    // Singleton constructor
    public static synchronized WORDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(), WORDatabase.class, "workout_management_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomCallBack)
                    .build();
        }
        return sInstance;
    }

    private static final RoomDatabase.Callback sRoomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(sInstance).execute();
        }
    };

    // Initial available exercise items in the database
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final AvailableExerciseDao mAvailableExerciseDao;

        private PopulateDbAsyncTask(WORDatabase db) {
            mAvailableExerciseDao = db.availableExerciseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Add initial available exercise info
            String[] cardio = {"Walking", "Jogging", "Cycling", "Swimming", "Rowing", "Squash", "Hockey", "Tennis", "Football", "Jump Rope"};
            String[] strength = {"Weighted Squats", "Bench Press", "Leg Press", "Leg Extension", "Biceps Curl", "Weighted Crunch", "Weighted Leg Raise", "Back Extension"};
            String[] calisthenics = {"Muscle-ups", "Squat Jumps", "Front Lever", "Push-ups", "Pull-ups", "Chin-ups", "Squats", "Back Lever", "Handstand", "Dips", "Hyper-extensions", "Leg Raises", "Planks"};
            Set<String> setOfCardio = new TreeSet<>(Arrays.asList(cardio));
            Set<String> setOfStrength = new TreeSet<>(Arrays.asList(strength));
            Set<String> setOfCalisthenics = new TreeSet<>(Arrays.asList(calisthenics));
            for (String exerciseName : setOfCardio) {

            }
            for (String exerciseName : setOfStrength) {

            }
            for (String exerciseName : setOfCalisthenics) {

            }
            return null;
        }
    }
}

