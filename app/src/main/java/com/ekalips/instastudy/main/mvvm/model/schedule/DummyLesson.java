package com.ekalips.instastudy.main.mvvm.model.schedule;

import com.ekalips.instastudy.data.lessons.models.Lesson;
import com.ekalips.instastudy.data.lessons.models.LessonDate;
import com.ekalips.instastudy.data.lessons.models.Subject;
import com.ekalips.instastudy.data.user.User;

/**
 * Created by Ekalips on 11/8/17.
 */

public class DummyLesson implements Lesson {
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
        return null;
    }

    @Override
    public Subject getSubject() {
        return null;
    }

    @Override
    public LessonDate getDate() {
        return null;
    }
}
