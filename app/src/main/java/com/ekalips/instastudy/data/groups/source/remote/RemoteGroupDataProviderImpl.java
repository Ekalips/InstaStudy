package com.ekalips.instastudy.data.groups.source.remote;

import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.error_handling.ErrorThrower;
import com.ekalips.instastudy.network.InstaApi;
import com.wonderslab.base.rx.RxUtils;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by Ekalips on 11/5/17.
 */

public class RemoteGroupDataProviderImpl implements RemoteGroupDataProvider {

    private final InstaApi api;
    private final ErrorThrower errorThrower;

    @Inject
    public RemoteGroupDataProviderImpl(InstaApi instaApi, ErrorThrower errorThrower) {
        this.api = instaApi;
        this.errorThrower = errorThrower;
    }


    @Override
    public Single<DataWrap<? extends Group>> joinGroup(String token, String groupName) {
        return RxUtils.wrapAsIO(Single.fromCallable(() -> {
            Response<RemoteGroup> response = api.joinToGroup(token, groupName).execute();
            if (response.isSuccessful()) {
                return new DataWrap<>(response.body(), response.code());
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }
}
