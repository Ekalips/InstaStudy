package com.ekalips.instastudy.data.groups.source.local;

import android.util.Log;

import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.error_handling.ErrorThrower;
import com.wonderslab.base.rx.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.reactive.DataObserver;
import io.reactivex.Single;

/**
 * Created by Ekalips on 11/5/17.
 */

public class LocalGroupDataProviderImpl implements LocalGroupDataProvider {

    private static final String TAG = LocalGroupDataProviderImpl.class.getSimpleName();

    private final BoxStore boxStore;
    private final Box<LocalGroup> groupBox;
    private final ErrorThrower errorThrower;

    @Inject
    public LocalGroupDataProviderImpl(BoxStore boxStore, Box<LocalGroup> groupBox, ErrorThrower errorThrower) {
        this.boxStore = boxStore;
        this.groupBox = groupBox;
        this.errorThrower = errorThrower;

        boxStore.subscribe(LocalGroup.class).observer(groupDataObserver);
    }

    private final DataObserver<Class<LocalGroup>> groupDataObserver = data -> Log.d(TAG, "Box<LocalGroup> DataObserver onData: " + data);

    @Override
    public void saveGroup(Group data) {
        if (data != null) {
            LocalGroup newGr = new LocalGroup(data);
            List<LocalGroup> foundGr = groupBox.find(LocalGroup_.id, data.getId());
            if (foundGr != null && foundGr.size() > 0) {
                newGr.setBoxId(foundGr.get(0).getBoxId());
            }
            groupBox.put(newGr);
        }
    }

    @Override
    public Single<DataWrap<? extends Group>> getGroup(String groupId) {
        return RxUtils.wrapAsIO(Single.fromCallable(() -> {
            List<LocalGroup> groups = groupBox.find(LocalGroup_.id, groupId);
            if (groups != null && groups.size() > 0) {
                return new DataWrap<>(groups.get(0), 0);
            } else return new DataWrap<>(null, 400);
        }));
    }
}
