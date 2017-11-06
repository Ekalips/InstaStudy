package com.ekalips.instastudy.data.groups.source.local;

import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.stuff.DataWrap;

import io.reactivex.Single;

/**
 * Created by Ekalips on 11/5/17.
 */

public interface LocalGroupDataProvider {

    void saveGroup(Group data);

    Single<DataWrap<? extends Group>> getGroup(String groupId);
}
