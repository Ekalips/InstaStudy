package com.ekalips.instastudy.data.lessons.mappers;

import android.support.annotation.Nullable;

import com.ekalips.instastudy.data.lessons.models.LessonDate;
import com.ekalips.instastudy.data.lessons.sources.local.models.LocalLessonDate;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ekalips on 11/14/17.
 */

@Singleton
public class LessonDateDataMapper {

    @Inject
    public LessonDateDataMapper() {
    }

    public LocalLessonDate toLocal(@Nullable LessonDate lessonDate) {
        LocalLessonDate date = new LocalLessonDate();
        if (lessonDate != null) {
            date.setDay(lessonDate.getDay());
            date.setOrdinal(lessonDate.getOrdinal());
            date.setWeek(lessonDate.getWeek());
        }
        return date;
    }
}
