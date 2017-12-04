package com.ekalips.instastudy.main.mvvm.vm.notifications;

import android.databinding.ObservableBoolean;
import android.util.Log;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.main.contract.NotificationsScreenContract;
import com.ekalips.instastudy.providers.ToastProvider;
import com.ekalips.instastudy.stuff.Role;
import com.wonderslab.base.rx.RxRequests;

import javax.inject.Inject;

/**
 * Created by ekalips on 12/4/17.
 */

public class NotificationsScreenViewModel extends NotificationsScreenContract.ViewModel {

    private static final String TAG = NotificationsScreenViewModel.class.getSimpleName();

    private final UserDataProvider userDataProvider;
    private final ToastProvider toastProvider;

    private final ObservableBoolean canPublish = new ObservableBoolean(false);

    @Inject
    public NotificationsScreenViewModel(RxRequests rxRequests, @DataProvider UserDataProvider userDataProvider, ToastProvider toastProvider) {
        super(rxRequests);
        this.userDataProvider = userDataProvider;
        this.toastProvider = toastProvider;

        request(userDataProvider.getUser(false).map(user -> Role.getRoleFromInt(user.getData().getRole()).canPublishNotifications()), canPublish::set, this::onErrorDefiningRole);
    }

    private void onErrorDefiningRole(Throwable throwable) {
        Log.e(TAG, "onErrorDefiningRole: ", throwable);
        toastProvider.showToast(R.string.error_setup_role);
    }


    @Override
    public ObservableBoolean getCanPublish() {
        return canPublish;
    }
}
