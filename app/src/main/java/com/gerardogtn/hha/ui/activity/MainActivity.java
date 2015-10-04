package com.gerardogtn.hha.ui.activity;

import android.app.AlarmManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gerardogtn.hha.R;

import com.gerardogtn.hha.data.model.WakeUp;
import com.gerardogtn.hha.data.remote.APIClent;
import com.gerardogtn.hha.ui.adapter.PagerAdapter;
import com.gerardogtn.hha.ui.fragment.AlarmFragment;
import com.gerardogtn.hha.ui.fragment.ProfileFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private AlarmManager mAlarmManager;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;

    @Bind(R.id.pager)
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        setUpViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
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
        }if(id == R.id.post){
            WakeUp wakeUp = new WakeUp(2015, 10, 04, 11, 8, true, 4);
            APIClent.newInstance().addDatePost(wakeUp, new Callback<WakeUp>() {
                @Override
                public void success(WakeUp wakeUp, Response response) {
                    Toast.makeText(MainActivity.this, ":)", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(MainActivity.this, ":(", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();

                }
            });
        }


        return super.onOptionsItemSelected(item);
    }

    private void setUpViewPager() {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(AlarmFragment.newInstance(), "Alarmas" );
        adapter.addFragment(ProfileFragment.newInstance(), "Perfil" );
        mViewPager.setAdapter(adapter);
    }


}
