package com.github.browep.wegu;

import android.app.AlarmManager;
import android.content.Context;
import android.content.SharedPreferences;
import com.github.browep.wegu.util.Utils;


/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 2/2/11
 * Time: 9:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Dao {

    SharedPreferences sharedPreferences;

    public Dao(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public static void setAlarm(int hours, int minutes, boolean[] days, AlarmManager am, Context context) {
        for (int i = 0; i < Constants.DAY_MAP.length; i++) {
            if (days[i])
                am.setRepeating(AlarmManager.RTC_WAKEUP, Utils.timestampOfNextOccurenceOfDayAtTime(i, hours, minutes), Constants.WEEK_INTERVAL_MILLIS, Utils.getAlarmPendingIntent(context));
        }
    }

    public boolean[] getDays() {
        boolean[] temp_days = new boolean[8];
        for (int i = 0; i < Constants.DAY_MAP.length; i++) {
            temp_days[i] = getBooleanPreference(Constants.DAY_PREPEND + String.valueOf(i));
        }
        return temp_days;
    }

    protected void removePreference(String prefName) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(prefName);
        editor.commit();
    }

    public Long getLongPreference(String prefName) {

        return sharedPreferences.getLong(prefName, 0);
    }

    public Boolean getBooleanPreference(String prefName) {

        return sharedPreferences.getBoolean(prefName, false);
    }

    public int getIntPreference(String prefName) {

        return sharedPreferences.getInt(prefName, -1);
    }

    public void setIntPreference(String prefName, int value) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(prefName, value);
        editor.commit();
    }

    public void setBooleanPreference(String prefName, boolean value) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(prefName, value);
        editor.commit();
    }
}
