package com.ekalips.instastudy.splash.di;

import com.ekalips.instastudy.di.scopes.ActivityScope;
import com.ekalips.instastudy.splash.SplashScreeContract;
import com.ekalips.instastudy.splash.mvvm.vm.SplashScreenViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 11/4/17.
 */

@Module
public abstract class SplashScreenModule {

    @Binds
    @ActivityScope
    public abstract SplashScreeContract.ViewModel bindVM(SplashScreenViewModel viewModel);

}
