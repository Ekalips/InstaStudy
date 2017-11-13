package com.ekalips.instastudy.main.mvvm.vm;

import android.databinding.ObservableField;
import android.util.Log;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.ekalips.instastudy.providers.ToastProvider;
import com.wonderslab.base.rx.RxRequests;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/6/17.
 */

public class MainActivityViewModel extends MainActivityContract.ViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    private final ObservableField<MainActivityContract.Screens> currentScreen = new ObservableField<>(MainActivityContract.Screens.NONE);

    private final UserDataProvider userDataProvider;
    private final ObservableField<User> user = new ObservableField<>(null);
    private final ToastProvider toastProvider;

    @Inject
    public MainActivityViewModel(@DataProvider UserDataProvider userDataProvider, RxRequests rxRequests, ToastProvider toastProvider) {
        super(rxRequests);
        this.userDataProvider = userDataProvider;
        this.toastProvider = toastProvider;
        getUserAndNavigateToGroup();
    }

    private void getUserAndNavigateToGroup() {
        request(userDataProvider.getUser(false), data -> onGetUserSuccess(data.getData()), this::onGetUserError);
    }

    private void onGetUserSuccess(User user) {
        this.user.set(user);
        changeScreen(MainActivityContract.Screens.GROUP_CHAT, user.getGroups().get(0).getId());
    }

    private void onGetUserError(Throwable throwable) {
        Log.e(TAG, "onGetUserError: ", throwable);
        toastProvider.showToast(R.string.error_get_user);
    }


    @Override
    public ObservableField<User> getUser() {
        return user;
    }

    private void changeScreen(MainActivityContract.Screens screen, Object payload) {
        if (screen.getPlace() != null) {
            navigateTo(screen.getPlace(), payload);
        }
        currentScreen.set(screen);
    }

    @Override
    public ObservableField<MainActivityContract.Screens> getCurrentScreen() {
        return currentScreen;
    }

    @Override
    public void navigateTo(MainActivityContract.Screens screens) {
        changeScreen(screens, null);
    }
}
