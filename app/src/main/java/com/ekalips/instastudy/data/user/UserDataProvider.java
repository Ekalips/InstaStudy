package com.ekalips.instastudy.data.user;


import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.source.local.LocalUserDataProvider;
import com.ekalips.instastudy.data.user.source.network.RemoteUserDataProvider;

import io.reactivex.Observable;

/**
 * Created by Ekalips on 10/2/17.
 */

public interface UserDataProvider extends LocalUserDataProvider, RemoteUserDataProvider {

    Observable<DataWrap<? extends User>> getUser(boolean fetchRemotely);

    Observable<String> getUserToken();

    void addUserDataChangeCallback(UserDataChangeCallback changeCallback);

    void removeUserDataChangeCallback(UserDataChangeCallback changeCallback);

    void clearAllCallbacks();

    interface UserDataChangeCallback {
        void onUserDataChanged(User user);
    }

}
