package com.ekalips.instastudy.data.user.source.local.model;


import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.groups.source.local.LocalGroup;
import com.ekalips.instastudy.data.user.User;

import java.util.List;

/**
 * Created by Ekalips on 10/2/17.
 */

public class LocalUserData implements User {

    private String token;
    private String name;
    private String number;
    private String userId;
    private String avatar;
    private String firebaseToken;
    private List<LocalGroup> groups;
    private boolean online;
    private int role;

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String getUserName() {
        return name;
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
    public List<? extends Group> getGroups() {
        return groups;
    }

    @Override
    public boolean isOnline() {
        return online;
    }

    @Override
    public int getRole() {
        return role;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setGroups(List<LocalGroup> groups) {
        this.groups = groups;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
