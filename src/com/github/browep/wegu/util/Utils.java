package com.github.browep.wegu.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.github.browep.wegu.Constants;

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
}
