package com.ekalips.instastudy.data.lessons.test;

import com.ekalips.instastudy.data.lessons.models.LessonDate;

/**
 * Created by Ekalips on 11/8/17.
 */

public class TestDate implements LessonDate {

    private int day;
    private int ordinal;
    private int week;

    public TestDate(int day, int ordinal, int week) {
        this.day = day;
        this.ordinal = ordinal;
        this.week = week;
    }

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
