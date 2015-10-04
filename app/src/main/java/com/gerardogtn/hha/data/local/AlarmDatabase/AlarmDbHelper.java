package com.gerardogtn.hha.data.local.AlarmDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gerardogtn.hha.data.model.Alarm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerardogtn on 10/3/15.
 */
public class AlarmDbHelper extends SQLiteOpenHelper {

    public AlarmDbHelper(Context context){
        super(context, AlarmDbConstants.DATABASE_NAME, null, AlarmDbConstants.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(AlarmDbConstants.CREATE_TABLE_ALARMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AlarmDbConstants.CREATE_TABLE_ALARMS);
        onCreate(sqLiteDatabase);
    }

    private Alarm load(Cursor c){
        Alarm model = new Alarm();
        model.setId(c.getLong(c.getColumnIndex(AlarmDbConstants.COLUMN_ID)));
        model.setHour(c.getInt(c.getColumnIndex(AlarmDbConstants.COLUMN_HOUR)));
        model.setMinute(c.getInt(c.getColumnIndex(AlarmDbConstants.COLUMN_MINUTE)));
        model.setIsOn(c.getInt(c.getColumnIndex(AlarmDbConstants.COLUMN_IS_ON)) != 0);

        return model;
    }

    private ContentValues write(Alarm model){
        ContentValues contentValues = new ContentValues();
        contentValues.put(AlarmDbConstants.COLUMN_ID, model.getId());
        contentValues.put(AlarmDbConstants.COLUMN_HOUR, model.getHour());
        contentValues.put(AlarmDbConstants.COLUMN_MINUTE, model.getMinute());
        contentValues.put(AlarmDbConstants.COLUMN_IS_ON, model.isOn());
        return contentValues;
    }

    public long createAlarm(Alarm model) {
        ContentValues values = write(model);
        return getWritableDatabase().insert(AlarmDbConstants.TABLE_ALARMS, null, values);
    }

    public Alarm getAlarm(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " +
                AlarmDbConstants.TABLE_ALARMS +
                " WHERE " +
                AlarmDbConstants.COLUMN_ID +
                " = " + id;

        Cursor cursor = db.rawQuery(select, null);

        if (cursor.moveToNext()) {
            return load(cursor);
        }

        return null;
    }

    public long update(Alarm model) {
        ContentValues values = write(model);
        return getWritableDatabase().
                update(AlarmDbConstants.TABLE_ALARMS,
                        values,
                        AlarmDbConstants.COLUMN_ID + " = ?",
                        new String[]{String.valueOf(model.getId())});
    }

    public int delete(long id) {
        return getWritableDatabase().
                delete(AlarmDbConstants.TABLE_ALARMS,
                        AlarmDbConstants.COLUMN_ID + " = ?",
                        new String[]{String.valueOf(id)});
    }


    public List<Alarm> getAlarms() {
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + AlarmDbConstants.TABLE_ALARMS;

        Cursor cursor = db.rawQuery(select, null);

        List<Alarm> alarmList = new ArrayList<Alarm>();

        while (cursor.moveToNext()) {
            alarmList.add(load(cursor));
        }

        if (!alarmList.isEmpty()) {
            return alarmList;
        }

        return null;
    }
}
