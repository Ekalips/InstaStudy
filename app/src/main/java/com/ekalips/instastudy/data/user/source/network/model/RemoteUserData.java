package com.ekalips.instastudy.data.user.source.network.model;

import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.groups.source.remote.RemoteGroup;
import com.ekalips.instastudy.data.user.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekalips on 10/2/17.
 */

public class RemoteUserData implements User {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("name")
    @Expose
    private String userName;
    @SerializedName("phone")
    @Expose
    private String number;
    @SerializedName(value = "_id", alternate = "userId")
    @Expose
    private String userId;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("firebaseToken")
    @Expose
    private String firebaseToken;
    @SerializedName("groups")
    @Expose
    private List<RemoteGroup> groupList;
    @SerializedName("online")
    @Expose
    private boolean online;
    @SerializedName("role")
    @Expose
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

    @Override
    public List<? extends Group> getGroups() {
        return groupList;
    }
}
