package com.github.browep.wegu;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 1/23/11
 * Time: 9:02 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class WeguActivity extends Activity {

    public Long getLongPreference(String prefName) {
        SharedPreferences settings = getSharedPreferences(Constants.PREFS_FILE_NAME, 0);
        return settings.getLong(prefName, 0);
    }

    public Boolean getBooleanPreference(String prefName){
        SharedPreferences settings = getSharedPreferences(Constants.PREFS_FILE_NAME, 0);
        return settings.getBoolean(prefName, false);
    }
}
