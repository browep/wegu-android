package com.github.browep.wegu;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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

    protected Dao dao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new Dao(getSharedPreferences(Constants.PREFS_FILE_NAME, 0));
    }

    protected List<String> daysAddedStringList(boolean[] days) {
        List<String> daysAdded = new LinkedList<String>();
        for (int i = 1; i < 8; i++) {
            if (days[i])
                daysAdded.add(Constants.DAY_MAP_NAMES[i]);
        }
        return daysAdded;
    }

    protected void removeAlarmData() {
        dao.removePreference(Constants.HOUR_OF_DAY);
        dao.removePreference(Constants.MINUTE_OF_DAY);
        for (int i = 0; i < 8; i++)
            dao.removePreference(Constants.DAY_PREPEND + String.valueOf(i));
    }

    protected void setAlarm(int hours, int minutes) {
        Dao.setAlarm(hours, minutes, (AlarmManager) getSystemService(ALARM_SERVICE),getApplicationContext());
    }


}
