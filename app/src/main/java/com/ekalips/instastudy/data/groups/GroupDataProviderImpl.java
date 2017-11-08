package com.ekalips.instastudy.data.groups;

import com.ekalips.instastudy.data.groups.source.local.LocalGroupDataProvider;
import com.ekalips.instastudy.data.groups.source.remote.RemoteGroupDataProvider;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.di.source_qualifier.Local;
import com.ekalips.instastudy.di.source_qualifier.Remote;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Ekalips on 11/5/17.
 */

public class GroupDataProviderImpl implements GroupDataProvider {

    private final LocalGroupDataProvider localGroupDataProvider;
    private final RemoteGroupDataProvider remoteGroupDataProvider;
    private final UserDataProvider userDataProvider;

    @Inject
    public GroupDataProviderImpl(@Local LocalGroupDataProvider localGroupDataProvider, @Remote RemoteGroupDataProvider remoteGroupDataProvider,
                                 @DataProvider UserDataProvider userDataProvider) {
        this.localGroupDataProvider = localGroupDataProvider;
        this.remoteGroupDataProvider = remoteGroupDataProvider;
        this.userDataProvider = userDataProvider;
    }

    @Override
    public Single<DataWrap<? extends Group>> joinGroup(String token, String groupName) {
        return remoteGroupDataProvider.joinGroup(token, groupName)
                .doOnSuccess(data -> localGroupDataProvider.saveGroup(data.getData()))
                .doOnSuccess(data -> userDataProvider.markCacheDirty());
    }

    @Override
    public Single<DataWrap<? extends Group>> joinGroup(String groupName) {
        return Single.fromObservable(userDataProvider.getUserToken()).flatMap(token -> joinGroup(token, groupName))
                .doOnSuccess(data -> saveGroup(data.getData()));
    }

    @Override
    public Single<DataWrap<? extends Group>> getGroup(String groupId) {
        return localGroupDataProvider.getGroup(groupId);
    }

    @Override
    public Single<DataWrap<? extends Group>> getGroup(String token, String groupId) {
        return remoteGroupDataProvider.getGroup(token, groupId);
    }

    @Override
    public Observable<DataWrap<? extends Group>> getGroup(String groupId, boolean fetchRemotely) {
        if (!fetchRemotely) {
            return getGroup(groupId).toObservable();
        } else {
            return Observable.concat(getGroup(groupId).toObservable(),
                    userDataProvider.getUserToken().switchMap(token -> getGroup(token, groupId).toObservable())
                            .doOnNext(data -> saveGroup(data.getData())));
        }
    }

    @Override
    public Observable<DataWrap<? extends Group>> getMainGroup(boolean fetchRemotely) {
        return userDataProvider.getUser(false).switchMap(data -> getGroup(data.getData().getGroups().get(0).getId(), fetchRemotely));
    }

    @Override
    public void saveGroup(Group data) {
        localGroupDataProvider.saveGroup(data);
    }
}
