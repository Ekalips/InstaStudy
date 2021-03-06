package com.ekalips.instastudy.data.user.source.network;

import android.content.Context;
import android.support.annotation.Nullable;

import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.data.user.source.network.model.RemoteUserData;
import com.ekalips.instastudy.data.user.source.network.model.RemoteUserDataWrap;
import com.ekalips.instastudy.error_handling.ErrorThrower;
import com.ekalips.instastudy.network.InstaApi;
import com.ekalips.instastudy.network.body.LoginBody;
import com.ekalips.instastudy.network.body.UpdateFirebaseTokenBody;
import com.ekalips.instastudy.network.body.UpdateUserNameBody;
import com.ekalips.instastudy.stuff.NetworkUtils;
import com.wonderslab.base.rx.RxUtils;

import java.io.File;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by Ekalips on 10/2/17.
 */

public class RemoteUserDataProviderImpl implements RemoteUserDataProvider {

    private final InstaApi api;
    private final Context context;
    private final ErrorThrower errorThrower;

    @Inject
    public RemoteUserDataProviderImpl(InstaApi instaApi, Context context, ErrorThrower errorThrower) {
        this.api = instaApi;
        this.context = context;
        this.errorThrower = errorThrower;
    }

    @Override
    public Observable<DataWrap<? extends User>> getUser(String token) {
        return RxUtils.wrapAsIO(Observable.fromCallable((Callable<DataWrap<? extends User>>) () -> {
            Response<RemoteUserData> response = api.getMyUser(token).execute();
            if (response.isSuccessful()) {
                return new DataWrap<>(response.body(), response.code());
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }

    @Override
    public Single<DataWrap<? extends User>> login(String firebaseAuthToken, @Nullable String firebaseDeviceToken) {
        return RxUtils.wrapAsIO(Single.fromCallable((Callable<DataWrap<? extends User>>) () -> {
            Response<RemoteUserDataWrap> response = api.login(new LoginBody(firebaseAuthToken, firebaseDeviceToken)).execute();
            if (response.isSuccessful()) {
                return new DataWrap<>(response.body(), response.code());
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }

    @Override
    public Single<DataWrap<? extends User>> setUserName(String token, String name) {
        return RxUtils.wrapAsIO(Single.fromCallable((Callable<DataWrap<? extends User>>) () -> {
            Response<RemoteUserData> response = api.updateName(token, new UpdateUserNameBody(name)).execute();
            if (response.isSuccessful()) {
                return new DataWrap<>(response.body(), response.code());
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }

    @Override
    public Single<DataWrap<? extends User>> setUserImage(String token, @Nullable File image) {
        return RxUtils.wrapAsIO(Single.fromCallable(() -> {
            Response<RemoteUserData> response = api.updateAvatar(token, NetworkUtils.prepareFilePart(context, "avatar", image)).execute();
            if (response.isSuccessful()) {
                return new DataWrap<>(response.body(), response.code());
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }

    @Override
    public Single<DataWrap<Void>> updateFirebaseToken(String accessToken, String oldToken, String newToken) {
        return RxUtils.wrapAsIO(Single.fromCallable(() -> {
            Response<Void> response = api.updateDeviceToken(accessToken, new UpdateFirebaseTokenBody(oldToken, newToken)).execute();
            if (response.isSuccessful()) {
                return new DataWrap<>(response.body(), response.code());
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }

    @Override
    public Observable<DataWrap<? extends User>> getUserById(String token, String userId) {
        return RxUtils.wrapAsIO(Observable.fromCallable((Callable<DataWrap<? extends User>>) () -> {
            Response<RemoteUserData> response = api.getUser(token, userId).execute();
            if (response.isSuccessful()) {
                return new DataWrap<>(response.body(), response.code());
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }
}
