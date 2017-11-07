package com.ekalips.instastudy.data.lessons.sources.local.models;

import com.ekalips.instastudy.data.lessons.models.LessonDate;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Ekalips on 11/7/17.
 */

@Entity
public class LocalLessonDate implements LessonDate {

    @Id
    private long boxId;

    private int day;
    private int ordinal;
    private int week;

    public long getBoxId() {
        return boxId;
    }

    public void setBoxId(long boxId) {
        this.boxId = boxId;
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
