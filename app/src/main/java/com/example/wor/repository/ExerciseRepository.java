package com.example.wor.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wor.room.AvailableExerciseDao;
import com.example.wor.room.AvailableExerciseItem;
import com.example.wor.room.CompletedExerciseDao;
import com.example.wor.room.CompletedExerciseItem;
import com.example.wor.room.WORDatabase;

import org.threeten.bp.LocalDate;

import java.util.List;

public class ExerciseRepository {
    //Fields
    private CompletedExerciseDao mCompletedExerciseDao;
    private AvailableExerciseDao mAvailableExerciseDao;

    private  LiveData<List<AvailableExerciseItem>> mAllAvailableExercise;
    private LiveData<List<CompletedExerciseItem>> mAllCompletedExercises;

    // Constructor
    public ExerciseRepository(Application application) {
        WORDatabase database = WORDatabase.getInstance(application);
        //complete
        mCompletedExerciseDao = database.completedExerciseDao();
        mAllCompletedExercises = mCompletedExerciseDao.getAllCompletedExercises();
        //available
        mAvailableExerciseDao = database.availableExerciseDao();
        mAllAvailableExercise = mAvailableExerciseDao.getAllAvailableExercises();
    }

    // Methods for CompletedExerciseDao
    public void insert(CompletedExerciseItem completedExerciseItem) {
        new InsertCompletedExerciseAsyncTask(mCompletedExerciseDao).execute(completedExerciseItem);
    }

    public void update(CompletedExerciseItem completedExerciseItem) {
        new UpdateCompletedExerciseAsyncTask(mCompletedExerciseDao).execute(completedExerciseItem);
    }

    public void delete(CompletedExerciseItem completedExerciseItem) {
        new DeleteCompletedExerciseAsyncTask(mCompletedExerciseDao).execute(completedExerciseItem);
    }

    public void deleteAllCompletedExerciseByDate(LocalDate date) {
        new DeleteAllCompletedExerciseByDateAsyncTask(mCompletedExerciseDao).execute(date);
    }

    public void deleteAllCompletedExercises() {
        new DeleteAllCompletedExerciseAsyncTask(mCompletedExerciseDao).execute();
    }
    public LiveData<List<CompletedExerciseItem>> getAllCompletedExerciseByDate(LocalDate date) { return mCompletedExerciseDao.getCompletedExerciseByDate(date); }

    // AsyncTasks for Completed Exercise Item
    private static class InsertCompletedExerciseAsyncTask extends AsyncTask<CompletedExerciseItem, Void, Void> {

        private CompletedExerciseDao completedExerciseDao;

        private InsertCompletedExerciseAsyncTask(CompletedExerciseDao completedExerciseDao) {
            this.completedExerciseDao = completedExerciseDao;
        }

        @Override
        protected Void doInBackground(CompletedExerciseItem... completedExerciseItem) {
            completedExerciseDao.insert(completedExerciseItem[0]);
            return null;
        }
    }

    private static class UpdateCompletedExerciseAsyncTask extends AsyncTask<CompletedExerciseItem, Void, Void> {

        private CompletedExerciseDao completedExerciseDao;

        private UpdateCompletedExerciseAsyncTask(CompletedExerciseDao completedExerciseDao) {
            this.completedExerciseDao = completedExerciseDao;
        }

        @Override
        protected Void doInBackground(CompletedExerciseItem... completedExerciseItem) {
            completedExerciseDao.update(completedExerciseItem[0]);
            return null;
        }
    }

    private static class DeleteCompletedExerciseAsyncTask extends AsyncTask<CompletedExerciseItem, Void, Void> {

        private CompletedExerciseDao completedExerciseDao;

        private DeleteCompletedExerciseAsyncTask(CompletedExerciseDao completedExerciseDao) {
            this.completedExerciseDao = completedExerciseDao;
        }

        @Override
        protected Void doInBackground(CompletedExerciseItem... completedExerciseItem) {
            completedExerciseDao.delete(completedExerciseItem[0]);
            return null;
        }
    }

    private static class DeleteAllCompletedExerciseByDateAsyncTask extends AsyncTask<LocalDate, Void, Void> {

        private CompletedExerciseDao completedExerciseDao;

        private DeleteAllCompletedExerciseByDateAsyncTask(CompletedExerciseDao completedExerciseDao) {
            this.completedExerciseDao = completedExerciseDao;
        }

        @Override
        protected Void doInBackground(LocalDate... dates) {
            completedExerciseDao.deleteAllCompletedExerciseByDate(dates[0]);
            return null;
        }
    }

    private static class DeleteAllCompletedExerciseAsyncTask extends AsyncTask<Void, Void, Void> {

        private CompletedExerciseDao completedExerciseDao;

        private DeleteAllCompletedExerciseAsyncTask(CompletedExerciseDao completedExerciseDao) {
            this.completedExerciseDao = completedExerciseDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            completedExerciseDao.deleteAllCompletedExercises();
            return null;
        }
    }


    //Method Available Exercise
    public void insert(AvailableExerciseItem availableExerciseItem) {
        new InsertAvailableExerciseAsyncTask(mAvailableExerciseDao).execute(availableExerciseItem);
    }

    public void update(AvailableExerciseItem availableExerciseItem) {
        new UpdateAvailableExerciseAsyncTask(mAvailableExerciseDao).execute(availableExerciseItem);
    }

    public void delete(AvailableExerciseItem availableExerciseItem) {
        new DeleteAvailableExerciseAsyncTask(mAvailableExerciseDao).execute(availableExerciseItem);

    }

    public void deleteAllAvailableExercise() {
        new DeleteAllAvailableExerciseAsyncTask(mAvailableExerciseDao).execute();
    }

    public LiveData<List<AvailableExerciseItem>> getAllAvailableExercise() {
        return mAllAvailableExercise;
    }

    //Async task for Available Exercise
    public static class InsertAvailableExerciseAsyncTask extends AsyncTask<AvailableExerciseItem,Void,Void> {

        private AvailableExerciseDao availableExerciseDao;

        private  InsertAvailableExerciseAsyncTask(AvailableExerciseDao availableExerciseDao) {
            this.availableExerciseDao = availableExerciseDao;
        }

        @Override
        protected Void doInBackground(AvailableExerciseItem... availableExerciseItems) {
            return null;
        }
    }

    private static class UpdateAvailableExerciseAsyncTask extends AsyncTask<AvailableExerciseItem, Void, Void> {

        private AvailableExerciseDao availableExerciseDao;

        private UpdateAvailableExerciseAsyncTask(AvailableExerciseDao availableExerciseDao) {
            this.availableExerciseDao = availableExerciseDao;
        }


        @Override
        protected Void doInBackground(AvailableExerciseItem... availableExerciseItems) {
            return null;
        }
    }

    public static class DeleteAvailableExerciseAsyncTask extends AsyncTask<AvailableExerciseItem, Void, Void> {
        private AvailableExerciseDao availableExerciseDao;

        public DeleteAvailableExerciseAsyncTask(AvailableExerciseDao availableExerciseDao) {
            this.availableExerciseDao = availableExerciseDao;
        }

        @Override
        protected Void doInBackground(AvailableExerciseItem... availableExerciseItems) {
            return null;
        }
    }

    public static class DeleteAllAvailableExerciseAsyncTask extends AsyncTask<Void, Void, Void> {
        private AvailableExerciseDao availableExerciseDao;

        public DeleteAllAvailableExerciseAsyncTask(AvailableExerciseDao availableExerciseDao) {
            this.availableExerciseDao = availableExerciseDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
