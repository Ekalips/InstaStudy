package com.ekalips.instastudy.di.modules;

import com.ekalips.instastudy.data.notifications.sources.remote.RemoteNotificationsSource;
import com.ekalips.instastudy.data.notifications.sources.remote.RemoteNotificationsSourceImpl;
import com.ekalips.instastudy.di.source_qualifier.Remote;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ekalips on 12/4/17.
 */

@Module
public abstract class NotificationsDataModule {

    @Binds
    @Singleton
    @Remote
    abstract RemoteNotificationsSource remoteNotificationsSource(RemoteNotificationsSourceImpl remoteNotificationsSource);

}
