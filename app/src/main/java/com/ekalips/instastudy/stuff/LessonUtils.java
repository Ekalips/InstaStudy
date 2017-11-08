package com.ekalips.instastudy.stuff;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.stuff.learning.WeekType;

/**
 * Created by Ekalips on 11/8/17.
 */

public class LessonUtils {

    public static int getBackgroundColorForWeekType(Context context, int weekInt) {
        WeekType weekType = WeekType.fromType(weekInt);
        switch (weekType) {
            case ODD: {
                return CommonUtils.adjustAlpha(ContextCompat.getColor(context, R.color.colorPrimary), 0.1f);
            }
            case EVEN:
                return CommonUtils.adjustAlpha(ContextCompat.getColor(context, R.color.colorAccent), 0.1f);
            default:
                return Color.TRANSPARENT;
        }
    }

    public static String getWeekName(Context context, WeekType weekType) {
        if (weekType == null) {
            return "";
        }
        switch (weekType) {
            case EVEN: {
                return context.getString(R.string.week_even);
            }
            case ODD: {
                return context.getString(R.string.week_odd);
            }
            default:
                return "";
        }
    }

}
