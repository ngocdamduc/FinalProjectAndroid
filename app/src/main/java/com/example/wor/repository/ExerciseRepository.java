package com.example.wor.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import com.example.wor.room.CompletedExerciseDao;
import com.example.wor.room.CompletedExerciseItem;
import com.example.wor.room.WORDatabase;

import org.threeten.bp.LocalDate;

import java.util.List;

public class ExerciseRepository {
    //Fields
    private CompletedExerciseDao mCompletedExerciseDao;
    private LiveData<List<CompletedExerciseItem>> mAllCompletedExercises;

    // Constructor
    public ExerciseRepository(Application application) {
        WORDatabase database = WORDatabase.getInstance(application);
        mCompletedExerciseDao = database.completedExerciseDao();
        mAllCompletedExercises = mCompletedExerciseDao.getAllCompletedExercises();
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
}
