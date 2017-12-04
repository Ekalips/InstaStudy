package com.ekalips.instastudy.profile.di;

import com.ekalips.instastudy.di.scopes.FragmentScope;
import com.ekalips.instastudy.profile.ProfileContract;
import com.ekalips.instastudy.profile.mvvm.vm.ProfileViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ekalips on 12/4/17.
 */

@Module
public abstract class ProfileModule {

    @Binds
    @FragmentScope
    abstract ProfileContract.ViewModel viewModel(ProfileViewModel viewModel);

}
