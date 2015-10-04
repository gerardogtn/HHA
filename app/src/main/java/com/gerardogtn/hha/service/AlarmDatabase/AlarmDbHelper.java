package com.gerardogtn.hha.service.AlarmDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
