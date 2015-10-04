package com.gerardogtn.hha.ui.fragment;


import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.gerardogtn.hha.R;
import com.gerardogtn.hha.data.model.Alarm;
import com.gerardogtn.hha.ui.adapter.AlarmAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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


    @Bind(R.id.list_alarms)
    RecyclerView mRecycleView;

    @Bind(R.id.btn_new_alarm)
    FloatingActionButton mFab;

    private List<Alarm> mAlarms;

    public AlarmFragment() {
        this.mAlarms = new ArrayList<>();
        mAlarms.add(new Alarm(8, 0));
        mAlarms.add(new Alarm(9, 0));
    }

    public static AlarmFragment newInstance() {
        AlarmFragment fragment = new AlarmFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


    private void setUpRecyclerView() {
        Context context = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mAdapter = new AlarmAdapter(context, mAlarms);
        mRecycleView.setAdapter(mAdapter);
    }

    @OnClick(R.id.btn_new_alarm)
    public void addAlarm(){
        mTimePickerDialog.show();
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        mAlarms.add(new Alarm(hour, minute));
        mAdapter.notifyDataSetChanged();
    }

    private void setUpTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();

        mTimePickerDialog = new TimePickerDialog(getActivity(),
                this,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false);
    }
}
