package com.ekalips.instastudy.data.user;

import android.support.annotation.Nullable;

import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.source.local.LocalUserDataProvider;
import com.ekalips.instastudy.data.user.source.local.UserSharedPrefsDataHelper;
import com.ekalips.instastudy.data.user.source.network.RemoteUserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.Local;
import com.ekalips.instastudy.di.source_qualifier.Remote;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Ekalips on 10/2/17.
 */

public class UserDataProviderImpl implements UserDataProvider {


    private final LocalUserDataProvider localDataProvider;
    private final RemoteUserDataProvider remoteDataProvider;

    private final List<UserDataChangeCallback> userDataChangeCallbacks = new ArrayList<>();

    @Inject
    public UserDataProviderImpl(@Local LocalUserDataProvider localDataProvider,
                                @Remote RemoteUserDataProvider remoteDataProvider) {
        this.localDataProvider = localDataProvider;
        this.remoteDataProvider = remoteDataProvider;
    }

    @Override
    public Observable<DataWrap<? extends User>> getUser() {
        return getUser(false);
    }

    @Override
    public User getUserSync() {
        return localDataProvider.getUserSync();
    }


    @Override
    public Observable<DataWrap<? extends User>> getUser(boolean fetchRemotely) {
        if (fetchRemotely) {
            return Observable.concat(localDataProvider.getUser(), getUserToken().switchMap(this::getUser)
                    .doOnNext(dataWrap -> saveUser(null, dataWrap.getData())));
        } else {
            return localDataProvider.getUser();
        }
    }


    @Override
    public Observable<DataWrap<? extends User>> getUser(String token) {
        return remoteDataProvider.getUser(token);
    }

    @Override
    public Single<DataWrap<? extends User>> login(String firebaseAuthToken, @Nullable String firebaseDeviceToken) {
        return remoteDataProvider.login(firebaseAuthToken, firebaseDeviceToken)
                .doOnSuccess(dataWrap -> {
                    if (dataWrap.getResponseCode() == 200) {
                        saveUser(dataWrap.getData().getToken(), dataWrap.getData());
                    }
                });
    }

    @Override
    public Observable<String> getUserToken() {
        return getUser(false).map(dataWrap -> dataWrap.getData().getToken());
    }

    @Override
    public void saveUser(@Nullable String token, User user) {
        localDataProvider.saveUser(token, user);
        notifyCallbacks(user);
    }

    @Override
    public void saveUserField(UserSharedPrefsDataHelper.UserFields field, Object value) {
        localDataProvider.saveUserField(field, value);
    }

    @Override
    public void clear() {
        localDataProvider.clear();
    }

    @Override
    public void saveGroups(List<? extends Group> userGroups) {
        localDataProvider.saveGroups(userGroups);
    }


    @Override
    public void addUserDataChangeCallback(UserDataChangeCallback changeCallback) {
        userDataChangeCallbacks.add(changeCallback);
    }

    @Override
    public void removeUserDataChangeCallback(UserDataChangeCallback changeCallback) {
        userDataChangeCallbacks.remove(changeCallback);
    }

    @Override
    public void clearAllCallbacks() {
        userDataChangeCallbacks.clear();
    }

    private void notifyCallbacks(User user) {
        for (UserDataChangeCallback changeCallback :
                userDataChangeCallbacks) {
            changeCallback.onUserDataChanged(user);
        }
    }
}
