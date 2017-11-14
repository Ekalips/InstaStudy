package com.ekalips.instastudy.data.lessons;

import com.ekalips.instastudy.data.lessons.models.Lesson;
import com.ekalips.instastudy.data.lessons.sources.local.LocalLessonDataProvider;
import com.ekalips.instastudy.data.lessons.sources.remote.RemoteLessonDataProvider;
import com.ekalips.instastudy.data.lessons.test.TestDate;
import com.ekalips.instastudy.data.lessons.test.TestLeason;
import com.ekalips.instastudy.data.lessons.test.TestSubject;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.di.source_qualifier.Local;
import com.ekalips.instastudy.di.source_qualifier.Remote;
import com.ekalips.instastudy.stuff.learning.WeekType;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Ekalips on 11/8/17.
 */

public class LessonDataProviderImpl implements LessonDataProvider {

    private final LocalLessonDataProvider localLessonDataProvider;
    private final RemoteLessonDataProvider remoteLessonDataProvider;
    private final UserDataProvider userDataProvider;

    @Inject
    public LessonDataProviderImpl(@Local LocalLessonDataProvider localLessonDataProvider, @Remote RemoteLessonDataProvider remoteLessonDataProvider,
                                  @DataProvider UserDataProvider userDataProvider) {
        this.localLessonDataProvider = localLessonDataProvider;
        this.remoteLessonDataProvider = remoteLessonDataProvider;
        this.userDataProvider = userDataProvider;
    }

    @Override
    public Single<List<? extends Lesson>> getLessons() {
        return localLessonDataProvider.getLessons();
    }

    @Override
    public void saveLessons(List<? extends Lesson> data) {
        localLessonDataProvider.saveLessons(data);
    }

    @Override
    public Single<List<? extends Lesson>> getLessons(String token, String groupId) {
        return remoteLessonDataProvider.getLessons(token, groupId);
    }

    @Override
    public Observable<List<? extends Lesson>> getLessons(boolean fetchRemotely) {
        if (fetchRemotely) {
            return Observable.concat(getLessons().toObservable(), userDataProvider.getUser()
                    .flatMap(user -> getLessons(user.getData().getToken(), user.getData().getGroups().get(0).getId()).toObservable())
                    .doOnNext(this::saveLessons));
        } else {
            return getLessons().toObservable();
        }
    }

    private List<? extends Lesson> getTestLessons() {
        List<Lesson> lessons = new ArrayList<>();

        Lesson monday1 = new TestLeason(new TestSubject("Право"), "loc", new TestDate(0, 3, WeekType.ODD.getType()));
        Lesson monday2 = new TestLeason(new TestSubject("Анализ"), "loc", new TestDate(0, 3, WeekType.EVEN.getType()));
        Lesson monday3 = new TestLeason(new TestSubject("Анализ"), "loc", new TestDate(0, 4, WeekType.EVEN.getType()));
//        Lesson monday4 = new TestLeason(new TestSubject("Monday4"), "loc", new TestDate(0, 3, 0));
//        Lesson monday5 = new TestLeason(new TestSubject("Monday5"), "loc", new TestDate(0, 4, 0));

        Lesson tues1 = new TestLeason(new TestSubject("Анализ"), "loc", new TestDate(1, 0, WeekType.ANY.getType()));
        Lesson tues2 = new TestLeason(new TestSubject("Проектный практикум"), "loc", new TestDate(1, 1, WeekType.ANY.getType()));
        Lesson tues3 = new TestLeason(new TestSubject("БД"), "loc", new TestDate(1, 2, WeekType.ANY.getType()));
//        Lesson tues4 = new TestLeason(new TestSubject("Tuesday4"), "loc", new TestDate(1, 3, 0));
//        Lesson tues5 = new TestLeason(new TestSubject("Tuesday5"), "loc", new TestDate(1, 4, 0));

        Lesson wedn1 = new TestLeason(new TestSubject("Графика"), "loc", new TestDate(2, 0, WeekType.ANY.getType()));
        Lesson wedn2 = new TestLeason(new TestSubject("Тестирование"), "loc", new TestDate(2, 1, WeekType.ANY.getType()));
        Lesson wedn3 = new TestLeason(new TestSubject("Графика"), "loc", new TestDate(2, 2, WeekType.EVEN.getType()));
//        Lesson wedn4 = new TestLeason(new TestSubject("Wednesday4"), "loc", new TestDate(2, 3, 0));
//        Lesson wedn5 = new TestLeason(new TestSubject("Wednesday5"), "loc", new TestDate(2, 4, 0));

        Lesson thur1 = new TestLeason(new TestSubject("Проектный практикум"), "loc", new TestDate(3, 0, WeekType.ODD.getType()));
        Lesson thur2 = new TestLeason(new TestSubject("ИИ"), "loc", new TestDate(3, 1, WeekType.ANY.getType()));
        Lesson thur3 = new TestLeason(new TestSubject("Анализ"), "loc", new TestDate(3, 2, WeekType.ODD.getType()));
        Lesson thur4 = new TestLeason(new TestSubject("Философия"), "loc", new TestDate(3, 2, WeekType.EVEN.getType()));
//        Lesson thur5 = new TestLeason(new TestSubject("Thursday5"), "loc", new TestDate(3, 4, 0));

//        Lesson fri1 = new TestLeason(new TestSubject("Friday1"), "loc", new TestDate(4, 0, 0));
        Lesson fri2 = new TestLeason(new TestSubject("Право"), "loc", new TestDate(4, 1, WeekType.ODD.getType()));
        Lesson fri3 = new TestLeason(new TestSubject("Философия"), "loc", new TestDate(4, 1, WeekType.EVEN.getType()));
        Lesson fri4 = new TestLeason(new TestSubject("ИИ"), "loc", new TestDate(4, 2, WeekType.ANY.getType()));
        Lesson fri5 = new TestLeason(new TestSubject("БД"), "loc", new TestDate(4, 3, WeekType.EVEN.getType()));


        lessons.add(monday1);
        lessons.add(monday2);
        lessons.add(monday3);
//        lessons.add(monday4);
//        lessons.add(monday5);

        lessons.add(tues1);
        lessons.add(tues2);
        lessons.add(tues3);
//        lessons.add(tues4);
//        lessons.add(tues5);

        lessons.add(wedn1);
        lessons.add(wedn2);
        lessons.add(wedn3);
//        lessons.add(wedn4);
//        lessons.add(wedn5);

        lessons.add(thur1);
        lessons.add(thur2);
        lessons.add(thur3);
        lessons.add(thur4);
//        lessons.add(thur5);

//        lessons.add(fri1);
        lessons.add(fri2);
        lessons.add(fri3);
        lessons.add(fri4);
        lessons.add(fri5);


        return lessons;
    }
}
