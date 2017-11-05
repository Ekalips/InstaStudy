package com.ekalips.instastudy.registration.di;

import com.ekalips.instastudy.di.scopes.FragmentScope;
import com.ekalips.instastudy.registration.di.fragments.FillDataScreenModule;
import com.ekalips.instastudy.registration.di.fragments.SetImageScreenModule;
import com.ekalips.instastudy.registration.mvvm.view.FillDataFragment;
import com.ekalips.instastudy.registration.mvvm.view.SetImageFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Ekalips on 11/5/17.
 */

@Module
@FragmentScope
public abstract class RegistrationScreensProvider {

    @ContributesAndroidInjector(modules = {FillDataScreenModule.class})
    @FragmentScope
    abstract FillDataFragment fillDataFragment();


    @ContributesAndroidInjector(modules = {SetImageScreenModule.class})
    @FragmentScope
    abstract SetImageFragment setImageFragment();
}

