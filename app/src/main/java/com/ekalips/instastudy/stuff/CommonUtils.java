package com.ekalips.instastudy.stuff;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Ekalips on 10/18/17.
 */

public class CommonUtils {
    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int adjustAlpha(int color, @FloatRange(from = 0, to = 1) float factor) {
        int alpha = (int) (255 * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    public static String getDayName(@IntRange(from = 1, to = 7) int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, day);
        SimpleDateFormat sd = new SimpleDateFormat("EEEE", Locale.getDefault());
        return sd.format(calendar.getTime());
    }
}
