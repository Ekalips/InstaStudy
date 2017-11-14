package com.ekalips.instastudy.main.mvvm.vm.schedule;

import android.databinding.ObservableField;
import android.util.Log;
import android.util.SparseArray;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.lessons.LessonsDataProvider;
import com.ekalips.instastudy.data.lessons.models.Lesson;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.ekalips.instastudy.main.contract.ScheduleScreenContract;
import com.ekalips.instastudy.main.mvvm.model.DummyLesson;
import com.ekalips.instastudy.main.mvvm.model.LessonDay;
import com.ekalips.instastudy.main.mvvm.model.MenuItemSelectedEvent;
import com.ekalips.instastudy.stuff.CommonUtils;
import com.ekalips.instastudy.stuff.Const;
import com.ekalips.instastudy.stuff.learning.WeekType;
import com.wonderslab.base.rx.RxRequests;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    private final MainActivityContract.ViewModel mainActivityVM;

    @Inject
    public ScheduleScreenViewModel(RxRequests rxRequests, @DataProvider LessonsDataProvider lessonsDataProvider, MainActivityContract.ViewModel mainActivityVM) {
        super(rxRequests);
        this.lessonsDataProvider = lessonsDataProvider;
        this.mainActivityVM = mainActivityVM;
        setUpWeek();
        fetchLessons();
    }

    @Override
    public void onAttach(ScheduleScreenContract.View view) {
        super.onAttach(view);
        mainActivityVM.getEventPublishSubject().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivityVM.getEventPublishSubject().unregister(this);
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

//        for (int i = 0; i < Const.MAX_DAYS; i++) {
//            evenLessons.put(i, new SparseArray<>());
//            oddLessons.put(i, new SparseArray<>());
//            for (int j = 0; j < Const.MAX_LESSONS; j++) {
//                evenLessons.get(i).put(j, new DummyLesson());
//                oddLessons.get(i).put(j, new DummyLesson());
//            }
//        }

        for (Lesson lesson :
                lessons) {
            switch (WeekType.fromType(lesson.getDate().getWeek())) {
                case ODD: {
                    if (oddLessons.get(lesson.getDate().getDay(), null) == null) {
                        oddLessons.put(lesson.getDate().getDay(), new SparseArray<>());
                    }
                    oddLessons.get(lesson.getDate().getDay()).put(lesson.getDate().getOrdinal(), lesson);
                    break;
                }
                case EVEN: {
                    if (evenLessons.get(lesson.getDate().getDay(), null) == null) {
                        evenLessons.put(lesson.getDate().getDay(), new SparseArray<>());
                    }
                    evenLessons.get(lesson.getDate().getDay()).put(lesson.getDate().getOrdinal(), lesson);
                    break;
                }
                case ANY: {
                    if (evenLessons.get(lesson.getDate().getDay(), null) == null) {
                        evenLessons.put(lesson.getDate().getDay(), new SparseArray<>());
                    }
                    if (oddLessons.get(lesson.getDate().getDay(), null) == null) {
                        oddLessons.put(lesson.getDate().getDay(), new SparseArray<>());
                    }
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
        int maxLessonsNumber = 0;
        List<LessonDay> list = new ArrayList<>();
        for (int i = 0; i < weekLessons.size(); i++) {
            int key = weekLessons.keyAt(i);
            List<? super Lesson> lessonsForList = new ArrayList<>();
            SparseArray<? super Lesson> lessonDay = weekLessons.get(key, new SparseArray<>());
            for (int j = 0; j < Const.MAX_LESSONS; j++) {
                Lesson lesson = (Lesson) lessonDay.get(j, new DummyLesson());
                lessonsForList.add(lesson);
                if (!(lesson instanceof DummyLesson) && j > maxLessonsNumber) {
                    maxLessonsNumber = j;
                }
            }
            list.add(new LessonDay(CommonUtils.getDayName(key + 2), lessonsForList));
        }

        for (LessonDay day :
                list) {
            for (int i = day.getLessons().size() - 1; i >= 0; i--) {
                if (i > maxLessonsNumber) {
                    day.getLessons().remove(i);
                }
            }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMenuItemSelectedEvent(MenuItemSelectedEvent event) {
        switch (event.getMenuItemRes()) {
            case R.id.menu_switch: {
                switchWeek();
                break;
            }
        }
    }
}
