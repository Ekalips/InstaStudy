package com.ekalips.instastudy.login.mvvm.view;

import android.content.Context;
import android.content.Intent;

import com.android.databinding.library.baseAdapters.BR;
import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.ActivityLoginBinding;
import com.ekalips.instastudy.login.LoginScreenContract;
import com.ekalips.instastudy.navigation.GlobalNavigator;
import com.ekalips.instastudy.navigation.NavigateToEnum;
import com.wonderslab.base.activity.BaseBindingActivity;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;

import javax.inject.Inject;

public class LoginActivity extends BaseBindingActivity<ActivityLoginBinding, LoginScreenContract.View, LoginScreenContract.ViewModel> implements LoginScreenContract.View {

    public static Intent getIntentFor(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public int layoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public LoginScreenContract.View getViewInterface() {
        return this;
    }

    @Override
    protected void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {
        switch ((NavigateToEnum) eventNavigate.getNavigationPlace()) {
            case REGISTER: {
                finish();
                globalNavigator.navigateToRegistrationActivity(this);
                break;
            }
            case MAIN:
                finish();
                globalNavigator.navigateToMainActivity(this);
                break;
        }
    }

    @Inject
    GlobalNavigator globalNavigator;
}
