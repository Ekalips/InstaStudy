package com.ekalips.instastudy.data.user;


import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.source.local.LocalUserDataProvider;
import com.ekalips.instastudy.data.user.source.network.RemoteUserDataProvider;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Ekalips on 10/2/17.
 */

public interface UserDataProvider extends LocalUserDataProvider, RemoteUserDataProvider {

    Observable<DataWrap<? extends User>> getUser(boolean fetchRemotely);

    Single<DataWrap<? extends User>> setUserName(String name);

    Observable<String> getUserToken();

    Single<DataWrap<? extends User>> updateUserImage(File image);

    void addUserDataChangeCallback(UserDataChangeCallback changeCallback);

    void removeUserDataChangeCallback(UserDataChangeCallback changeCallback);

    void clearAllCallbacks();

    interface UserDataChangeCallback {
        void onUserDataChanged(User user);
    }

}
