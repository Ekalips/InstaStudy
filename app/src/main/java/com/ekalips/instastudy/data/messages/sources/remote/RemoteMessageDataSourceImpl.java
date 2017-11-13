package com.ekalips.instastudy.data.messages.sources.remote;

import com.ekalips.instastudy.data.messages.Message;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.error_handling.ErrorThrower;
import com.ekalips.instastudy.network.InstaApi;
import com.ekalips.instastudy.network.body.SendMessageBody;
import com.ekalips.instastudy.network.response.PaginatedListResponse;
import com.wonderslab.base.rx.RxUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
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
    public Observable<DataWrap<PaginatedListResponse<? extends Message>>> getMessages(String token, String groupId, int offset, int limit) {
        return RxUtils.wrapAsIO(Observable.fromCallable(() -> {
            Response<PaginatedListResponse<RemoteMessage>> response = api.getMessages(token, groupId, offset, limit).execute();
            if (response.isSuccessful()) {
                return new DataWrap<>(response.body(), response.code());
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }

    @Override
    public Observable<DataWrap<? extends Message>> sendMessage(String token, String chatId, String message) {
        return RxUtils.wrapAsIO(Observable.fromCallable(() -> {
            Response<RemoteMessage> response = api.sendMessage(token, chatId, new SendMessageBody(message)).execute();
            if (response.isSuccessful()) {
                return new DataWrap<>(response.body(), response.code());
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }
}
