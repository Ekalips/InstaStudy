package com.ekalips.instastudy.data.lessons.sources.local;

import com.ekalips.instastudy.data.lessons.mappers.LessonDataMapper;
import com.ekalips.instastudy.data.lessons.models.Lesson;
import com.ekalips.instastudy.data.lessons.sources.local.models.LocalLesson;
import com.ekalips.instastudy.data.lessons.sources.local.models.LocalLesson_;
import com.wonderslab.base.rx.RxUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.reactivex.Single;

/**
 * Created by Ekalips on 11/8/17.
 */

public class LocalLessonDataProviderImpl implements LocalLessonDataProvider {

    private final Box<LocalLesson> localLessonBox;
    private final LessonDataMapper lessonDataMapper;

    @Inject
    public LocalLessonDataProviderImpl(Box<LocalLesson> localLessonBox, LessonDataMapper lessonDataMapper) {
        this.localLessonBox = localLessonBox;
        this.lessonDataMapper = lessonDataMapper;
    }

    @Override
    public Single<List<? extends Lesson>> getLessons() {
        return RxUtils.wrapAsIO(Single.fromCallable(localLessonBox::getAll));
    }

    @Override
    public void saveLessons(List<? extends Lesson> data) {
        List<LocalLesson> localLessons = new ArrayList<>(data.size());
        for (Lesson lesson :
                data) {
            LocalLesson localLesson = lessonDataMapper.toLocal(lesson);
            List<LocalLesson> foundLessons = localLessonBox.find(LocalLesson_.id, lesson.getId());
            if (foundLessons != null && foundLessons.size() > 0) {
                localLesson.setBoxId(foundLessons.get(0).getBoxId());
            }
            localLessons.add(localLesson);
        }
        localLessonBox.put(localLessons);
    }
}
