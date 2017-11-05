package com.ekalips.instastudy.registration.mvvm.vm;

import com.ekalips.instastudy.navigation.NavigateToEnum;
import com.ekalips.instastudy.registration.contract.RegistrationActivityContract;
import com.ekalips.instastudy.registration.mvvm.model.FillDataObservable;
import com.ekalips.instastudy.registration.rules.CredentialsValidator;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/5/17.
 */

public class RegistrationActivityViewModel extends RegistrationActivityContract.ViewModel {

    private final FillDataObservable data = new FillDataObservable();
    private final CredentialsValidator credentialsValidator;

    @Inject
    public RegistrationActivityViewModel(CredentialsValidator credentialsValidator) {
        this.credentialsValidator = credentialsValidator;
        navigateTo(NavigateToEnum.FILL_DATA, null);
    }

    @Override
    public CredentialsValidator getValidator() {
        return credentialsValidator;
    }

    @Override
    public FillDataObservable getRegistrationData() {
        return data;
    }
}
