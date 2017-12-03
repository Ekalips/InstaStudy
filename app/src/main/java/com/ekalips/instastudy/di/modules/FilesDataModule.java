package com.ekalips.instastudy.di.modules;

import com.ekalips.instastudy.data.files.source.remote.RemoteFilesDataSource;
import com.ekalips.instastudy.data.files.source.remote.RemoteFilesDataSourceImpl;
import com.ekalips.instastudy.di.source_qualifier.Remote;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by djqrj on 12/3/2017.
 */

@Module
public abstract class FilesDataModule {

    @Binds
    @Singleton
    @Remote
    abstract RemoteFilesDataSource remoteFilesDataSource(RemoteFilesDataSourceImpl remoteFilesDataSource);

}
