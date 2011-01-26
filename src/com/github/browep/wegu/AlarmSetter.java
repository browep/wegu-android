package com.github.browep.wegu;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.*;
import android.widget.Button;
import com.github.browep.wegu.util.Log;
import com.github.browep.wegu.util.Utils;

import java.util.Calendar;
import java.util.Date;
import android.widget.CheckBox;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 1/20/11
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlarmSetter extends WeguActivity {
    private static final int MILLIS_IN_THE_FUTURE = 5 * 1000;

    PendingIntent mainIntent;

    private AlarmSetter self = this;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Long currentDateLong = getLongPreference(Constants.CURRENT_DATE_LONG);
        TextView currentTimeTextView = (TextView) findViewById(R.id.current_alarm_time);
        if(currentDateLong != 0){
            Calendar currentAlarmDate = Calendar.getInstance();
            currentAlarmDate.setTimeInMillis(currentDateLong);
            currentTimeTextView.setText("Alarm is currently set for " + currentAlarmDate.toString());
        }

        setContentView(R.layout.alarm_setter);

        CheckBox checkBox;
        for (int i=0;i<Constants.DAY_MAP.length;i++) {

            checkBox = new CheckBox(getApplicationContext());
            checkBox.setChecked(false);
            checkBox.setText(Constants.DAY_MAP_NAMES[i]);
            checkBox.setId(Constants.CHECKBOX_PREPEND + i);
            if(i < 5)
                ((LinearLayout) findViewById(R.id.days_of_week_layout)).addView(checkBox);
            else
                ((LinearLayout) findViewById(R.id.sat_sun_layout)).addView(checkBox);

        }

        Intent intent = new Intent(Constants.ALARM_ALERT_ACTION);

        mainIntent = PendingIntent.getBroadcast(
                        getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        Button button = (Button) findViewById(R.id.start_alarm);
        button.setOnClickListener(openAlarmDialog);

        Button cancelButton = (Button) findViewById(R.id.stop_alarm);
        cancelButton.setOnClickListener(stopAlarmListener);

        Button timeButton = (Button) findViewById(R.id.time_display);
        timeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(Constants.TIME_DIALOG_ID);
            }
        });

        // update the time display
        updateTimeDisplay();


    }

    private void updateTimeDisplay() {
        // time button
        Button timeButton = (Button) findViewById(R.id.time_display);
        int hour = getDisplayHourOfDay();
        if (hour < 0)
            hour = Calendar.getInstance().get(Calendar.HOUR);
        int minute = getMinute();
        if(minute < 0)
            minute = Calendar.getInstance().get(Calendar.MINUTE);
        timeButton.setText(hour + ":" + minute + " " + getAMorPM());
    }


    private View.OnClickListener startAlarmListener = new View.OnClickListener() {
        public void onClick(View v) {
            Context context = getApplicationContext();
            CharSequence text = "Starting alarm for " + MILLIS_IN_THE_FUTURE / 1000 + " seconds in the future.";
            Toast startAlarmToast = Utils.shortToastMessage(context, text);
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

//            am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + MILLIS_IN_THE_FUTURE, mainIntent);
            am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + MILLIS_IN_THE_FUTURE, mainIntent);

            Utils.shortToastMessage(context, "Alarm set for " + MILLIS_IN_THE_FUTURE / 1000 + " seconds in the future.", startAlarmToast);

        }
    };


    private  View.OnClickListener openAlarmDialog = new View.OnClickListener() {
        public void onClick(View view) {
            StringBuilder sb = new StringBuilder("Alarm will fire on days: ");
            for(int i=0;i<Constants.DAY_MAP.length;i++){

                CheckBox cb = (CheckBox) findViewById(Constants.CHECKBOX_PREPEND + i);
                if (cb.isChecked()){
                    sb.append(Constants.DAY_MAP_NAMES[i]).append(", ");
                }
            }
            sb.append(" at " ).append(getDisplayHourOfDay()).append(":").append(getMinute()).append(" ").append(getAMorPM());
            Utils.shortToastMessage(getApplicationContext(),sb.toString());

        }
    } ;


    private View.OnClickListener stopAlarmListener = new View.OnClickListener() {
        public void onClick(View v) {

            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
            Toast cancelingToast = Utils.shortToastMessage(getApplicationContext(), "Canceling alarm.");
            am.cancel(mainIntent);
            Utils.shortToastMessage(getApplicationContext(), "Alarm canceled.", cancelingToast);

        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case Constants.TIME_DIALOG_ID:
                int hour = get24HourOfDay();
                if (hour < 0)
                    hour = Calendar.getInstance().get(Calendar.HOUR);
                int minute = getMinute();
                if(minute < 0)
                    minute = Calendar.getInstance().get(Calendar.MINUTE);
                return new TimePickerDialog(this,
                        mTimeSetListener, hour, minute, false);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
    new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            setIntPreference(Constants.HOUR_OF_DAY, hourOfDay);
            setIntPreference(Constants.MINUTE_OF_DAY, minute);
            updateTimeDisplay();
        }
    };


}
