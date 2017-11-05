package com.ekalips.instastudy.registration.mvvm.vm;

import com.ekalips.instastudy.registration.contract.FillDataScreenContract;
import com.ekalips.instastudy.registration.contract.RegistrationActivityContract;
import com.ekalips.instastudy.registration.mvvm.model.FillDataObservable;
import com.ekalips.instastudy.registration.rules.CredentialsValidator;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/5/17.
 */

public class FillDataScreenViewModel extends FillDataScreenContract.ViewModel {

    private final RegistrationActivityContract.ViewModel registrationActivityViewModel;

    @Inject
    public FillDataScreenViewModel(RegistrationActivityContract.ViewModel registrationActivityViewModel) {
        this.registrationActivityViewModel = registrationActivityViewModel;
    }

    @Override
    public FillDataObservable getData() {
        return registrationActivityViewModel.getRegistrationData();
    }

    @Override
    public CredentialsValidator getValidator() {
        return registrationActivityViewModel.getValidator();
    }

    @Override
    public void signUp() {
        registrationActivityViewModel.register();
    }
}
