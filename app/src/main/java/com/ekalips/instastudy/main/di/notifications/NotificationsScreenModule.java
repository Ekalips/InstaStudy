package com.ekalips.instastudy.main.di.notifications;

import com.ekalips.instastudy.di.scopes.FragmentScope;
import com.ekalips.instastudy.main.contract.NotificationsScreenContract;
import com.ekalips.instastudy.main.mvvm.vm.notifications.NotificationsScreenViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ekalips on 12/4/17.
 */

@Module
public abstract class NotificationsScreenModule {


    @Binds
    @FragmentScope
    abstract NotificationsScreenContract.ViewModel viewModel(NotificationsScreenViewModel viewModel);

}
