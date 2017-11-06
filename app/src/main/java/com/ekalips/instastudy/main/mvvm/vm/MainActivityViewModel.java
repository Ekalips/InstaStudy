package com.ekalips.instastudy.main.mvvm.vm;

import android.databinding.ObservableField;
import android.util.Log;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.ekalips.instastudy.navigation.NavigateToEnum;
import com.ekalips.instastudy.providers.MessagingProvider;
import com.wonderslab.base.rx.RxRequests;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/6/17.
 */

public class MainActivityViewModel extends MainActivityContract.ViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    private final UserDataProvider userDataProvider;
    private final ObservableField<User> user = new ObservableField<>(null);
    private final MessagingProvider messagingProvider;

    @Inject
    public MainActivityViewModel(@DataProvider UserDataProvider userDataProvider, RxRequests rxRequests, MessagingProvider messagingProvider) {
        super(rxRequests);
        this.userDataProvider = userDataProvider;
        this.messagingProvider = messagingProvider;
        getUserAndNavigateToGroup();
    }

    private void getUserAndNavigateToGroup() {
        request(userDataProvider.getUser(false), data -> onGetUserSuccess(data.getData()), this::onGetUserError);
    }

    private void onGetUserSuccess(User user) {
        this.user.set(user);
        navigateTo(NavigateToEnum.GROUP_CHAT, user.getGroups().get(0).getId());
    }

    private void onGetUserError(Throwable throwable) {
        Log.e(TAG, "onGetUserError: ", throwable);
        messagingProvider.showToast(R.string.error_get_user);
    }


    @Override
    public ObservableField<User> getUser() {
        return user;
    }


}
