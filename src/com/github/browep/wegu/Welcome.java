package com.github.browep.wegu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    }


    private View.OnClickListener openAlarmSetter = new View.OnClickListener() {
        public void onClick(View view) {

            Intent setAlarmIntent = new Intent();
            setAlarmIntent.setClass(self,AlarmSetter.class);
            startActivity(setAlarmIntent);

        }
    };
}
