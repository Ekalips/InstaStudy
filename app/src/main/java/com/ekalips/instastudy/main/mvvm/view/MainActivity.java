package com.ekalips.instastudy.main.mvvm.view;

import android.content.Context;
import android.content.Intent;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.ActivityMainBinding;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.wonderslab.base.BR;
import com.wonderslab.base.activity.BaseBindingActivity;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding, MainActivityContract.View, MainActivityContract.ViewModel> implements MainActivityContract.View {

    public static Intent getIntentFor(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public int layoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public MainActivityContract.View getViewInterface() {
        return this;
    }

    @Override
    protected void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {

    }


}
