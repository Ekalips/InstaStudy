package com.ekalips.instastudy.data.lessons.sources.remote.models;

import com.ekalips.instastudy.data.lessons.models.LessonDate;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekalips on 11/7/17.
 */

class RemoteDate implements LessonDate {

    @SerializedName("day")
    @Expose
    private int day;

    @SerializedName("ordinal")
    @Expose
    private int ordinal;

    @SerializedName("week")
    @Expose
    private int week;

    @SerializedName("dates")
    @Expose
    private long[] dates;

    @Override
    public int getDay() {
        return day;
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public int getWeek() {
        return week;
    }
}
