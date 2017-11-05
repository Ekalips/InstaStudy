package com.ekalips.instastudy.registration.di.fragments;

import com.ekalips.instastudy.di.scopes.FragmentScope;
import com.ekalips.instastudy.registration.contract.SetImageScreenContract;
import com.ekalips.instastudy.registration.mvvm.vm.SetImageScreenViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 11/5/17.
 */

@Module
@FragmentScope
public abstract class SetImageScreenModule {

    @FragmentScope
    @Binds
    abstract SetImageScreenContract.ViewModel bindVm(SetImageScreenViewModel setImageScreenViewModel);


}
