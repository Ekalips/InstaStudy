package com.ekalips.instastudy.login.di;

import android.support.v4.app.FragmentActivity;

import com.ekalips.instastudy.di.scopes.ActivityScope;
import com.ekalips.instastudy.login.LoginScreenContract;
import com.ekalips.instastudy.login.mvvm.view.LoginActivity;
import com.ekalips.instastudy.login.mvvm.vm.LoginScreenViewModel;
import com.ekalips.instastudy.providers.firebase_login.FirebaseLoginProvider;
import com.ekalips.instastudy.providers.firebase_login.FirebaseLoginProviderImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 11/5/17.
 */

@Module
public abstract class LoginScreenModule {

    @Binds
    @ActivityScope
    public abstract LoginScreenContract.ViewModel bindVM(LoginScreenViewModel viewModel);

    @Binds
    @ActivityScope
    public abstract FragmentActivity bindActivity(LoginActivity loginActivity);

    @Binds
    @ActivityScope
    public abstract FirebaseLoginProvider bindFirebaesLogin(FirebaseLoginProviderImpl firebaseLoginProvider);

}
