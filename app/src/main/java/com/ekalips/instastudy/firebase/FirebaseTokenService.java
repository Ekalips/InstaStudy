package com.ekalips.instastudy.firebase;

import android.util.Log;

import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.wonderslab.base.rx.RxRequests;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by Ekalips on 11/14/17.
 */

public class FirebaseTokenService extends FirebaseInstanceIdService {

    private static final String TAG = FirebaseTokenService.class.getSimpleName();

    @Inject
    @DataProvider
    UserDataProvider userDataProvider;

    @Inject
    RxRequests rxRequests;

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        Log.d(TAG, "onTokenRefresh: " + FirebaseInstanceId.getInstance().getToken());
        rxRequests.subscribe(userDataProvider.updateFirebaseToken(FirebaseInstanceId.getInstance().getToken()).toObservable(), data -> Log.d(TAG, "updateFirebaseToken: success"),
                throwable -> Log.e(TAG, "onTokenRefresh: fail", throwable), null, null);

    }

}
