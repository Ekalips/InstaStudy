package com.ekalips.instastudy.data.messages;

import com.ekalips.instastudy.data.other_user.OtherUser;

/**
 * Created by Ekalips on 11/6/17.
 */

public interface Message {

    String getId();

    String getMessage();

    long getDate();

    int getType();

    OtherUser getAuthor();

    boolean isMine();

}
