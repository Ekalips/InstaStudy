package com.ekalips.instastudy.data.messages;

import com.ekalips.instastudy.data.user.User;

/**
 * Created by Ekalips on 11/6/17.
 */

public interface Message {

    String getId();

    String getMessage();

    long getDate();

    int getType();

    User getAuthor();

    boolean isMine();

}
