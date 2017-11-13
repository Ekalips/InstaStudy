package com.ekalips.instastudy.data.messages;

import com.ekalips.instastudy.data.messages.sources.remote.RemoteMessageDataSource;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.di.source_qualifier.Remote;
import com.ekalips.instastudy.network.response.PaginatedListResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Ekalips on 11/7/17.
 */

public class MessagesDataProviderImpl implements MessageDataProvider {

    private final RemoteMessageDataSource remoteMessageDataSource;
    private final UserDataProvider userDataProvider;

    @Inject
    public MessagesDataProviderImpl(@Remote RemoteMessageDataSource remoteMessageDataSource, @DataProvider UserDataProvider userDataProvider) {
        this.userDataProvider = userDataProvider;
        this.remoteMessageDataSource = remoteMessageDataSource;
    }

    @Override
    public Observable<DataWrap<PaginatedListResponse<? extends Message>>> getMessages(String token, String chatId, int offset, int limit) {
        return remoteMessageDataSource.getMessages(token, chatId, offset, limit);
    }

    @Override
    public Observable<DataWrap<PaginatedListResponse<? extends Message>>> getMainGroupMessages(int offset) {
        return userDataProvider.getUser(false)
                .switchMap(user -> getGroupMessages(user.getData().getGroups().get(0).getId(), offset));
    }

    @Override
    public Observable<DataWrap<PaginatedListResponse<? extends Message>>> getGroupMessages(String groupId, int offset) {
        return userDataProvider.getUserToken().switchMap(token -> getMessages(token, groupId, offset, MESSAGES_LIMIT));
    }

    @Override
    public Observable<DataWrap<? extends Message>> sendMessage(String token, String chatId, String message) {
        return remoteMessageDataSource.sendMessage(token, chatId, message);
    }

    @Override
    public Observable<DataWrap<? extends Message>> sendMessage(String groupId, String message) {
        return userDataProvider.getUserToken().switchMap(token -> sendMessage(token, groupId, message));
    }
}
