package com.ekalips.instastudy.profile.mvvm.vm;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.groups.GroupDataProvider;
import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.profile.ProfileContract;
import com.ekalips.instastudy.providers.ToastProvider;
import com.ekalips.instastudy.stuff.StringUtils;
import com.wonderslab.base.rx.RxRequests;

import javax.inject.Inject;

/**
 * Created by ekalips on 12/4/17.
 */

public class ProfileViewModel extends ProfileContract.ViewModel {

    private static final String TAG = ProfileViewModel.class.getSimpleName();

    private final UserDataProvider userDataProvider;
    private final ToastProvider toastProvider;
    private final GroupDataProvider groupDataProvider;

    private String userId;

    private final ObservableField<User> user = new ObservableField<>();
    private final ObservableBoolean isMe = new ObservableBoolean(false);
    private final ObservableBoolean loading = new ObservableBoolean(false);

    @Inject
    public ProfileViewModel(RxRequests rxRequests, @DataProvider UserDataProvider userDataProvider, @DataProvider GroupDataProvider groupDataProvider, ToastProvider toastProvider) {
        super(rxRequests);
        this.userDataProvider = userDataProvider;
        this.groupDataProvider = groupDataProvider;
        this.toastProvider = toastProvider;
    }

    @Override
    public void init(String userId) {
        this.userId = userId;
        fetchUserData();
    }

    private void fetchUserData() {
        loading.set(true);
        request(userDataProvider.getUserById(userId), data -> user.set(data.getData()), this::onGetUserError, () -> loading.set(false));
        request(userDataProvider.getUser(false).map(data -> data.getData().getUserId()), id -> isMe.set(TextUtils.equals(id, userId)));
    }

    private void onGetUserError(Throwable throwable) {
        Log.e(TAG, "onGetUserError: ", throwable);
        toastProvider.showToast(R.string.error_fetching_user);
    }

    @Override
    public ObservableField<User> getUser() {
        return user;
    }

    @Override
    public ObservableBoolean getIsMe() {
        return isMe;
    }

    @Override
    public ObservableBoolean getLoading() {
        return loading;
    }

    @Override
    public void showEnterCodeDialog() {
        if (view != null) {
            view.showEnterCodeDialog();
        }
    }

    @Override
    public void sendRiseCode(String code) {
        if (StringUtils.isEmpty(code)) {
            toastProvider.showToast(R.string.error_fields);
        } else {
            loading.set(true);
            request(groupDataProvider.upraise(code).toObservable(), (data) -> fetchUserData(), this::onSendCodeError, () -> loading.set(false));
        }
    }

    private void onSendCodeError(Throwable throwable) {
        Log.e(TAG, "onSendCodeError: ", throwable);
        toastProvider.showToast(R.string.error_uprising);
    }
}
