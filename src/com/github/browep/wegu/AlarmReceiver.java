package com.github.browep.wegu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.github.browep.wegu.util.AlarmAlertWakeLock;
import com.github.browep.wegu.util.Log;


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
            // Send a text notification to the screen.
//        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification notification = new Notification(0, "WEGU Alarm!", System.currentTimeMillis());
//        nm.notify(0, notification);

//        Utils.shortToastMessage(context,"WEGU Alarm!");

        // acquire cpu lock
        AlarmAlertWakeLock.acquireCpuWakeLock(context);

        Intent playAlarm = new Intent(context,MainAlarm.class);
        playAlarm.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        context.startActivity(playAlarm);





   }

}
