package com.github.browep.wegu;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.github.browep.wegu.util.Utils;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 1/20/11
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlarmSetter extends Activity {
    private static final int MILLIS_IN_THE_FUTURE = 10   * 1000;
    PendingIntent mainIntent;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alarm_setter);

        Intent intent = new Intent(this, Main.class);

        mainIntent = PendingIntent.getActivity(this, 0,
                intent
                , Intent.FLAG_ACTIVITY_NEW_TASK);

        Button button = (Button) findViewById(R.id.start_alarm);
        button.setOnClickListener(startAlarmListener);

        Button cancelButton = (Button) findViewById(R.id.stop_alarm);
        cancelButton.setOnClickListener(stopAlarmListener);
    }


    private View.OnClickListener startAlarmListener = new View.OnClickListener() {
        public void onClick(View v) {
            Context context = getApplicationContext();
            CharSequence text = "Starting alarm for " + MILLIS_IN_THE_FUTURE / 1000 + " seconds in the future.";
            Toast startAlarmToast = Utils.shortToastMessage(context, text);
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

//            am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + MILLIS_IN_THE_FUTURE, mainIntent);
//            am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + MILLIS_IN_THE_FUTURE, mainIntent);

            AlarmReceiver alarmReceiver = new AlarmReceiver();
//            alarmReceiver.get


            Utils.shortToastMessage(context, "Alarm set for " + MILLIS_IN_THE_FUTURE / 1000 + " seconds in the future.", startAlarmToast);

        }
    };


    private View.OnClickListener stopAlarmListener = new View.OnClickListener() {
        public void onClick(View v) {

            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
            Toast cancelingToast = Utils.shortToastMessage(getApplicationContext(), "Canceling alarm.");
            am.cancel(mainIntent);
            Utils.shortToastMessage(getApplicationContext(), "Alarm canceled.", cancelingToast);

        }
    };


}
