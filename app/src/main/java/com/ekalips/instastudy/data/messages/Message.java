package com.ekalips.instastudy.data.messages;

import com.ekalips.instastudy.data.files.models.File;
import com.ekalips.instastudy.data.user.User;

import javax.annotation.Nullable;

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

    @Nullable
    File getFile();
}
