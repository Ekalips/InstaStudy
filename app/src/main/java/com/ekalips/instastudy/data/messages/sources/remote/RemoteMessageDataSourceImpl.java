package com.ekalips.instastudy.data.messages.sources.remote;

import android.content.Context;

import com.ekalips.instastudy.data.messages.Message;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.error_handling.ErrorThrower;
import com.ekalips.instastudy.network.InstaApi;
import com.ekalips.instastudy.network.body.SendMessageBody;
import com.ekalips.instastudy.network.response.PaginatedListResponse;
import com.ekalips.instastudy.stuff.NetworkUtils;
import com.wonderslab.base.rx.RxUtils;

import java.io.File;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Ekalips on 11/7/17.
 */

public class RemoteMessageDataSourceImpl implements RemoteMessageDataSource {

    private final InstaApi api;
    private final ErrorThrower errorThrower;
    private final Context context;

    @Inject
    public RemoteMessageDataSourceImpl(InstaApi instaApi, ErrorThrower errorThrower, Context context) {
        this.api = instaApi;
        this.errorThrower = errorThrower;
        this.context = context;
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

    @Override
    public Observable<? extends Message> sendChatAttachment(String token, String chatId, File file) {
        return RxUtils.wrapAsIO(Observable.fromCallable((Callable<Message>) () -> {
            Response<RemoteMessage> response = api.uploadAttachment(token, chatId, NetworkUtils.prepareFilePart(context, "file", file)).execute();
            if (response.isSuccessful()) {
                return response.body();
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }
}
