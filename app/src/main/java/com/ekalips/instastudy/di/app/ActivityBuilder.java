package com.ekalips.instastudy.di.app;


import com.ekalips.instastudy.di.scopes.ActivityScope;
import com.ekalips.instastudy.splash.di.SplashScreenModule;
import com.ekalips.instastudy.splash.mvvm.view.SplashScreenActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Ekalips on 10/2/17.
 */

@Module
public abstract class ActivityBuilder {


    @ActivityScope
    @ContributesAndroidInjector(modules = {SplashScreenModule.class})
    abstract SplashScreenActivity splashScreenActivity();


}
