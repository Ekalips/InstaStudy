package com.ekalips.instastudy.data.messages;

import com.ekalips.instastudy.data.messages.sources.remote.RemoteMessageDataSource;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.di.source_qualifier.Remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

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
    public Single<DataWrap<List<? extends Message>>> getMessages(String token, String chatId, int offset, int limit) {
        return null;
    }

    @Override
    public Single<DataWrap<List<? extends Message>>> getMessages(String chatId, int page) {
        return Single.fromObservable(userDataProvider.getUserToken()).flatMap(token -> getMessages(token, chatId, page * MESSAGES_LIMIT, MESSAGES_LIMIT));
    }
}
