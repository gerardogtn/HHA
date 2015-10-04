package com.gerardogtn.hha.service.AlarmDatabase;

/**
 * Created by gerardogtn on 10/3/15.
 */
public class AlarmDbConstants {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "friends.db";

    public static final String TABLE_ALARMS = "friends";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IS_ON = "is_on";
    public static final String COLUMN_MINUTE = "minute";
    public static final String COLUMN_HOUR = "hour";

    public static final String CREATE_TABLE_ALARMS =
            "CREATE TABLE " + TABLE_ALARMS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_IS_ON + " BOOLEAN, " +
                    COLUMN_MINUTE + " INTEGER, " +
                    COLUMN_HOUR + " INTEGER);";
}
