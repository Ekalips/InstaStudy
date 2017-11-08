package com.ekalips.instastudy.data.lessons.sources.remote;

import com.ekalips.instastudy.data.lessons.models.Lesson;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Ekalips on 11/8/17.
 */

public interface RemoteLessonDataProvider {

    Single<List<? extends Lesson>> getLessons(String token);

}
