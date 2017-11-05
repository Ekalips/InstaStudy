package com.ekalips.instastudy.di.modules;

import com.ekalips.instastudy.data.groups.GroupDataProvider;
import com.ekalips.instastudy.data.groups.GroupDataProviderImpl;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.data.user.UserDataProviderImpl;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 10/2/17.
 */

@Module(includes = {UserDataModule.class, GroupsDataModule.class})
abstract public class DataModules {

    @DataProvider
    @Binds
    @Singleton
    abstract UserDataProvider bindUserDataProvider(UserDataProviderImpl userDataProvider);

    @DataProvider
    @Binds
    @Singleton
    abstract GroupDataProvider bindGroupsProvider(GroupDataProviderImpl groupDataProvider);

}
