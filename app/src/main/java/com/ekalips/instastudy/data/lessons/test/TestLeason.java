package com.ekalips.instastudy.data.lessons.test;

import com.ekalips.instastudy.data.lessons.models.Lesson;
import com.ekalips.instastudy.data.lessons.models.LessonDate;
import com.ekalips.instastudy.data.lessons.models.Subject;
import com.ekalips.instastudy.data.user.User;

/**
 * Created by Ekalips on 11/8/17.
 */

public class TestLeason implements Lesson {

    private Subject subject;
    private String location;
    private LessonDate date;

    public TestLeason(Subject subject, String location, LessonDate date) {
        this.subject = subject;
        this.location = location;
        this.date = date;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public User getTeacher() {
        return null;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public Subject getSubject() {
        return subject;
    }

    @Override
    public LessonDate getDate() {
        return date;
    }
}
