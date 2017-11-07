package com.ekalips.instastudy.data.user.source.local.model;

import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.user.User;

import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Ekalips on 11/7/17.
 */

@Entity
public class BoxLocalUser implements User {

    @Id
    private long boxId;

    private String token;
    private String userName;
    private String phoneNumber;
    private String userId;
    private String avatar;
    private String firebaseToken;
    private boolean online;
    private int role;


    public long getBoxId() {
        return boxId;
    }

    public void setBoxId(long boxId) {
        this.boxId = boxId;
    }

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
        return phoneNumber;
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
        return null;
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
