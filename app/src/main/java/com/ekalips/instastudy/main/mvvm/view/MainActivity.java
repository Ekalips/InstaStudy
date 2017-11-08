package com.ekalips.instastudy.main.mvvm.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarDrawerToggle;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.ActivityMainBinding;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.ekalips.instastudy.main.navigation.MainLocalNavigator;
import com.ekalips.instastudy.navigation.NavigateToEnum;
import com.ekalips.instastudy.stuff.CommonUtils;
import com.wonderslab.base.BR;
import com.wonderslab.base.activity.BaseBindingActivity;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;

import javax.inject.Inject;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding, MainActivityContract.View, MainActivityContract.ViewModel>
        implements MainActivityContract.View, MainActivityContract.FlexibleMainToolbar {

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
        binding.drawerLayout.closeDrawers();
        switch ((NavigateToEnum) eventNavigate.getNavigationPlace()) {
            case GROUP_CHAT: {
                localNavigator.navigateToGroupChat((String) eventNavigate.getPayload());
                break;
            }
            case SCHEDULE: {
                localNavigator.navigateToSchedule();
                break;
            }
        }
    }

    @Inject
    MainLocalNavigator localNavigator;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (binding.includeHeader != null) {
            binding.includeHeader.userHeader.setPadding(binding.includeHeader.userHeader.getPaddingLeft(), CommonUtils.getStatusBarHeight(this),
                    binding.includeHeader.userHeader.getPaddingRight(), binding.includeHeader.userHeader.getPaddingBottom());
        }

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.openDrawer, R.string.closeDrawer);
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.drawerLayout.removeDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public void onToolbarTitleChange(String title) {
        binding.setTitle(title);
    }

    @Override
    public void onMenuChange(int menu) {

    }


}
