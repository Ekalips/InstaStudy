package com.ekalips.instastudy.main.di;

import com.ekalips.instastudy.di.scopes.ActivityScope;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.ekalips.instastudy.main.mvvm.view.MainActivity;
import com.ekalips.instastudy.main.mvvm.vm.MainActivityViewModel;
import com.ekalips.instastudy.main.navigation.MainLocalNavigator;
import com.ekalips.instastudy.main.navigation.MainLocalNavigatorImpl;

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

    @Binds
    @ActivityScope
    abstract MainLocalNavigator mainLocalNavigator(MainLocalNavigatorImpl mainLocalNavigator);

    @Binds
    @ActivityScope
    abstract MainActivityContract.FlexibleMainToolbar bindToolbarChanger(MainActivity mainActivity);

}
