package com.gerardogtn.hha.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.gerardogtn.hha.R;
import com.gerardogtn.hha.ui.adapter.AlarmAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {
    private RecyclerView newRecyclerView;
    private RecyclerView.Adapter newAdapter;
    private RecyclerView.LayoutManager newLayoutManager;

    @Bind(R.id.list_alarms)
    RecyclerView list_alarms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newRecyclerView.setHasFixedSize(true);

        newLayoutManager = new LinearLayoutManager(this);
        newRecyclerView.setLayoutManager(newLayoutManager);

        List<String> alarms = new ArrayList<>();
        alarms.add("08:00");
        alarms.add("09:00");
        newAdapter = new AlarmAdapter(alarms);
        newRecyclerView.setAdapter(newAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
