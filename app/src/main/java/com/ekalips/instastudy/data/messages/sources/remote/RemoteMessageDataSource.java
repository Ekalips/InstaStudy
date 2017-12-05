package com.ekalips.instastudy.data.messages.sources.remote;

import com.ekalips.instastudy.data.messages.Message;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.network.response.PaginatedListResponse;

import java.io.File;

import io.reactivex.Observable;

/**
 * Created by Ekalips on 11/6/17.
 */

public interface RemoteMessageDataSource {

    Observable<DataWrap<PaginatedListResponse<? extends Message>>> getMessages(String token, String chatId, int offset, int limit);

    Observable<DataWrap<? extends Message>> sendMessage(String token, String chatId, String message);

    Observable<? extends Message> sendChatAttachment(String token, String chatId, File file);
}
