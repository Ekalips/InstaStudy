package com.ekalips.instastudy.registration.mvvm.vm;

import android.databinding.ObservableBoolean;
import android.util.Log;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.groups.source.GroupDataProvider;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.navigation.NavigateToEnum;
import com.ekalips.instastudy.providers.MessagingProvider;
import com.ekalips.instastudy.registration.contract.RegistrationActivityContract;
import com.ekalips.instastudy.registration.mvvm.model.FillDataObservable;
import com.ekalips.instastudy.registration.rules.CredentialsValidator;
import com.wonderslab.base.rx.RxRequests;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/5/17.
 */

public class RegistrationActivityViewModel extends RegistrationActivityContract.ViewModel {

    private static final String TAG = RegistrationActivityViewModel.class.getSimpleName();

    private final FillDataObservable data = new FillDataObservable();
    private final CredentialsValidator credentialsValidator;

    private final UserDataProvider userDataProvider;
    private final GroupDataProvider groupDataProvider;
    private final RxRequests rxRequests;
    private final MessagingProvider messagingProvider;

    private boolean isNameSet = false;
    private final ObservableBoolean inProgress = new ObservableBoolean(false);
    private boolean isGroupSet = false;

    @Inject
    public RegistrationActivityViewModel(CredentialsValidator credentialsValidator, @DataProvider UserDataProvider userDataProvider,
                                         @DataProvider GroupDataProvider groupDataProvider, RxRequests rxRequests, MessagingProvider messagingProvider) {
        this.credentialsValidator = credentialsValidator;
        this.groupDataProvider = groupDataProvider;
        this.userDataProvider = userDataProvider;
        this.rxRequests = rxRequests;
        this.messagingProvider = messagingProvider;
        navigateTo(NavigateToEnum.FILL_DATA, null);
    }

    @Override
    public CredentialsValidator getValidator() {
        return credentialsValidator;
    }

    @Override
    public FillDataObservable getRegistrationData() {
        return data;
    }

    @Override
    public void register() {
        if (credentialsValidator.isNameValid(data.getName()) && credentialsValidator.isGroupValid(data.getGroup())) {
            inProgress.set(true);
            sendUserName(data.getName());
            sendGroup(data.getGroup());
        }
    }

    private void sendUserName(String name) {
        addDisposable(rxRequests.subscribe(userDataProvider.setUserName(name).toObservable(), dataWrap -> onSaveNameSuccess(), this::onSaveNameError,
                () -> inProgress.set(isGroupSet)));
    }

    private void onSaveNameSuccess() {
        isNameSet = true;
        checkAndNavigateToImageSetting();
    }

    private void onSaveNameError(Throwable throwable) {
        Log.e(TAG, "onSaveNameError: ", throwable);
        messagingProvider.showToast(R.string.error_save_name);
    }

    private void sendGroup(String group) {
        addDisposable(rxRequests.subscribe(groupDataProvider.joinGroup(group).toObservable(), dataWrap -> onSendGroupSuccess(), this::onSendGroupError,
                () -> inProgress.set(isNameSet)));
    }

    private void onSendGroupSuccess() {
        isGroupSet = true;
        checkAndNavigateToImageSetting();
    }

    private void onSendGroupError(Throwable throwable) {
        Log.e(TAG, "onSendGroupError: ", throwable);
        messagingProvider.showToast(R.string.error_save_group);
    }

    private void checkAndNavigateToImageSetting() {
        if (isNameSet && isGroupSet) {
            navigateTo(NavigateToEnum.SET_IMAGE, null);
        }
    }

    @Override
    public void onImageSet(File file) {

    }

    @Override
    public void onImageSkip() {
        navigateTo(NavigateToEnum.MAIN, null);
    }
}
