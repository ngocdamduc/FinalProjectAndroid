package com.example.wor.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wor.room.Stat;
import com.example.wor.room.StatDao;
import com.example.wor.room.WORDatabase;

import java.time.LocalDate;
import java.util.List;

public class StatRepository {

    //field
    private StatDao mStatDao;

    //constructor
    private StatRepository(Application application) {
        WORDatabase database = WORDatabase.getInstance(application);
        mStatDao = database.statDao();
    }

    public void insert(Stat stat) {
        new InsertStatAsyncTask(mStatDao).execute(stat);
    }

    public void update(Stat stat) {
        new UpdateStatAsyncTask(mStatDao).execute(stat);
    }

    public void delete(Stat stat) {
        new DeleteStatAsyncTask(mStatDao).execute(stat);
    }

    public void deleteStatByDate(LocalDate mDate) {
        return mStatDao.deleteStatByDate(mDate);
    }

    public void deleteAllStats() {
        new DeleteAllStatsAsyncTask(mStatDao).execute();
    }

    public LiveData<List<Stat>> getStatByDate(LocalDate mDate) {
        return mStatDao.getStatByDate(mDate);
    }

    public LiveData<List<Stat>> getAllStats() {
        return mStatDao.getAllstats();
    }

    //async class
    public static class InsertStatAsyncTask extends AsyncTask<Stat,Void,Void>{

        private StatDao statDao;

        private InsertStatAsyncTask(StatDao statDao) {
            this.statDao = statDao;
        }

        @Override
        protected Void doInBackground(Stat... stats) {
            return null;
        }
    }

    public static class UpdateStatAsyncTask extends AsyncTask<Stat,Void,Void>{

        private StatDao statDao;

        private UpdateStatAsyncTask(StatDao statDao) {
            this.statDao = statDao;
        }

        @Override
        protected Void doInBackground(Stat... stats) {
            return null;
        }
    }

    public static class DeleteStatAsyncTask extends AsyncTask<Stat,Void,Void>{

        private StatDao statDao;

        private DeleteStatAsyncTask(StatDao statDao) {
            this.statDao = statDao;
        }

        @Override
        protected Void doInBackground(Stat... stats) {
            return null;
        }
    }

    public static class DeleteStatByDateAsyncTask extends AsyncTask<Stat,Void,Void>{

        private StatDao statDao;

        private DeleteStatByDateAsyncTask(StatDao statDao) {
            this.statDao = statDao;
        }

        @Override
        protected Void doInBackground(Stat... stats) {
            return null;
        }
    }

    public static class DeleteAllStatsAsyncTask extends AsyncTask<Stat,Void,Void>{

        private StatDao statDao;

        private DeleteAllStatsAsyncTask(StatDao statDao) {
            this.statDao = statDao;
        }

        @Override
        protected Void doInBackground(Stat... stats) {
            return null;
        }
    }
}
