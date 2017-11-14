package com.ekalips.instastudy.data.user;

import android.support.annotation.Nullable;

import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.source.local.LocalUserDataProvider;
import com.ekalips.instastudy.data.user.source.local.UserSharedPrefsDataHelper;
import com.ekalips.instastudy.data.user.source.network.RemoteUserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.Local;
import com.ekalips.instastudy.di.source_qualifier.Remote;

import java.io.File;
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
    public Single<DataWrap<Void>> updateFirebaseToken(String accessToken, String oldToken, String newToken) {
        return remoteDataProvider.updateFirebaseToken(accessToken, oldToken, newToken);
    }

    @Override
    public Single<DataWrap<? extends User>> login(String firebaseAuthToken, @Nullable String firebaseDeviceToken) {
        return remoteDataProvider.login(firebaseAuthToken, firebaseDeviceToken)
                .doOnSuccess(dataWrap -> saveUser(dataWrap.getData().getToken(), dataWrap.getData()))
                .doOnSuccess(data -> saveUserField(UserSharedPrefsDataHelper.UserFields.FIREBASE_TOKEN, firebaseAuthToken));
    }

    @Override
    public Single<DataWrap<? extends User>> setUserName(String token, String name) {
        return remoteDataProvider.setUserName(token, name)
                .doOnSuccess(data -> saveUserField(UserSharedPrefsDataHelper.UserFields.NAME, name));
    }

    @Override
    public Single<DataWrap<? extends User>> setUserName(String name) {
        return Single.fromObservable(getUserToken()).flatMap(token -> setUserName(token, name));
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
    public Single<DataWrap<? extends User>> updateUserImage(File image) {
        return Single.fromObservable(getUserToken()).flatMap(token -> setUserImage(token, image))
                .doOnSuccess(data -> saveUserField(UserSharedPrefsDataHelper.UserFields.AVATAR, data.getData().getAvatar()));
    }

    @Override
    public Single<DataWrap<? extends User>> setUserImage(String token, File image) {
        return remoteDataProvider.setUserImage(token, image);
    }

    @Override
    public Single<DataWrap<Void>> updateFirebaseToken(String newToken) {
        return Single.fromObservable(getUser(false).flatMap(data -> updateFirebaseToken(data.getData().getToken(), data.getData().getFirebaseToken(), newToken).toObservable()))
                .doOnSuccess(data -> saveUserField(UserSharedPrefsDataHelper.UserFields.FIREBASE_TOKEN, newToken));
    }

    @Override
    public void clear() {
        localDataProvider.clear();
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

    @Override
    public void markCacheDirty() {
        localDataProvider.markCacheDirty();
    }
}
