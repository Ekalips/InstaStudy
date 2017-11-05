package com.ekalips.instastudy.registration.di.fragments;

import com.ekalips.instastudy.di.scopes.FragmentScope;
import com.ekalips.instastudy.registration.contract.FillDataScreenContract;
import com.ekalips.instastudy.registration.mvvm.vm.FillDataScreenViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 11/5/17.
 */

@Module
@FragmentScope
public abstract class FillDataScreenModule {

    @Binds
    @FragmentScope
    abstract FillDataScreenContract.ViewModel bindVM(FillDataScreenViewModel viewModel);

}
