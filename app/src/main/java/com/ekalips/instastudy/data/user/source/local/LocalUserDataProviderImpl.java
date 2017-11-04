package com.ekalips.instastudy.data.user.source.local;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.User;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Ekalips on 10/2/17.
 */

public class LocalUserDataProviderImpl implements LocalUserDataProvider {

    private final SharedPreferences sharedPreferences;
    private final UserSharedPrefsDataHelper sharedPrefsDataHelper;

    private User lastFetchedUser;
    private boolean shouldReFetch = true;

    @Inject
    public LocalUserDataProviderImpl(SharedPreferences sharedPreferences, UserSharedPrefsDataHelper userSharedPrefsDataHelper) {
        this.sharedPreferences = sharedPreferences;
        this.sharedPrefsDataHelper = userSharedPrefsDataHelper;
    }

    @Override
    public Observable<DataWrap<? extends User>> getUser() {
        return Observable.fromCallable(() -> new DataWrap<>(getUserSync(), 0));
    }

    @Override
    public User getUserSync() {
        if (lastFetchedUser == null || shouldReFetch) {
            shouldReFetch = false;
            lastFetchedUser = sharedPrefsDataHelper.extractUserFromSharedPrefs(sharedPreferences);
        }
        return lastFetchedUser;
    }

    @Override
    public void saveUser(@Nullable String token, User user) {
        shouldReFetch = true;
        sharedPrefsDataHelper.saveUser(sharedPreferences, token, user);
    }

    @Override
    public void saveUserField(UserSharedPrefsDataHelper.UserFields field, Object value) {
        shouldReFetch = true;
        sharedPrefsDataHelper.saveField(sharedPreferences, field, value);
    }

    @Override
    public void clear() {
        shouldReFetch = true;
        sharedPreferences.edit().clear().apply();
    }
}
