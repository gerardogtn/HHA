package com.gerardogtn.hha.ui.fragment;


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

import com.gerardogtn.hha.R;
import com.gerardogtn.hha.ui.adapter.AlarmAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment {

    @Bind(R.id.list_alarms)
    RecyclerView mRecycleView;

    @Bind(R.id.btn_new_alarm)
    FloatingActionButton mFab;

    // TODO: Change to List<Alarm>
    private List<String> mAlarms;

    public AlarmFragment() {
        this.mAlarms = new ArrayList<>();
        mAlarms.add("08:00");
        mAlarms.add("09:00");
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
        return root;
    }

    private void setUpRecyclerView() {
        Context context = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(linearLayoutManager);
        AlarmAdapter alarmsAdapter = new AlarmAdapter(context, mAlarms);
        mRecycleView.setAdapter(alarmsAdapter);
    }


}
