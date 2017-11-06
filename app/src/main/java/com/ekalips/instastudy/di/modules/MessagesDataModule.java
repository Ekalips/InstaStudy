package com.ekalips.instastudy.di.modules;

import com.ekalips.instastudy.data.messages.sources.remote.RemoteMessageDataSource;
import com.ekalips.instastudy.data.messages.sources.remote.RemoteMessageDataSourceImpl;
import com.ekalips.instastudy.di.source_qualifier.Remote;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 11/7/17.
 */

@Module
public abstract class MessagesDataModule {

    @Binds
    @Singleton
    @Remote
    abstract RemoteMessageDataSource remoteMessageDataSource(RemoteMessageDataSourceImpl remoteMessageDataSource);

}
