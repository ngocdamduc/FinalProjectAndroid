package com.example.wor.ui.stats;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wor.repository.StatRepository;
import com.example.wor.room.Stat;

import java.util.List;

public class StatsViewModel extends AndroidViewModel {

    // Fields
    private final StatRepository mRepository;
    private final LiveData<List<Stat>> mAllStat;

    // Constructor
    public StatsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new StatRepository(application);
        mAllStat = mRepository.getAllStats();
    }

    // Methods for Stats
    public void insert(Stat stat) {
        mRepository.insert(stat);
    }

    public void update(Stat stat) {
        mRepository.update(stat);
    }

    public void delete(Stat stat) {
        mRepository.delete(stat);
    }

    LiveData<List<Stat>> getAllStats() {
        return mAllStat;
    }
}
