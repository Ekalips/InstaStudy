package com.ekalips.instastudy.main.di.schedule;

import com.ekalips.instastudy.di.scopes.FragmentScope;
import com.ekalips.instastudy.main.contract.ScheduleScreenContract;
import com.ekalips.instastudy.main.mvvm.vm.schedule.ScheduleScreenViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 11/7/17.
 */

@Module
public abstract class ScheduleModule {

    @FragmentScope
    @Binds
    abstract ScheduleScreenContract.ViewModel bindVM(ScheduleScreenViewModel viewModel);

}
