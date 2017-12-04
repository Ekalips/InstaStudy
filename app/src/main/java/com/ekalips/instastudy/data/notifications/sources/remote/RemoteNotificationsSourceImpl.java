package com.ekalips.instastudy.data.notifications.sources.remote;

import com.ekalips.instastudy.data.notifications.models.Notification;
import com.ekalips.instastudy.error_handling.ErrorThrower;
import com.ekalips.instastudy.network.InstaApi;
import com.ekalips.instastudy.network.body.PostNotificationBody;
import com.wonderslab.base.rx.RxUtils;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by ekalips on 12/4/17.
 */

public class RemoteNotificationsSourceImpl implements RemoteNotificationsSource {

    private final InstaApi api;
    private final ErrorThrower errorThrower;

    @Inject
    public RemoteNotificationsSourceImpl(InstaApi api, ErrorThrower errorThrower) {
        this.api = api;
        this.errorThrower = errorThrower;
    }

    @Override
    public Observable<List<? extends Notification>> getNotifications(String token, String groupId) {
        return RxUtils.wrapAsIO(Observable.fromCallable((Callable<List<? extends Notification>>) () -> {
            Response<List<RemoteNotification>> response = api.getNotifications(token, groupId).execute();
            if (response.isSuccessful()) {
                return response.body();
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }

    @Override
    public Observable<? extends Notification> postNotification(String token, String groupId, String title, String body) {
        return RxUtils.wrapAsIO(Observable.fromCallable((Callable<Notification>) () -> {
            Response<RemoteNotification> response = api.postNotification(token, groupId, new PostNotificationBody(title, body)).execute();
            if (response.isSuccessful()) {
                return response.body();
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }
}
