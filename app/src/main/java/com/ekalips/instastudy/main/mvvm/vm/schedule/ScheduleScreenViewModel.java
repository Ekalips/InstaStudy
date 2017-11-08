package com.ekalips.instastudy.main.mvvm.vm.schedule;

import android.databinding.ObservableField;
import android.util.Log;
import android.util.SparseArray;

import com.ekalips.instastudy.data.lessons.LessonsDataProvider;
import com.ekalips.instastudy.data.lessons.models.Lesson;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.main.contract.ScheduleScreenContract;
import com.ekalips.instastudy.main.mvvm.model.DummyLesson;
import com.ekalips.instastudy.main.mvvm.model.LessonDay;
import com.ekalips.instastudy.stuff.CommonUtils;
import com.ekalips.instastudy.stuff.Const;
import com.ekalips.instastudy.stuff.learning.WeekType;
import com.wonderslab.base.rx.RxRequests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ekalips on 11/7/17.
 */

public class ScheduleScreenViewModel extends ScheduleScreenContract.ViewModel {

    private static final String TAG = ScheduleScreenViewModel.class.getSimpleName();

    private final ObservableField<List<LessonDay>> lessons = new ObservableField<>(new ArrayList<>());
    private final ObservableField<WeekType> weekType = new ObservableField<>(null);

    private final SparseArray<SparseArray<? super Lesson>> oddLessons = new SparseArray<>();
    private final SparseArray<SparseArray<? super Lesson>> evenLessons = new SparseArray<>();

    private final LessonsDataProvider lessonsDataProvider;

    @Inject
    public ScheduleScreenViewModel(RxRequests rxRequests, @DataProvider LessonsDataProvider lessonsDataProvider) {
        super(rxRequests);
        this.lessonsDataProvider = lessonsDataProvider;

        setUpWeek();
        fetchLessons();
    }

    private void setUpWeek() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        weekType.set(week % 2 == 0 ? WeekType.EVEN : WeekType.ODD);
    }

    private void fetchLessons() {
        request(lessonsDataProvider.getLessons(true).observeOn(Schedulers.computation()),
                this::onFetchLessonSuccess, this::onFetchLessonError);
    }

    private void onFetchLessonError(Throwable throwable) {
        Log.e(TAG, "onFetchLessonError: ", throwable);
    }

    private void onFetchLessonSuccess(List<? extends Lesson> lessons) {
        oddLessons.clear();
        evenLessons.clear();
        this.lessons.get().clear();

        for (int i = 0; i < Const.MAX_DAYS; i++) {
            evenLessons.put(i, new SparseArray<>());
            oddLessons.put(i, new SparseArray<>());
            for (int j = 0; j < Const.MAX_LESSONS; j++) {
                evenLessons.get(i).put(j, new DummyLesson());
                oddLessons.get(i).put(j, new DummyLesson());
            }
        }

        for (Lesson lesson :
                lessons) {
            switch (WeekType.fromType(lesson.getDate().getWeek())) {
                case ODD: {
                    oddLessons.get(lesson.getDate().getDay()).put(lesson.getDate().getOrdinal(), lesson);
                    break;
                }
                case EVEN: {
                    evenLessons.get(lesson.getDate().getDay()).put(lesson.getDate().getOrdinal(), lesson);
                    break;
                }
                case ANY: {
                    oddLessons.get(lesson.getDate().getDay()).put(lesson.getDate().getOrdinal(), lesson);
                    evenLessons.get(lesson.getDate().getDay()).put(lesson.getDate().getOrdinal(), lesson);
                    break;
                }
            }
        }
        validateWeekListDueToWeek();
    }

    private void validateWeekListDueToWeek() {
        switch (weekType.get()) {
            case ANY:
            case ODD: {
                lessons.set(getWeekScheduleFrom(oddLessons));
                break;
            }
            case EVEN: {
                lessons.set(getWeekScheduleFrom(evenLessons));
                break;
            }
        }
        lessons.notifyChange();
    }

    private List<LessonDay> getWeekScheduleFrom(SparseArray<SparseArray<? super Lesson>> weekLessons) {
        List<LessonDay> list = new ArrayList<>();
        for (int i = 0; i < Const.MAX_DAYS; i++) {
            List<? super Lesson> lessonsForList = new ArrayList<>();
            SparseArray<? super Lesson> lessonDay = weekLessons.get(i, new SparseArray<>());
            for (int j = 0; j < Const.MAX_LESSONS; j++) {
                Lesson lesson = (Lesson) lessonDay.get(j, new DummyLesson());
                lessonsForList.add(lesson);
            }
            list.add(new LessonDay(CommonUtils.getDayName(i + 2), lessonsForList));
        }
        return list;
    }

    @Override
    public void switchWeek() {
        if (weekType.get() == WeekType.ODD) {
            weekType.set(WeekType.EVEN);
        } else {
            weekType.set(WeekType.ODD);
        }
        validateWeekListDueToWeek();
    }

    @Override
    public ObservableField<List<LessonDay>> getLessons() {
        return lessons;
    }

    @Override
    public ObservableField<WeekType> getWeekType() {
        return weekType;
    }
}
