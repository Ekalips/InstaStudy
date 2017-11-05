package com.ekalips.instastudy.registration.di;

import com.ekalips.instastudy.di.scopes.ActivityScope;
import com.ekalips.instastudy.registration.contract.RegistrationActivityContract;
import com.ekalips.instastudy.registration.mvvm.vm.RegistrationActivityViewModel;
import com.ekalips.instastudy.registration.navigation.LocalNavigator;
import com.ekalips.instastudy.registration.navigation.LocalNavigatorImpl;
import com.ekalips.instastudy.registration.rules.CredentialsValidator;
import com.ekalips.instastudy.registration.rules.CredentialsValidatorImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 11/5/17.
 */

@Module(includes = {RegistrationScreensProvider.class})
@ActivityScope
public abstract class RegistrationScreenModule {

    @Binds
    @ActivityScope
    abstract RegistrationActivityContract.ViewModel bindVM(RegistrationActivityViewModel viewModel);

    @Binds
    @ActivityScope
    abstract LocalNavigator bindNavigator(LocalNavigatorImpl localNavigator);

    @Binds
    @ActivityScope
    abstract CredentialsValidator bindValidator(CredentialsValidatorImpl credentialsValidator);

}
