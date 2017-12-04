package com.ekalips.instastudy.data.notifications;

import com.ekalips.instastudy.data.notifications.models.Notification;
import com.ekalips.instastudy.data.notifications.sources.remote.RemoteNotificationsSource;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.di.source_qualifier.Remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by ekalips on 12/4/17.
 */

public class NotificationsDataProviderImpl implements NotificationsDataProvider {

    private final UserDataProvider userDataProvider;
    private final RemoteNotificationsSource remoteNotificationsSource;

    @Inject
    public NotificationsDataProviderImpl(@DataProvider UserDataProvider userDataProvider, @Remote RemoteNotificationsSource remoteNotificationsSource) {
        this.userDataProvider = userDataProvider;
        this.remoteNotificationsSource = remoteNotificationsSource;
    }

    @Override
    public Observable<List<? extends Notification>> getNotifications(String token, String groupId) {
        return remoteNotificationsSource.getNotifications(token, groupId);
    }

    @Override
    public Observable<? extends Notification> postNotification(String token, String groupId, String title, String body) {
        return remoteNotificationsSource.postNotification(token, groupId, title, body);
    }

    @Override
    public Observable<List<? extends Notification>> getNotifications(String groupId) {
        return userDataProvider.getUserToken().switchMap(token -> getNotifications(token, groupId));
    }

    @Override
    public Observable<List<? extends Notification>> getMainGroupNotifications() {
        return userDataProvider.getUser(false).switchMap(data -> getNotifications(data.getData().getGroups().get(0).getId()));
    }

    @Override
    public Observable<? extends Notification> postNotification(String groupId, String title, String body) {
        return userDataProvider.getUserToken().switchMap(token -> postNotification(token, groupId, title, body));
    }

    @Override
    public Observable<? extends Notification> postMainGroupNotification(String title, String body) {
        return userDataProvider.getUser(false).switchMap(data -> postNotification(data.getData().getGroups().get(0).getId(), title, body));
    }
}
