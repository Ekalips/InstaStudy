package com.ekalips.instastudy.registration.mvvm.vm;

import com.ekalips.instastudy.navigation.NavigateToEnum;
import com.ekalips.instastudy.registration.contract.RegistrationActivityContract;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/5/17.
 */

public class RegistrationActivityViewModel extends RegistrationActivityContract.ViewModel {

    @Inject
    public RegistrationActivityViewModel() {
        navigateTo(NavigateToEnum.FILL_DATA, null);
    }

}
