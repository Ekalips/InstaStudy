package com.ekalips.instastudy.data.lessons.sources.local;

import com.ekalips.instastudy.data.lessons.models.Lesson;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Ekalips on 11/8/17.
 */

public interface LocalLessonDataProvider {

    Single<List<? extends Lesson>> getLessons();

    void saveLessons(List<? extends Lesson> data);

}
