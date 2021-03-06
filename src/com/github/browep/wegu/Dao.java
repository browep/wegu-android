package com.github.browep.wegu;

import android.app.AlarmManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import com.github.browep.wegu.util.Log;
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

    public Dao(Context context){
        this.sharedPreferences = context.getSharedPreferences(Constants.PREFS_FILE_NAME,0);
    }

    public static void setAlarm(int hours, int minutes, AlarmManager am, Context context) {
        am.cancel(Utils.getAlarmPendingIntent(context));
        am.setRepeating(AlarmManager.RTC_WAKEUP, Utils.nearestFireTimeForDay(hours,minutes), Constants.DAY_INTERVAL_MILLIS, Utils.getAlarmPendingIntent(context));
    }

    public boolean[] getDays() {
        boolean[] temp_days = new boolean[8];
        for (int i = 1; i < 8; i++) {
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
        Log.i("DAO: set INT pref " + prefName + "->" + String.valueOf(value));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(prefName, value);
        editor.commit();
    }

    public void setBooleanPreference(String prefName, boolean value) {
        Log.i("DAO: set BOOLEAN pref " + prefName + "->" + String.valueOf(value));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(prefName, value);
        editor.commit();
    }
}
