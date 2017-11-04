package com.ekalips.instastudy.splash.mvvm.view;

import android.databinding.ViewDataBinding;

import com.ekalips.instastudy.splash.SplashScreeContract;
import com.wonderslab.base.activity.BaseBindingActivity;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;

public class SplashScreenActivity extends BaseBindingActivity<ViewDataBinding, SplashScreeContract.View, SplashScreeContract.ViewModel> implements SplashScreeContract.View {

    @Override
    public int layoutResId() {
        return 0;
    }

    @Override
    public int getViewModelBRId() {
        return 0;
    }

    @Override
    public SplashScreeContract.View getViewInterface() {
        return this;
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {

    }

    @Override
    public boolean inflateView() {
        return false;
    }
}
