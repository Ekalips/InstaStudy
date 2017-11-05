package com.ekalips.instastudy.registration.navigation;

import android.support.v4.app.FragmentManager;

import com.ekalips.instastudy.registration.mvvm.view.FillDataFragment;
import com.ekalips.instastudy.registration.mvvm.view.RegistrationActivity;
import com.ekalips.instastudy.registration.mvvm.view.SetImageFragment;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/5/17.
 */

public class LocalNavigatorImpl implements LocalNavigator {

    private final FragmentManager fragmentManager;

    @Inject
    public LocalNavigatorImpl(RegistrationActivity activity) {
        this.fragmentManager = activity.getSupportFragmentManager();
    }

    @Override
    public void navigateToFillDataScreen(int containerRes) {
        fragmentManager.beginTransaction()
                .replace(containerRes, FillDataFragment.newInstance(), FillDataFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void navigateToSetImageScreen(int containerRes) {
        fragmentManager.beginTransaction()
                .replace(containerRes, SetImageFragment.newInstance(), SetImageFragment.class.getSimpleName())
                .commit();
    }
}
