package com.ekalips.instastudy.main.di;

import com.ekalips.instastudy.di.scopes.ActivityScope;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.ekalips.instastudy.main.mvvm.vm.MainActivityViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 11/6/17.
 */

@Module
@ActivityScope
public abstract class MainActivityModule {

    @Binds
    @ActivityScope
    abstract MainActivityContract.ViewModel bindVM(MainActivityViewModel viewModel);

}
