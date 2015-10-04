package com.gerardogtn.hha.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.gerardogtn.hha.ui.activity.WakeUpActivity;
import com.gerardogtn.hha.util.AlarmHelper;

/**
 * Created by gerardogtn on 10/3/15.
 */
public class AlarmService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent alarmIntent = new Intent(getBaseContext(), WakeUpActivity.class);
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        alarmIntent.putExtras(intent);
        getApplication().startActivity(alarmIntent);

        AlarmHelper.setAlarms(this);

        return super.onStartCommand(intent, flags, startId);
    }
}
