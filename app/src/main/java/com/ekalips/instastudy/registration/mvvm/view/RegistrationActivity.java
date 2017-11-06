package com.ekalips.instastudy.registration.mvvm.view;

import android.content.Context;
import android.content.Intent;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.ActivityRegistrationBinding;
import com.ekalips.instastudy.navigation.GlobalNavigator;
import com.ekalips.instastudy.navigation.NavigateToEnum;
import com.ekalips.instastudy.registration.contract.RegistrationActivityContract;
import com.ekalips.instastudy.registration.navigation.LocalNavigator;
import com.wonderslab.base.BR;
import com.wonderslab.base.activity.BaseBindingActivity;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;

import javax.inject.Inject;

public class RegistrationActivity extends BaseBindingActivity<ActivityRegistrationBinding, RegistrationActivityContract.View, RegistrationActivityContract.ViewModel>
        implements RegistrationActivityContract.View {

    public static Intent getIntent(Context context) {
        return new Intent(context, RegistrationActivity.class);
    }

    @Override
    public int layoutResId() {
        return R.layout.activity_registration;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public RegistrationActivityContract.View getViewInterface() {
        return this;
    }

    @Override
    protected void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {
        switch ((NavigateToEnum) eventNavigate.getNavigationPlace()) {
            case FILL_DATA: {
                localNavigator.navigateToFillDataScreen(R.id.fragment_container);
                break;
            }
            case SET_IMAGE: {
                localNavigator.navigateToSetImageScreen(R.id.fragment_container);
                break;
            }
            case MAIN: {
                globalNavigator.navigateToMainActivity(this);
                break;
            }
        }
    }

    @Inject
    GlobalNavigator globalNavigator;

    @Inject
    LocalNavigator localNavigator;
}
