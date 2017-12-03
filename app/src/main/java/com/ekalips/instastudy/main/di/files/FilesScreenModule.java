package com.ekalips.instastudy.main.di.files;

import com.ekalips.instastudy.di.scopes.FragmentScope;
import com.ekalips.instastudy.main.contract.FilesScreenContract;
import com.ekalips.instastudy.main.mvvm.vm.files.FilesScreenViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by djqrj on 12/3/2017.
 */

@Module
public abstract class FilesScreenModule {


    @Binds
    @FragmentScope
    abstract FilesScreenContract.ViewModel viewModel(FilesScreenViewModel vIewModel);

}
