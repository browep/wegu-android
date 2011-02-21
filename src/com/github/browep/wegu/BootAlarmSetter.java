package com.github.browep.wegu;

import android.app.AlarmManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.github.browep.wegu.util.Log;


/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 2/2/11
 * Time: 12:32 AM
 * To change this template use File | Settings | File Templates.
 */

public class BootAlarmSetter extends Service {
    public void onCreate() {
        super.onCreate();
        Log.i("BootAlarmSetter started");

        Dao dao = new Dao(getSharedPreferences(Constants.PREFS_FILE_NAME,0));
        // initialize from stored or default to now
        int hours = dao.getIntPreference(Constants.HOUR_OF_DAY);

        int minutes = dao.getIntPreference(Constants.MINUTE_OF_DAY);

        boolean[] days = dao.getDays();

        Dao.setAlarm(hours, minutes, (AlarmManager) getSystemService(ALARM_SERVICE),getApplicationContext());

        Log.i("Done setting alarm");
        stopSelf();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}