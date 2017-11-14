package com.ekalips.instastudy.stuff.lists;

import android.text.TextUtils;

import com.ekalips.instastudy.data.messages.Message;

/**
 * Created by Ekalips on 11/14/17.
 */

public class MessageEqualator implements Equalator<Message> {
    @Override
    public boolean equals(Message o1, Object o2) {
        return o2 instanceof Message && TextUtils.equals(o1.getId(), ((Message) o2).getId());
    }
}
