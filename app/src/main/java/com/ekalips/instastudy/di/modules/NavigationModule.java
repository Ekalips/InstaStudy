package com.ekalips.instastudy.di.modules;

import com.ekalips.instastudy.navigation.GlobalNavigator;
import com.ekalips.instastudy.navigation.GlobalNavigatorImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 10/9/17.
 */

@Module
public abstract class NavigationModule {

    @Binds
    @Singleton
    abstract GlobalNavigator bindGlobalNavigator(GlobalNavigatorImpl globalNavigator);

}
