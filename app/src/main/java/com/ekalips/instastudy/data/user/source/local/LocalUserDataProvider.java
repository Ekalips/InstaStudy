package com.ekalips.instastudy.data.user.source.local;


import android.support.annotation.Nullable;

import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.User;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Ekalips on 10/2/17.
 */

public interface LocalUserDataProvider {

    Observable<DataWrap<? extends User>> getUser();

    User getUserSync();

    void saveUser(@Nullable String token, User user);

    void saveUserField(UserSharedPrefsDataHelper.UserFields field, Object value);

    void clear();

    void saveGroups(List<? extends Group> userGroups);
}
