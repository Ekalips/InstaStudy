package com.ekalips.instastudy.data.groups;

import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.groups.source.local.LocalGroupDataProvider;
import com.ekalips.instastudy.data.groups.source.remote.RemoteGroupDataProvider;
import com.ekalips.instastudy.data.stuff.DataWrap;

import io.reactivex.Single;

/**
 * Created by Ekalips on 11/5/17.
 */

public interface GroupDataProvider extends LocalGroupDataProvider, RemoteGroupDataProvider {
    Single<DataWrap<? extends Group>> joinGroup(String groupName);
}
