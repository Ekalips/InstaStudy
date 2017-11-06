package com.ekalips.instastudy.data.groups;

import com.ekalips.instastudy.data.groups.source.local.LocalGroupDataProvider;
import com.ekalips.instastudy.data.groups.source.remote.RemoteGroupDataProvider;
import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.di.source_qualifier.Local;
import com.ekalips.instastudy.di.source_qualifier.Remote;

import javax.inject.Inject;

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
                .doOnSuccess(data -> localGroupDataProvider.addToUserGroups(data.getData()))
                .doOnSuccess(data -> userDataProvider.markCacheDirty());
    }

    @Override
    public Single<DataWrap<? extends Group>> joinGroup(String groupName) {
        return Single.fromObservable(userDataProvider.getUserToken()).flatMap(token -> joinGroup(token, groupName))
                .doOnSuccess(data -> addToUserGroups(data.getData()));
    }

    @Override
    public void addToUserGroups(Group data) {
        localGroupDataProvider.addToUserGroups(data);
    }
}
