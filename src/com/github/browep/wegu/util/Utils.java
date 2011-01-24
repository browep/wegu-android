package com.github.browep.wegu.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.github.browep.wegu.Constants;

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

    public static Toast shortToastMessage(Context context, CharSequence text, Toast previousToast) {
//        if(previousToast != null)
//            previousToast.cancel();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        return toast;
    }
}
