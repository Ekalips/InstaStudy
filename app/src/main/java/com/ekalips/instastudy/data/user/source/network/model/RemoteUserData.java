package com.ekalips.instastudy.data.user.source.network.model;

import com.ekalips.instastudy.data.user.User;

/**
 * Created by Ekalips on 10/2/17.
 */

public class RemoteUserData implements User {

    private String token;
    private String userName;
    private String number;
    private String userId;
    private String avatar;
    private String group;
    private String firebaseToken;
    private boolean online;
    private int role;

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPhoneNumber() {
        return number;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public String getFirebaseToken() {
        return firebaseToken;
    }

    @Override
    public boolean isOnline() {
        return online;
    }

    @Override
    public int getRole() {
        return role;
    }
}
