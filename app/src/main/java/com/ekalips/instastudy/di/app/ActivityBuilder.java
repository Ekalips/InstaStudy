package com.ekalips.instastudy.di.app;


import com.ekalips.instastudy.di.scopes.ActivityScope;
import com.ekalips.instastudy.login.di.LoginScreenModule;
import com.ekalips.instastudy.login.mvvm.view.LoginActivity;
import com.ekalips.instastudy.main.di.MainActivityModule;
import com.ekalips.instastudy.main.di.MainActivityScreensProvider;
import com.ekalips.instastudy.main.mvvm.view.MainActivity;
import com.ekalips.instastudy.registration.di.RegistrationScreenModule;
import com.ekalips.instastudy.registration.mvvm.view.RegistrationActivity;
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

    @ActivityScope
    @ContributesAndroidInjector(modules = {LoginScreenModule.class})
    abstract LoginActivity loginActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {RegistrationScreenModule.class})
    abstract RegistrationActivity registrationActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {MainActivityModule.class, MainActivityScreensProvider.class})
    abstract MainActivity mainActivity();


}
