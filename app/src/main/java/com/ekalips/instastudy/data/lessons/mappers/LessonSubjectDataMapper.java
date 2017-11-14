package com.ekalips.instastudy.data.lessons.mappers;

import android.support.annotation.Nullable;

import com.ekalips.instastudy.data.lessons.models.Subject;
import com.ekalips.instastudy.data.lessons.sources.local.models.LocalSubject;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ekalips on 11/14/17.
 */

@Singleton
public class LessonSubjectDataMapper {
    @Inject
    public LessonSubjectDataMapper() {
    }

    public LocalSubject toLocal(@Nullable Subject subject) {
        LocalSubject localSubject = new LocalSubject();
        if (subject != null) {
            localSubject.setId(subject.getId());
            localSubject.setTitle(subject.getTitle());
        }
        return localSubject;
    }
}
