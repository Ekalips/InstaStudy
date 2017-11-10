package com.ekalips.instastudy.data.lessons.sources.remote;

import com.ekalips.instastudy.data.lessons.models.Lesson;
import com.ekalips.instastudy.data.lessons.sources.remote.models.RemoteLesson;
import com.ekalips.instastudy.error_handling.ErrorThrower;
import com.ekalips.instastudy.network.InstaApi;
import com.wonderslab.base.rx.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by Ekalips on 11/8/17.
 */

public class RemoteLessonDataProviderImpl implements RemoteLessonDataProvider {

    private final InstaApi api;
    private final ErrorThrower errorThrower;

    @Inject
    public RemoteLessonDataProviderImpl(InstaApi instaApi, ErrorThrower errorThrower) {
        this.api = instaApi;
        this.errorThrower = errorThrower;
    }

    @Override
    public Single<List<? extends Lesson>> getLessons(String token, String groupId) {
        return RxUtils.wrapAsIO(Single.fromCallable(() -> {
            Response<List<RemoteLesson>> response = api.getLessons(token, groupId).execute();
            if (response.isSuccessful()) {
                return response.body();
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }
}
