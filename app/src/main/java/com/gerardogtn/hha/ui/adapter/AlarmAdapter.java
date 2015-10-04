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
    private Context mContext;

    public AlarmAdapter(Context context, List<Alarm> alarms) {
        this.mAlarms = alarms;
        inflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public AlarmAdapter.AlarmViewHolder onCreateViewHolder (ViewGroup parent,
                                                    int viewType) {
        View item = inflater.inflate(R.layout.item_alarm, parent, false);
        return new AlarmViewHolder(item);
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        holder.setData(mAlarms.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        return mAlarms.size();
    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder {

        private Alarm mAlarm;

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

        public void setData(Alarm alarm, Context context){
            this.mAlarm = alarm;
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
        }
    }
}
