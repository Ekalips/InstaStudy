package com.ekalips.instastudy.data.groups.source.remote;

import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.stuff.DataWrap;

import io.reactivex.Single;

/**
 * Created by Ekalips on 11/5/17.
 */

public interface RemoteGroupDataProvider {

    Single<DataWrap<? extends Group>> joinGroup(String token, String groupName);

    Single<DataWrap<? extends Group>> getGroup(String token, String groupId);

}
