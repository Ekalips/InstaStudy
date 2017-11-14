package com.ekalips.instastudy.firebase.firebase_handler.events;

import com.ekalips.instastudy.data.messages.Message;

/**
 * Created by Ekalips on 11/14/17.
 */

public class NewMessageEvent {
    private final Message message;

    public NewMessageEvent(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
}
