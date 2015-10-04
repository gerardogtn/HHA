package com.gerardogtn.hha.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.gerardogtn.hha.data.local.AlarmDatabase.AlarmDbConstants;
import com.gerardogtn.hha.data.local.AlarmDatabase.AlarmDbHelper;
import com.gerardogtn.hha.data.model.Alarm;
import com.gerardogtn.hha.service.AlarmService;

import java.util.Calendar;
import java.util.List;

/**
 * Created by gerardogtn on 10/3/15.
 */
public class AlarmHelper extends BroadcastReceiver {

    @Override
   public void onReceive(Context context, Intent intent) {
        setAlarms(context);
    }


    public static void setAlarms(Context context){
        cancelAlarms(context);

        AlarmDbHelper dbHelper = new AlarmDbHelper(context);
        List<Alarm> alarms =  dbHelper.getAlarms();

        for (Alarm alarm : alarms){
            if (alarm.isOn()){
                PendingIntent intent = createPendingIntent(context, alarm);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, alarm.getHour());
                calendar.set(Calendar.MINUTE, alarm.getMinute());
                calendar.set(Calendar.SECOND, 00);

                setAlarm(context, calendar, intent);
            }
        }
    }

    public static void setAlarm(Context context, Calendar calendar, PendingIntent intent){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intent);
        } else{
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intent);
        }
    }

    public static void cancelAlarms(Context context){
        AlarmDbHelper dbHelper = new AlarmDbHelper(context);

        List<Alarm> alarms =  dbHelper.getAlarms();

        if (alarms != null) {
            for (Alarm alarm : alarms) {
                if (alarm.isOn()) {
                    PendingIntent pIntent = createPendingIntent(context, alarm);
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    alarmManager.cancel(pIntent);
                }
            }
        }
    }


    @Nullable
    public static PendingIntent createPendingIntent(Context context, Alarm alarm){
        Intent intent = new Intent(context, AlarmService.class);
        intent.putExtra(AlarmDbConstants.COLUMN_ID, alarm.getId());
        intent.putExtra(AlarmDbConstants.COLUMN_HOUR, alarm.getHour());
        intent.putExtra(AlarmDbConstants.COLUMN_MINUTE, alarm.getMinute());
        intent.putExtra(AlarmDbConstants.COLUMN_IS_ON, alarm.isOn());

        return PendingIntent.getService(context,
                (int) alarm.getId(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
