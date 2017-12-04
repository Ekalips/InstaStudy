package com.ekalips.instastudy.data.notifications.sources.remote;

import com.ekalips.instastudy.data.notifications.models.Notification;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ekalips on 12/4/17.
 */

public interface RemoteNotificationsSource {

    Observable<List<? extends Notification>> getNotifications(String token, String groupId);

    io.reactivex.Observable<? extends Notification> postNotification(String token, String groupId, String title, String body);

}
