package com.ekalips.instastudy.data.user.source.network.model;

import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.user.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekalips on 10/2/17.
 */

public class RemoteUserDataWrap implements User {

    @SerializedName("user")
    @Expose
    private RemoteUserData userData;

    @SerializedName("token")
    @Expose
    private String token;


    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String getUserName() {
        return userData.getUserName();
    }

    @Override
    public String getPhoneNumber() {
        return userData.getPhoneNumber();
    }

    @Override
    public String getUserId() {
        return userData.getUserId();
    }

    @Override
    public String getAvatar() {
        return userData.getAvatar();
    }

    @Override
    public String getFirebaseToken() {
        return userData.getFirebaseToken();
    }

    @Override
    public boolean isOnline() {
        return userData.isOnline();
    }

    @Override
    public int getRole() {
        return userData.getRole();
    }

    @Override
    public List<? extends Group> getGroups() {
        return userData.getGroups();
    }
}
