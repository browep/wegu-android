package com.github.browep.wegu;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 1/20/11
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlarmSetter extends Activity {
    private static final int MILLIS_IN_THE_FUTURE = 5 * 1000;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alarm_setter);

        Intent intent = new Intent(this, Main.class);

        pendingIntent = PendingIntent.getActivity(this, 0,
                intent
                , Intent.FLAG_ACTIVITY_NEW_TASK);

        Button button = (Button) findViewById(R.id.start_alarm);
        button.setOnClickListener(startAlarmListener);
    }

    PendingIntent pendingIntent;

    private View.OnClickListener startAlarmListener = new View.OnClickListener() {
        public void onClick(View v) {
            Context context = getApplicationContext();
            CharSequence text = "Starting alarm for " + MILLIS_IN_THE_FUTURE / 1000 + " seconds in the future.";
            shortToastMessage(context, text);
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            am.set(AlarmManager.RTC, System.currentTimeMillis() + MILLIS_IN_THE_FUTURE, pendingIntent);
            shortToastMessage(context, "Alarm set for " + MILLIS_IN_THE_FUTURE /1000 + " seconds in the future.");

        }
    };


    public static void shortToastMessage(Context context, CharSequence text) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
