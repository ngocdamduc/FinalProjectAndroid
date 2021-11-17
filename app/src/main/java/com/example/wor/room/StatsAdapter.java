package com.example.wor.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wor.R;

import java.util.List;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatsViewHolder>{

    private List<Stats> mlistStats;
    public void setData(List<Stats> list){
        this.mlistStats = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stats, parent, false);
        return new StatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {
        Stats stats =  mlistStats.get(position);
        if (stats == null){
            return;
        }
            holder.tv_height.setText((String) stats.getHeight());
        holder.tv_weight.setText((String) stats.getWeight());
    }

    @Override
    public int getItemCount() {
        if (mlistStats != null){
            return mlistStats.size();
        }
        return 0;
    }
    public class StatsViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_height;
        private TextView tv_weight;

        public StatsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_height = itemView.findViewById(R.id.tv_height);
            tv_weight = itemView.findViewById(R.id.tv_weight);
        }
    }
}
