package com.ekalips.instastudy.data.user;

/**
 * Created by Ekalips on 10/2/17.
 */

public interface User {

    String getToken();

    String getUserName();

    String getPhoneNumber();

    String getUserId();

    String getAvatar();

    String getGroup();

    String getFirebaseToken();

    boolean isOnline();

    int getRole();

}
