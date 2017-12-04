package com.ekalips.instastudy.data.lessons;

import com.ekalips.instastudy.data.lessons.models.Lesson;
import com.ekalips.instastudy.data.lessons.sources.local.LocalLessonDataProvider;
import com.ekalips.instastudy.data.lessons.sources.remote.RemoteLessonDataProvider;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.di.source_qualifier.Local;
import com.ekalips.instastudy.di.source_qualifier.Remote;

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
}
