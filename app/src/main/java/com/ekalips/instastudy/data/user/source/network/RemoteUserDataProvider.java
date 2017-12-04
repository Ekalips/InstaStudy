package com.ekalips.instastudy.data.user.source.network;


import android.support.annotation.Nullable;

import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.User;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Ekalips on 10/2/17.
 */

public interface RemoteUserDataProvider {

    Observable<DataWrap<? extends User>> getUser(String token);

    Single<DataWrap<? extends User>> login(String firebaseAuthToken, @Nullable String firebaseDeviceToken);

    Single<DataWrap<? extends User>> setUserName(String token, String name);

    Single<DataWrap<? extends User>> setUserImage(String token, File image);

    Single<DataWrap<Void>> updateFirebaseToken(String accessToken, String oldToken, String newToken);

    Observable<DataWrap<? extends User>> getUserById(String token, String userId);
}
