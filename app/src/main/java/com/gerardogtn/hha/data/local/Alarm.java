package com.gerardogtn.hha.data.local;

import android.net.Uri;

/**
 * Created by gerardogtn on 10/3/15.
 */
public class Alarm {

    private boolean isOn;
    private int hour;
    private int minute;
    private Uri tone;


    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if (minute < 60 && minute >= 0){
            this.minute = minute;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn() {
        this.isOn = true;
    }

    public void setOff() {
        this.isOn = false;
    }
}
