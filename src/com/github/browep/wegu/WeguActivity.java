package com.github.browep.wegu;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 1/23/11
 * Time: 9:02 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class WeguActivity extends Activity {

    protected void removePreference(String prefName){
        SharedPreferences settings = getSharedPreferences(Constants.PREFS_FILE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(prefName);
        editor.commit();
    }

    public Long getLongPreference(String prefName) {
        SharedPreferences settings = getSharedPreferences(Constants.PREFS_FILE_NAME, 0);
        return settings.getLong(prefName, 0);
    }

    public Boolean getBooleanPreference(String prefName){
        SharedPreferences settings = getSharedPreferences(Constants.PREFS_FILE_NAME, 0);
        return settings.getBoolean(prefName, false);
    }

    public int getIntPreference(String prefName){
        SharedPreferences settings = getSharedPreferences(Constants.PREFS_FILE_NAME, 0);
        return settings.getInt(prefName, -1);
    }

    public void setIntPreference(String prefName, int value){
        SharedPreferences settings = getSharedPreferences(Constants.PREFS_FILE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(prefName,value);
        editor.commit();
    }

    public void setBooleanPreference(String prefName, boolean value){
        SharedPreferences settings = getSharedPreferences(Constants.PREFS_FILE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(prefName, value);
        editor.commit();
    }


    protected String getAMorPM(int hour) {
        if(hour < 12)
            return "AM";
        else
            return "PM";
    }

    protected int getDisplayHourOfDay(int hour){
        hour = hour % 12;
        if(hour == 0)
            return 12;
        else
            return hour;
    }

    protected boolean[] getDays() {
        boolean[] temp_days = new boolean[7];
        for (int i=0;i< Constants.DAY_MAP.length;i++) {
            temp_days[i] = getBooleanPreference(Constants.DAY_PREPEND + String.valueOf(i));
        }
        return temp_days;
    }

    protected List<String> daysAddedStringList(boolean[] days) {
        List<String> daysAdded = new LinkedList<String>();
        for (int i = 0; i < Constants.DAY_MAP.length; i++) {
            if(days[i])
                daysAdded.add(Constants.DAY_MAP_NAMES[i]);
        }
        return daysAdded;
    }

    protected PendingIntent getAlarmPendingIntent() {
        Intent intent = new Intent(Constants.ALARM_ALERT_ACTION);

        return PendingIntent.getBroadcast(
                getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    protected void removeAlarmData(){
        removePreference(Constants.HOUR_OF_DAY);
        removePreference(Constants.MINUTE_OF_DAY);
        for(int i=0;i<Constants.DAY_MAP.length;i++)
            removePreference(Constants.DAY_PREPEND + String.valueOf(i));
    }
}
