package com.ekalips.instastudy.data.notifications.models;

import com.ekalips.instastudy.data.user.User;

/**
 * Created by ekalips on 12/4/17.
 */

public interface Notification {

    String getId();

    String getTitle();

    String getBody();

    User getAuthor();

    long getDate();

}
