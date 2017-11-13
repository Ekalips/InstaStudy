package com.ekalips.instastudy.data.messages;

import com.ekalips.instastudy.data.messages.sources.remote.RemoteMessageDataSource;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.network.response.PaginatedListResponse;

import io.reactivex.Observable;

/**
 * Created by Ekalips on 11/7/17.
 */

public interface MessageDataProvider extends RemoteMessageDataSource{

    int MESSAGES_LIMIT = 100;

    Observable<DataWrap<PaginatedListResponse<? extends Message>>> getMainGroupMessages(int offset);

    Observable<DataWrap<PaginatedListResponse<? extends Message>>> getGroupMessages(String groupId, int offset);

    Observable<DataWrap<? extends Message>> sendMessage(String groupId, String message);
}
