package com.ekalips.instastudy.main.mvvm.vm.notifications;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.notifications.NotificationsDataProvider;
import com.ekalips.instastudy.data.notifications.models.Notification;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.main.contract.NotificationsScreenContract;
import com.ekalips.instastudy.providers.TimeUtilsProvider;
import com.ekalips.instastudy.providers.ToastProvider;
import com.ekalips.instastudy.stuff.Role;
import com.ekalips.instastudy.stuff.StringUtils;
import com.wonderslab.base.rx.RxRequests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by ekalips on 12/4/17.
 */

public class NotificationsScreenViewModel extends NotificationsScreenContract.ViewModel {

    private static final String TAG = NotificationsScreenViewModel.class.getSimpleName();

    private final UserDataProvider userDataProvider;
    private final NotificationsDataProvider notificationsDataProvider;
    private final ToastProvider toastProvider;
    private final TimeUtilsProvider timeUtilsProvider;

    private final ObservableBoolean canPublish = new ObservableBoolean(false);
    private final ObservableField<List<Object>> data = new ObservableField<>(new ArrayList<>());
    private final ObservableBoolean loading = new ObservableBoolean(false);
    private String groupId;

    @Inject
    public NotificationsScreenViewModel(RxRequests rxRequests, @DataProvider UserDataProvider userDataProvider, @DataProvider NotificationsDataProvider notificationsDataProvider,
                                        ToastProvider toastProvider, TimeUtilsProvider timeUtilsProvider) {
        super(rxRequests);
        this.userDataProvider = userDataProvider;
        this.notificationsDataProvider = notificationsDataProvider;
        this.toastProvider = toastProvider;
        this.timeUtilsProvider = timeUtilsProvider;

        request(userDataProvider.getUser(false).map(user -> Role.getRoleFromInt(user.getData().getRole()).canPublishNotifications()), canPublish::set, this::onErrorDefiningRole);
    }

    private void onErrorDefiningRole(Throwable throwable) {
        Log.e(TAG, "onErrorDefiningRole: ", throwable);
        toastProvider.showToast(R.string.error_setup_role);
    }


    @Override
    public void init(String groupId) {
        this.groupId = groupId;
        fetchNotifications();
    }

    @Override
    public void fetchNotifications() {
        loading.set(true);
        if (StringUtils.isEmpty(groupId)) {
            request(notificationsDataProvider.getMainGroupNotifications(), this::onNotificationsFetched, this::onNotificationFetchError, () -> loading.set(false));
        } else {
            request(notificationsDataProvider.getNotifications(groupId), this::onNotificationsFetched, this::onNotificationFetchError, () -> loading.set(false));
        }
    }

    private void onNotificationsFetched(List<? extends Notification> notificationList) {
        Collections.sort(notificationList, (o1, o2) -> Long.compare(o2.getDate(), o1.getDate()));

        data.get().clear();
        Map<String, List<? super Notification>> newData = new HashMap<>();
        for (Notification n :
                notificationList) {
            String relativeTime = timeUtilsProvider.getRelativeTime(n.getDate());
            if (!newData.containsKey(relativeTime)) {
                newData.put(relativeTime, new ArrayList<>());
            }
            newData.get(relativeTime).add(n);
        }
        for (Map.Entry<String, List<? super Notification>> entry :
                newData.entrySet()) {
            data.get().add(entry.getKey());
            data.get().addAll(entry.getValue());
        }
        data.notifyChange();
    }

    private void onNotificationFetchError(Throwable throwable) {
        Log.e(TAG, "onNotificationFetchError: ", throwable);
        toastProvider.showToast(R.string.error_fetching_notifications);
    }

    @Override
    public ObservableBoolean getCanPublish() {
        return canPublish;
    }

    @Override
    public ObservableField<List<Object>> getData() {
        return data;
    }

    @Override
    public ObservableBoolean getLoading() {
        return loading;
    }
}
