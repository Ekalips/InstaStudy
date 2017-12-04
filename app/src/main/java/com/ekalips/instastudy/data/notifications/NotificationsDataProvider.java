package com.ekalips.instastudy.data.notifications;

import com.ekalips.instastudy.data.notifications.models.Notification;
import com.ekalips.instastudy.data.notifications.sources.remote.RemoteNotificationsSource;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ekalips on 12/4/17.
 */

public interface NotificationsDataProvider extends RemoteNotificationsSource {

    Observable<List<? extends Notification>> getNotifications(String groupId);

    Observable<List<? extends Notification>> getMainGroupNotifications();

    Observable<? extends Notification> postNotification(String groupId, String title, String body);

    Observable<? extends Notification> postMainGroupNotification(String title, String body);

}
