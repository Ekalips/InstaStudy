package com.ekalips.instastudy.data.lessons.sources.local;

import com.ekalips.instastudy.data.lessons.models.Lesson;
import com.ekalips.instastudy.data.lessons.sources.local.models.LocalLesson;
import com.wonderslab.base.rx.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.reactivex.Single;

/**
 * Created by Ekalips on 11/8/17.
 */

public class LocalLessonDataProviderImpl implements LocalLessonDataProvider {

    private final Box<LocalLesson> localLessonBox;

    @Inject
    public LocalLessonDataProviderImpl(Box<LocalLesson> localLessonBox) {
        this.localLessonBox = localLessonBox;
    }

    @Override
    public Single<List<? extends Lesson>> getLessons() {
        return RxUtils.wrapAsIO(Single.fromCallable(localLessonBox::getAll));
    }
}
