package com.ekalips.instastudy.data.messages;

import com.ekalips.instastudy.data.messages.sources.remote.RemoteMessageDataSource;
import com.ekalips.instastudy.data.stuff.DataWrap;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Ekalips on 11/7/17.
 */

public interface MessageDataProvider extends RemoteMessageDataSource{

    int MESSAGES_LIMIT = 100;

    Single<DataWrap<List<? extends Message>>> getMessages(String chatId, int page);

}
