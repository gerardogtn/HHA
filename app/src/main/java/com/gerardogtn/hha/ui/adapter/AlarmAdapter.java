package com.gerardogtn.hha.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gerardogtn.hha.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by braulio on 3/10/15.
 */
public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    // TODO: change to List<Alarm>
    private List<String> alarms;
    private LayoutInflater inflater;

    public AlarmAdapter(Context context, List<String> alarms) {
        this.alarms = alarms;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public AlarmAdapter.AlarmViewHolder onCreateViewHolder (ViewGroup parent,
                                                    int viewType) {
        View item = inflater.inflate(R.layout.item_alarm, parent, false);
        return new AlarmViewHolder(item);
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        holder.setData(alarms.get(position));
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_alarm)
        TextView alarmTextView;

        public AlarmViewHolder (View view){
            super (view);
            ButterKnife.bind(this, view);
        }

        //TODO: Change String to Alarm.
        public void setData(String alarm){
            alarmTextView.setText(alarm);
        }
    }
}
