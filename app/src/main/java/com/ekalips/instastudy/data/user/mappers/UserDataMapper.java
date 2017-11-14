package com.ekalips.instastudy.data.user.mappers;

import android.support.annotation.Nullable;

import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.data.user.source.local.model.BoxLocalUser;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ekalips on 11/14/17.
 */

@Singleton
public class UserDataMapper {

    @Inject
    public UserDataMapper() {
    }

    public BoxLocalUser toBoxLocal(@Nullable User user) {
        BoxLocalUser boxLocalUser = new BoxLocalUser();
        if (user != null) {
            boxLocalUser.setAvatar(user.getAvatar());
            boxLocalUser.setFirebaseToken(user.getFirebaseToken());
            boxLocalUser.setOnline(user.isOnline());
            boxLocalUser.setPhoneNumber(user.getPhoneNumber());
            boxLocalUser.setRole(user.getRole());
            boxLocalUser.setToken(user.getToken());
            boxLocalUser.setUserId(user.getUserId());
            boxLocalUser.setUserName(user.getUserName());
        }
        return boxLocalUser;
    }
}
