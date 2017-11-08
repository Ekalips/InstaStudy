package com.ekalips.instastudy.data.lessons;

import com.ekalips.instastudy.data.lessons.models.Lesson;
import com.ekalips.instastudy.data.lessons.sources.local.LocalLessonDataProvider;
import com.ekalips.instastudy.data.lessons.sources.remote.RemoteLessonDataProvider;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ekalips on 11/8/17.
 */

public interface LessonsDataProvider extends LocalLessonDataProvider, RemoteLessonDataProvider{

    Observable<List<? extends Lesson>> getLessons(boolean fetchRemotely);

}
