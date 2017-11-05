package com.ekalips.instastudy.data.groups.source.local;

import android.util.Log;

import com.ekalips.instastudy.data.groups.Group;

import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.reactive.DataObserver;

/**
 * Created by Ekalips on 11/5/17.
 */

public class LocalGroupDataProviderImpl implements LocalGroupDataProvider {

    private static final String TAG = LocalGroupDataProviderImpl.class.getSimpleName();

    private final BoxStore boxStore;
    private final Box<LocalGroup> groupBox;

    @Inject
    public LocalGroupDataProviderImpl(BoxStore boxStore, Box<LocalGroup> groupBox) {
        this.boxStore = boxStore;
        this.groupBox = groupBox;

        boxStore.subscribe(LocalGroup.class).observer(groupDataObserver);
    }

    private final DataObserver<Class<LocalGroup>> groupDataObserver = data -> Log.d(TAG, "Box<LocalGroup> DataObserver onData: " + data);

    @Override
    public void addToUserGroups(Group data) {
        if (data != null) {
            LocalGroup newGr = new LocalGroup(data);
            List<LocalGroup> foundGr = groupBox.find(LocalGroup_.id, data.getId());
            if (foundGr != null && foundGr.size() > 0) {
                newGr.setBoxId(foundGr.get(0).getBoxId());
            }
            groupBox.put(newGr);
        }
    }
}
