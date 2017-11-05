package com.ekalips.instastudy.data.user.source.local;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.data.user.source.local.model.LocalUserData;
import com.ekalips.instastudy.stuff.Role;

import javax.inject.Inject;

/**
 * Created by Ekalips on 10/2/17.
 */

public class UserSharedPrefsDataHelper {

    public enum UserFields {
        ID, PHONE, TOKEN, NAME, ONLINE, AVATAR, NUMBER, FIREBASE_TOKEN, ROLE
    }

    @Inject
    public UserSharedPrefsDataHelper() {
    }

    public LocalUserData extractUserFromSharedPrefs(SharedPreferences sharedPreferences) {
        LocalUserData data = new LocalUserData();

        data.setUserId(sharedPreferences.getString(UserFields.ID.toString(), ""));
        data.setToken(sharedPreferences.getString(UserFields.TOKEN.toString(), ""));
        data.setOnline(sharedPreferences.getBoolean(UserFields.ONLINE.toString(), false));
        data.setAvatar(sharedPreferences.getString(UserFields.AVATAR.toString(), ""));
        data.setNumber(sharedPreferences.getString(UserFields.NUMBER.toString(), ""));
        data.setFirebaseToken(sharedPreferences.getString(UserFields.FIREBASE_TOKEN.toString(), ""));
        data.setRole(sharedPreferences.getInt(UserFields.ROLE.toString(), Role.USER.getRole()));


        return data;
    }


    public void saveUser(SharedPreferences sharedPreferences, @Nullable String token, User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit()
                .putString(UserFields.ID.toString(), user.getUserId())
                .putString(UserFields.PHONE.toString(), user.getPhoneNumber())
                .putString(UserFields.NAME.toString(), user.getUserName())
                .putBoolean(UserFields.ONLINE.toString(), user.isOnline())
                .putString(UserFields.AVATAR.toString(), user.getAvatar());
        if (token != null) {
            editor.putString(UserFields.TOKEN.toString(), token);
        }
        editor.apply();
    }

    public void saveField(SharedPreferences sharedPreferences, UserFields field, String value) {
        sharedPreferences.edit()
                .putString(field.toString(), value)
                .apply();
    }

    public void saveField(SharedPreferences sharedPreferences, UserFields field, boolean value) {
        sharedPreferences.edit()
                .putBoolean(field.toString(), value)
                .apply();
    }

    public void saveField(SharedPreferences sharedPreferences, UserFields field, int value) {
        sharedPreferences.edit()
                .putInt(field.toString(), value)
                .apply();
    }

    public void saveField(SharedPreferences preferences, UserFields field, Object value) {
        if (value instanceof String) {
            saveField(preferences, field, (String) value);
        } else if (value instanceof Integer) {
            saveField(preferences, field, (int) value);
        } else if (value instanceof Boolean) {
            saveField(preferences, field, (boolean) value);
        } else {
            throw new IllegalArgumentException("Wrong field type");
        }
    }
}
