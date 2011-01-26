package com.github.browep.wegu;

import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 1/23/11
 * Time: 1:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Constants {
    public static final String ALARM_ALERT_ACTION = "com.github.browep.wegu.ALARM_ALERT";
    public static final String SCREEN_OFF = "screen_off";
    public static final String PREFS_FILE_NAME = "main_prefs_file";
    public static int CHECKBOX_PREPEND = 333000;
    public static final String CURRENT_DATE_LONG = "current_date_long";

    public static int[] DAY_MAP = new int[]{
            Calendar.MONDAY,
            Calendar.TUESDAY,
            Calendar.WEDNESDAY,
            Calendar.THURSDAY,
            Calendar.FRIDAY,
            Calendar.SATURDAY,
            Calendar.SUNDAY,
    };

    public static String[] DAY_MAP_NAMES = new String[]{
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday",
    };
}
