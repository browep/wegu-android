package com.github.browep.wegu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.github.browep.wegu.util.Log;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 2/1/11
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Boot Received");
        Intent setAlarm = new Intent(context,BootAlarmSetter.class);
        setAlarm.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startService(setAlarm);

    }



}
