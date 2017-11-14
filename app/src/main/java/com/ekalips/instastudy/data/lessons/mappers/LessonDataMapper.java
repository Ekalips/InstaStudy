package com.ekalips.instastudy.data.lessons.mappers;

import android.support.annotation.Nullable;

import com.ekalips.instastudy.data.lessons.models.Lesson;
import com.ekalips.instastudy.data.lessons.sources.local.models.LocalLesson;
import com.ekalips.instastudy.data.user.mappers.UserDataMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ekalips on 11/14/17.
 */

@Singleton
public class LessonDataMapper {

    private final UserDataMapper userDataMapper;
    private final LessonDateDataMapper lessonDateDataMapper;
    private final LessonSubjectDataMapper lessonSubjectDataMapper;

    @Inject
    public LessonDataMapper(UserDataMapper userDataMapper, LessonDateDataMapper lessonDateDataMapper, LessonSubjectDataMapper lessonSubjectDataMapper) {
        this.userDataMapper = userDataMapper;
        this.lessonDateDataMapper = lessonDateDataMapper;
        this.lessonSubjectDataMapper = lessonSubjectDataMapper;
    }


    public LocalLesson toLocal(@Nullable Lesson lesson) {
        LocalLesson localLesson = new LocalLesson();
        if (lesson != null) {
            localLesson.setLocation(lesson.getLocation());
            localLesson.setId(lesson.getId());
            localLesson.getBoxLocalUserToOne().setTarget(userDataMapper.toBoxLocal(lesson.getTeacher()));
            localLesson.getLocalLessonDateToOne().setTarget(lessonDateDataMapper.toLocal(lesson.getDate()));
            localLesson.getSubjectToOne().setTarget(lessonSubjectDataMapper.toLocal(lesson.getSubject()));
        }
        return localLesson;
    }
}
