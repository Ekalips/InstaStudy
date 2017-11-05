package com.ekalips.instastudy.di.modules;

import com.ekalips.instastudy.data.groups.source.local.LocalGroupDataProvider;
import com.ekalips.instastudy.data.groups.source.local.LocalGroupDataProviderImpl;
import com.ekalips.instastudy.data.groups.source.remote.RemoteGroupDataProvider;
import com.ekalips.instastudy.data.groups.source.remote.RemoteGroupDataProviderImpl;
import com.ekalips.instastudy.di.source_qualifier.Local;
import com.ekalips.instastudy.di.source_qualifier.Remote;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 11/5/17.
 */

@Module
public abstract class GroupsDataModule {

    @Binds
    @Singleton
    @Local
    abstract LocalGroupDataProvider localGroupDataProvider(LocalGroupDataProviderImpl localGroupDataProvider);

    @Binds
    @Singleton
    @Remote
    abstract RemoteGroupDataProvider remoteGroupDataProvider(RemoteGroupDataProviderImpl remoteGroupDataProvider);


}
