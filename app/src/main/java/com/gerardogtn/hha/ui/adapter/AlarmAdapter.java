package com.gerardogtn.hha.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gerardogtn.hha.R;

import java.util.List;

/**
 * Created by braulio on 3/10/15.
 */
public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {
    private List<String> alarms;


    public class AlarmViewHolder extends RecyclerView.ViewHolder {

        public TextView alarmTextView;
        public AlarmViewHolder (View view){
            super (view);
        }
    }


    public AlarmAdapter(List<String> alarms) {
        this.alarms = alarms;
    }

    @Override
    public AlarmAdapter.AlarmViewHolder onCreateViewHolder (ViewGroup parent,
                                                    int viewType) {
        View layout = LayoutInflater.from (parent.getContext())
                .inflate(R.layout.item_alarm, parent, false);

        AlarmViewHolder alarmViewHolder = new AlarmViewHolder(layout);
        return alarmViewHolder;
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        holder.alarmTextView.setText(alarms.get(position));

    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }
}
