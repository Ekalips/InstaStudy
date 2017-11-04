package com.ekalips.instastudy.di.modules;

import com.ekalips.instastudy.data.user.source.local.LocalUserDataProvider;
import com.ekalips.instastudy.data.user.source.local.LocalUserDataProviderImpl;
import com.ekalips.instastudy.data.user.source.network.RemoteUserDataProvider;
import com.ekalips.instastudy.data.user.source.network.RemoteUserDataProviderImpl;
import com.ekalips.instastudy.di.source_qualifier.Local;
import com.ekalips.instastudy.di.source_qualifier.Remote;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 10/2/17.
 */

@Module
public abstract class UserDataModule {

    @Binds
    @Local
    @Singleton
    abstract LocalUserDataProvider bindLocalUserDataProvider(LocalUserDataProviderImpl provider);

    @Binds
    @Remote
    @Singleton
    abstract RemoteUserDataProvider bindRemoteUserDataProvider(RemoteUserDataProviderImpl provider);


}
