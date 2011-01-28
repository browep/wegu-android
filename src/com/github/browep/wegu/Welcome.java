package com.github.browep.wegu;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.github.browep.wegu.util.Log;
import com.github.browep.wegu.util.Utils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 1/25/11
 * Time: 8:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Welcome extends WeguActivity {
    private Welcome self = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        findViewById(R.id.edit_alarm).setOnClickListener(openAlarmSetter);

        Button disableButton = ((Button)findViewById(R.id.disable_alarm));
        disableButton.setOnClickListener(stopAlarmListener);


        updateButtonsAndText();

    }

    private void updateButtonsAndText() {

        Button disableButton = ((Button)findViewById(R.id.disable_alarm));

        int hours = getIntPreference(Constants.HOUR_OF_DAY);
        int minutes = getIntPreference(Constants.MINUTE_OF_DAY);
        boolean[] days = getDays();
        String amPm = getAMorPM(hours);

        List<String> daysAdded = daysAddedStringList(days);

        TextView daysDisplay = (TextView) findViewById(R.id.welcome_date_display);
        if(!(hours < 0 || minutes < 0)){
            daysDisplay.setText("Alarm is currently set for " + getDisplayHourOfDay(hours) + ":" + String.format("%02d",minutes) + " " + amPm + " on " + Utils.join(daysAdded, ", "));
            disableButton.setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.edit_alarm)).setText("Edit Alarm");

        }
        else{
            ((Button)findViewById(R.id.edit_alarm)).setText("Set Alarm");
            disableButton.setVisibility(View.INVISIBLE);
            daysDisplay.setText("");
        }
    }


    private View.OnClickListener openAlarmSetter = new View.OnClickListener() {
        public void onClick(View view) {

            Intent setAlarmIntent = new Intent();
            setAlarmIntent.setClass(self,AlarmSetter.class);
            startActivity(setAlarmIntent);

        }
    };


    private View.OnClickListener stopAlarmListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast cancelingToast = Utils.shortToastMessage(getApplicationContext(), "Canceling alarm.");

            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
            am.cancel(getAlarmPendingIntent());
            removeAlarmData();

            Utils.shortToastMessage(getApplicationContext(), "Alarm canceled.", cancelingToast);

            updateButtonsAndText();
        }
    };


    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        updateButtonsAndText();
    }
}
