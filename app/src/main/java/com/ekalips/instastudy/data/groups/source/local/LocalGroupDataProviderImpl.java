package com.ekalips.instastudy.data.groups.source.local;

import com.ekalips.instastudy.data.groups.Group;

import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;

/**
 * Created by Ekalips on 11/5/17.
 */

public class LocalGroupDataProviderImpl implements LocalGroupDataProvider {

    private final Box<LocalGroup> groupBox;

    @Inject
    public LocalGroupDataProviderImpl(Box<LocalGroup> groupBox) {
        this.groupBox = groupBox;
    }

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
