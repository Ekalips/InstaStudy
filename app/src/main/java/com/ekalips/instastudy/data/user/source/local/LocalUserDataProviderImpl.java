package com.ekalips.instastudy.data.user.source.local;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.groups.source.local.LocalGroup;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.data.user.source.local.model.LocalUserData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.reactivex.Observable;

/**
 * Created by Ekalips on 10/2/17.
 */

public class LocalUserDataProviderImpl implements LocalUserDataProvider {

    private final SharedPreferences sharedPreferences;
    private final UserSharedPrefsDataHelper sharedPrefsDataHelper;
    private final Box<LocalGroup> groupBox;

    private LocalUserData lastFetchedUser;
    private boolean shouldReFetch = true;

    @Inject
    public LocalUserDataProviderImpl(SharedPreferences sharedPreferences, UserSharedPrefsDataHelper userSharedPrefsDataHelper, Box<LocalGroup> groupBox) {
        this.sharedPreferences = sharedPreferences;
        this.sharedPrefsDataHelper = userSharedPrefsDataHelper;
        this.groupBox = groupBox;
    }

    @Override
    public Observable<DataWrap<? extends User>> getUser() {
        return Observable.fromCallable(() -> new DataWrap<>(getUserSync(), 0));
    }

    @Override
    public User getUserSync() {
        if (lastFetchedUser == null || shouldReFetch) {
            shouldReFetch = false;
            lastFetchedUser = sharedPrefsDataHelper.extractUserFromSharedPrefs(sharedPreferences);
            lastFetchedUser.setGroups(groupBox.getAll());
        }
        return lastFetchedUser;
    }

    @Override
    public void saveUser(@Nullable String token, User user) {
        shouldReFetch = true;
        sharedPrefsDataHelper.saveUser(sharedPreferences, token, user);
        saveGroups(user.getGroups());
    }

    @Override
    public void saveUserField(UserSharedPrefsDataHelper.UserFields field, Object value) {
        shouldReFetch = true;
        sharedPrefsDataHelper.saveField(sharedPreferences, field, value);
    }

    @Override
    public void clear() {
        shouldReFetch = true;
        sharedPreferences.edit().clear().apply();
        groupBox.removeAll();
    }

    @Override
    public void saveGroups(List<? extends Group> userGroups) {
        groupBox.removeAll();

        List<LocalGroup> groups = new ArrayList<>(userGroups.size());
        for (Group g :
                userGroups) {
            groups.add(new LocalGroup(g));
        }
        groupBox.put(groups);
    }
}
