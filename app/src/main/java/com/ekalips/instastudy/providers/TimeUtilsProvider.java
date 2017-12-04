package com.ekalips.instastudy.providers;

import android.content.Context;
import android.text.format.DateUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ekalips on 12/4/17.
 */

@Singleton
public class TimeUtilsProvider {

    private final Context context;

    @Inject
    public TimeUtilsProvider(Context context) {
        this.context = context;
    }

    public String getRelativeTime(long time) {
        return DateUtils.getRelativeTimeSpanString(time, System.currentTimeMillis(), DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_SHOW_DATE).toString();
    }
}
