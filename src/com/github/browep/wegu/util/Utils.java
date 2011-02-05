package com.github.browep.wegu.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.github.browep.wegu.Constants;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 1/22/11
 * Time: 5:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    public static Toast shortToastMessage(Context context, CharSequence text) {
        return shortToastMessage(context, text, null);
    }

    public static Toast longToastMessage(Context context, CharSequence text) {
        return longToastMessage(context, text,null);
    }

    public static Toast reallyLongToastMessage(Context context, CharSequence text){
        Toast toast = Toast.makeText(context, text, 10);
        toast.show();
        return toast;

    }

    public static Toast shortToastMessage(Context context, CharSequence text, Toast previousToast) {
//        if(previousToast != null)
//            previousToast.cancel();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        return toast;
    }

    public static Toast longToastMessage(Context context, CharSequence text, Toast previousToast) {
//        if(previousToast != null)
//            previousToast.cancel();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        return toast;
    }

    public static String join(Collection s, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        Iterator iter = s.iterator();
        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }

    public static long timestampOfNextOccurenceOfDayAtTime(int dayofweek, int hours, int minutes){
        // get current day.
        Calendar calendar = Calendar.getInstance();


        // roll the calendar forward until the day of the week is the same
        // if we missed the this time of day, roll forward a day to before checking for the correct day
        if(hours < calendar.get(Calendar.HOUR_OF_DAY) || (hours == calendar.get(Calendar.HOUR_OF_DAY) && minutes <= calendar.get(Calendar.MINUTE)))
            calendar.roll(Calendar.DATE,1);

        while(calendar.get(Calendar.DAY_OF_WEEK) != dayofweek){
            calendar.roll(Calendar.DATE,1);
        }
        // change the hour and minute
        calendar.set(Calendar.HOUR_OF_DAY,hours);
        calendar.set(Calendar.MINUTE,minutes);
        calendar.set(Calendar.SECOND,0);

        Log.i("Setting alarm for day(int): " + calendar.get(Calendar.DAY_OF_WEEK) + " day(string): " + Constants.DAY_MAP_NAMES[calendar.get(Calendar.DAY_OF_WEEK) ] +
            " at " + hours + ":" + minutes
        );

        return calendar.getTimeInMillis();
    }

    public static String getAMorPM(int hour) {
        if(hour < 12)
            return "AM";
        else
            return "PM";
    }

    public static int getDisplayHourOfDay(int hour){
        hour = hour % 12;
        if(hour == 0)
            return 12;
        else
            return hour;
    }


    public static PendingIntent getAlarmPendingIntent(Context context) {
        Intent intent = new Intent(Constants.ALARM_ALERT_ACTION);

        return PendingIntent.getBroadcast(
                context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}
