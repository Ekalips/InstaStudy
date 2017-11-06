package com.ekalips.instastudy.data.messages.sources.remote;

import com.ekalips.instastudy.data.messages.Message;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.error_handling.ErrorThrower;
import com.ekalips.instastudy.network.InstaApi;
import com.ekalips.instastudy.network.response.PaginatedListResponse;
import com.wonderslab.base.rx.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by Ekalips on 11/7/17.
 */

public class RemoteMessageDataSourceImpl implements RemoteMessageDataSource {

    private final InstaApi api;
    private final ErrorThrower errorThrower;

    @Inject
    public RemoteMessageDataSourceImpl(InstaApi instaApi, ErrorThrower errorThrower) {
        this.api = instaApi;
        this.errorThrower = errorThrower;
    }

    @Override
    public Single<DataWrap<List<? extends Message>>> getMessages(String token, String groupId, int offset, int limit) {
        return RxUtils.wrapAsIO(Single.fromCallable(() -> {
            Response<PaginatedListResponse<RemoteMessage>> response = api.getMessages(token, groupId, offset, limit).execute();
            if (response.isSuccessful()) {
                return new DataWrap<>(response.body().getData(), response.code());
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }
}
