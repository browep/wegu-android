package com.github.browep.wegu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.github.browep.wegu.util.AlarmAlertWakeLock;
import com.github.browep.wegu.util.Log;
import com.github.browep.wegu.util.Utils;

import java.util.Calendar;


/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 1/22/11
 * Time: 4:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlarmReceiver extends BroadcastReceiver {
      // Display an alert that we've received a message.
    @Override
    public void onReceive(Context context, Intent intent){
        Log.i("AlarmReceiver received an alarm");

        Dao dao = new Dao(context);

        // check to see we actually want to fire the alarm on this day
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        if (dao.getBooleanPreference(Constants.DAY_PREPEND + dayOfWeek)) {
            Log.i("We DO WANT to fire alarm for " + dayOfWeek + "->" + Constants.DAY_MAP_NAMES[dayOfWeek]);

            dao.setBooleanPreference(Constants.DO_ALARM, true);

            AlarmAlertWakeLock.acquireCpuWakeLock(context);

            Intent playAlarm = new Intent(context, MainAlarm.class);
            playAlarm.putExtra(Constants.FROM_ALARM, true);
            playAlarm.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            playAlarm.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            context.startActivity(playAlarm);
        } else {
            Log.i("We DONT WANT to fire alarm for " + dayOfWeek + "->" + Constants.DAY_MAP_NAMES[dayOfWeek]);
        }

   }



}
