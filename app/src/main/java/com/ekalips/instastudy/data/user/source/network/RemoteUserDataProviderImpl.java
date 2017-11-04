package com.ekalips.instastudy.data.user.source.network;

import android.content.Context;

import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.network.InstaApi;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Ekalips on 10/2/17.
 */

public class RemoteUserDataProviderImpl implements RemoteUserDataProvider {

    private final InstaApi instaApi;
    private final Context context;

    private String sessionToken;

    @Inject
    public RemoteUserDataProviderImpl(InstaApi instaApi, Context context) {
        this.instaApi = instaApi;
        this.context = context;
    }

    @Override
    public Observable<DataWrap<? extends User>> getUser(String token) {
        return Observable.empty();
    }
}
