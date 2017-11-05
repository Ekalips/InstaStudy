package com.ekalips.instastudy.login.mvvm.vm;

import android.databinding.ObservableBoolean;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.login.LoginScreenContract;
import com.ekalips.instastudy.navigation.NavigateToEnum;
import com.ekalips.instastudy.providers.MessagingProvider;
import com.ekalips.instastudy.providers.firebase_login.FirebaseLoginProvider;
import com.google.firebase.auth.FirebaseUser;
import com.wonderslab.base.rx.RxRequests;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/5/17.
 */

public class LoginScreenViewModel extends LoginScreenContract.ViewModel {

    private static final String TAG = LoginScreenViewModel.class.getSimpleName();

    private final FirebaseLoginProvider firebaseLoginProvider;
    private final RxRequests rxRequests;
    private final UserDataProvider userDataProvider;
    private final MessagingProvider messagingProvider;

    private final ObservableBoolean inProgress = new ObservableBoolean(false);

    @Inject
    public LoginScreenViewModel(FirebaseLoginProvider loginProvider, @DataProvider UserDataProvider userDataProvider, RxRequests rxRequests, MessagingProvider messagingProvider) {
        this.firebaseLoginProvider = loginProvider;
        this.userDataProvider = userDataProvider;
        this.rxRequests = rxRequests;
        this.messagingProvider = messagingProvider;
        this.firebaseLoginProvider.setLoginCallbacks(loginCallbacks);
    }


    @Override
    public void login() {
        firebaseLoginProvider.login();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        firebaseLoginProvider.cleanup();
    }

    private final FirebaseLoginProvider.LoginCallbacks loginCallbacks = new FirebaseLoginProvider.LoginCallbacks() {
        @Override
        public void onUserLoggedIn(FirebaseUser firebaseUser) {
            Log.d(TAG, "onUserLoggedIn: " + firebaseUser.getDisplayName());
            onFirebaseUserLoggedIn(firebaseUser);
        }

        @Override
        public void onUserLogout() {
            Log.d(TAG, "onUserLogout: ");
        }
    };

    private void onFirebaseUserLoggedIn(FirebaseUser firebaseUser) {
        firebaseUser.getIdToken(true).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                onGetFirebaseUserIdTokenSuccess(task.getResult().getToken());
            } else {
                onGetFirebaseUserIdTokenError(task.getException());
            }
        });
    }

    private void onGetFirebaseUserIdTokenSuccess(String token) {
        Log.d(TAG, "onGetFirebaseUserIdTokenSuccess: " + token);
        inProgress.set(true);
        addDisposable(rxRequests.subscribe(userDataProvider.login(token, null).toObservable()
                , this::handleLoginSuccess, this::handleLoginError, () -> navigateToRegistrationActivity()));
    }

    private void onGetFirebaseUserIdTokenError(@Nullable Exception exception) {
        Log.e(TAG, "onGetFirebaseUserIdTokenError: ", exception);
        messagingProvider.showToast(R.string.error_login);
    }

    private void handleLoginSuccess(DataWrap<? extends User> dataWrap) {
        if (dataWrap.getResponseCode() == 200) {
            navigateToMainActivity();
        } else {
            navigateToRegistrationActivity();
        }
    }

    private void handleLoginError(Throwable throwable) {
        Log.e(TAG, "handleLoginError: ", throwable);
        messagingProvider.showToast(R.string.error_login);
    }

    private void navigateToRegistrationActivity() {
        navigateTo(NavigateToEnum.REGISTER, null);
    }

    private void navigateToMainActivity() {
        navigateTo(NavigateToEnum.MAIN, null);
    }
}
