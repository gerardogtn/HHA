package com.gerardogtn.hha.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gerardogtn.hha.R;
import com.gerardogtn.hha.data.model.Alarm;
import com.gerardogtn.hha.ui.activity.MainActivity;
import com.gerardogtn.hha.ui.fragment.AlarmFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by braulio on 3/10/15.
 */
public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private List<Alarm> mAlarms;
    private LayoutInflater inflater;
    private AlarmFragment mFragment;

    public AlarmAdapter(Context context, List<Alarm> alarms, AlarmFragment fragment) {
        this.mAlarms = alarms;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mFragment = fragment;
    }

    public void setAlarms(List<Alarm> alarms){
        this.mAlarms = alarms;
    }

    @Override
    public AlarmAdapter.AlarmViewHolder onCreateViewHolder (ViewGroup parent,
                                                    int viewType) {
        View item = inflater.inflate(R.layout.item_alarm, parent, false);
        return new AlarmViewHolder(item);
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        holder.setData(mAlarms.get(position), mFragment);
    }

    @Override
    public int getItemCount() {
        if (mAlarms != null){
            return mAlarms.size();
        } else {
            return 0;
        }
    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder {

        private Alarm mAlarm;
        private AlarmFragment mFragment;

        private int onColor = 0xFF9C27B0;
        private int offColor = 0xFFFF9800;

        @Bind(R.id.btn_enable_alarm)
        Button enableAlarm;

        @Bind(R.id.txt_alarm)
        TextView alarmTextView;


        public AlarmViewHolder (View view){
            super (view);
            ButterKnife.bind(this, view);
        }

        public void setData(Alarm alarm, AlarmFragment fragment){
            this.mAlarm = alarm;
            this.mFragment = fragment;
            alarmTextView.setText(alarm.getFormattedString());
            drawToggleButton();
        }

        @OnClick(R.id.btn_enable_alarm)
        public void onClickToggleButton(){
            mAlarm.setIsOn(!mAlarm.isOn());
            drawToggleButton();
        }

        private void drawToggleButton(){
            if (mAlarm.isOn()){
                enableAlarm.setText("APAGAR");
                enableAlarm.setBackgroundColor(onColor);
            }
            else{
                enableAlarm.setText("ENCENDER");
                enableAlarm.setBackgroundColor(offColor);
            }
            mFragment.setAlarmEnabled(mAlarm.getId(), mAlarm.isOn());
        }
    }
}
