package com.example.wor.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wor.R;
import com.example.wor.room.database.StatsDatabase;

import java.util.ArrayList;
import java.util.List;

public class StatsAcivity extends AppCompatActivity {
    private EditText editHeight;
    private EditText editWeight;
    private Button btnAddStats;
    private RecyclerView rvcStats;
    private StatsAdapter statsAdapter;
    private List<Stats> mListStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stats_acivity);
        initUi();
        statsAdapter = new StatsAdapter();
        mListStats = new ArrayList<>();
        statsAdapter.setData(mListStats);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this);
        rvcStats.setAdapter(statsAdapter);

        btnAddStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStats();
            }


        });
        loadData();

    }
    private void initUi(){
        editHeight= findViewById(R.id.edit_height);
        editWeight= findViewById(R.id.edit_weight);
        btnAddStats= findViewById(R.id.btn_add_stats);
        rvcStats= findViewById(R.id.rcv_stats);
    }

    private void addStats() {
        String strWeight = editWeight.getText().toString().trim();
        String strHeight = editHeight.getText().toString().trim();
        if(TextUtils.isEmpty(strWeight)|| TextUtils.isEmpty(strHeight)){
            return;

        }
        Stats stats = new Stats(strWeight, strHeight);
        if(isStatsExist (stats)){
            Toast.makeText(this, "Exist Information", Toast.LENGTH_SHORT).show();
            return;
        }

        StatsDatabase.getInstance(this).statsDao().insertStats(stats);
        Toast.makeText(this, "Add Stats successfully", Toast.LENGTH_SHORT).show();

        editHeight.setText("");
        editHeight.setText("");
        hideSoftKeyBoard();
        loadData();
    }

    public void hideSoftKeyBoard(){
        try{
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    private void loadData(){
        mListStats = StatsDatabase.getInstance(this).statsDao().getListStats();
        statsAdapter.setData(mListStats);
    }

    private boolean isStatsExist(Stats stats){
        List<Stats> list = StatsDatabase.getInstance(this).statsDao().checkStats(stats.getWeight());
        return list != null && list.isEmpty();
    }
}