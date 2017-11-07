package com.ekalips.instastudy.data.lessons.models;

import com.ekalips.instastudy.data.user.User;

/**
 * Created by Ekalips on 11/7/17.
 */

public interface Lesson {

    String getId();

    User getTeacher();

    String getLocation();

    Subject getSubject();

    LessonDate getDate();

}
