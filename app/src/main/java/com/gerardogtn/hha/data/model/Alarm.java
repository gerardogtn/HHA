package com.gerardogtn.hha.data.model;

import android.net.Uri;

/**
 * Created by gerardogtn on 10/3/15.
 */
public class Alarm {

    private long id;
    private boolean isOn;
    private int hour;
    private int minute;
//    private Uri tone;

    public Alarm() {
        id = -1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setIsOn(boolean isOn) {this.isOn = isOn;}

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        if (hour < 24 && hour >= 0){
            this.hour = hour;
        }
        else{
            throw new IllegalArgumentException();
        }
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

    public void makeOn() {
        this.isOn = true;
    }

    public void makeOff() {
        this.isOn = false;
    }
}
