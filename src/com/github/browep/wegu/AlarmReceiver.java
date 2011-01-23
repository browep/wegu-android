package com.github.browep.wegu;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.github.browep.wegu.util.Utils;


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
            // Send a text notification to the screen.
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        nm.notifyWithText(R.id.alarm,
//                          "WEGU Alarm!!!",
//                          NotificationManager.LENGTH_SHORT,
//                          null);
        Notification notification = new Notification(0, "WEGU Alarm!", System.currentTimeMillis());
        nm.notify(0, notification);

        Utils.shortToastMessage(context,"WEGU Alarm!");
   }

}
