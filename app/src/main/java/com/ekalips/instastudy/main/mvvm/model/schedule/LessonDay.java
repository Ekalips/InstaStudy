package com.ekalips.instastudy.main.mvvm.model.schedule;

import com.ekalips.instastudy.data.lessons.models.Lesson;

import java.util.List;

/**
 * Created by Ekalips on 11/8/17.
 */

public class LessonDay {

    private String dayName;
    private List<? super Lesson> lessons;

    public LessonDay(String dayName, List<? super Lesson> lessons) {
        this.dayName = dayName;
        this.lessons = lessons;
    }

    public String getDayName() {
        return dayName;
    }

    public List<? super Lesson> getLessons() {
        return lessons;
    }
}
