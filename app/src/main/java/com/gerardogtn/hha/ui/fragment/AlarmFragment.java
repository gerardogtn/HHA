package com.gerardogtn.hha.ui.fragment;


import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.gerardogtn.hha.R;
import com.gerardogtn.hha.data.local.AlarmDatabase.AlarmDbHelper;
import com.gerardogtn.hha.data.model.Alarm;
import com.gerardogtn.hha.ui.adapter.AlarmAdapter;
import com.gerardogtn.hha.util.AlarmHelper;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment implements TimePickerDialog.OnTimeSetListener{

    private int newHour;
    private int newMinute;

    private TimePicker mTimePicker;
    private TimePickerDialog mTimePickerDialog;

    private AlarmAdapter mAdapter;
    private AlarmDbHelper mDbHelper;


    @Bind(R.id.list_alarms)
    RecyclerView mRecycleView;

    @Bind(R.id.btn_new_alarm)
    FloatingActionButton mFab;

    public AlarmFragment() {

    }

    public static AlarmFragment newInstance() {
        AlarmFragment fragment = new AlarmFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new AlarmDbHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_alarm, container, false);
        ButterKnife.bind(this, root);
        setUpRecyclerView();
        setUpTimePickerDialog();
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){
            mAdapter.setAlarms(mDbHelper.getAlarms());
            mAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.btn_new_alarm)
    public void addAlarm(){
        if (mTimePickerDialog != null) {
            mTimePickerDialog.show();
        }
    }

    @Override
    public void onPause() {
        mTimePickerDialog = null;
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpTimePickerDialog();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Alarm newAlarm = new Alarm(hour, minute);
        mDbHelper.createAlarm(newAlarm);
        mAdapter.setAlarms(mDbHelper.getAlarms());
        mAdapter.notifyDataSetChanged();

        //setAlarmEnabled(newAlarm.getId(), newAlarm.isOn());
    }

    private void setUpTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();

        mTimePickerDialog = new TimePickerDialog(getActivity(),
                this,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false);
    }

    public void setAlarmEnabled(long id, boolean isEnabled) {
        AlarmHelper.cancelAlarms(getActivity());
        Alarm model = mDbHelper.getAlarm(id);
        model.setIsOn(isEnabled);
        mDbHelper.update(model);
        AlarmHelper.setAlarms(getActivity());
    }

    private void setUpRecyclerView() {
        Context context = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mAdapter = new AlarmAdapter(context, mDbHelper.getAlarms(), this);
        mRecycleView.setAdapter(mAdapter);
    }

}
