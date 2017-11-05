package com.ekalips.instastudy.data.user;

import com.ekalips.instastudy.data.groups.Group;

import java.util.List;

/**
 * Created by Ekalips on 10/2/17.
 */

public interface User {

    String getToken();

    String getUserName();

    String getPhoneNumber();

    String getUserId();

    String getAvatar();

    String getFirebaseToken();

    List<? extends Group> getGroups();

    boolean isOnline();

    int getRole();

}
