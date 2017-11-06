package com.ekalips.instastudy.data.messages.sources.remote;

import com.ekalips.instastudy.data.messages.Message;
import com.ekalips.instastudy.data.stuff.DataWrap;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Ekalips on 11/6/17.
 */

public interface RemoteMessageDataSource {

    Single<DataWrap<List<? extends Message>>> getMessages(String token, String chatId, int offset, int limit);

}
