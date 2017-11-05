package com.ekalips.instastudy.navigation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ekalips.instastudy.login.mvvm.view.LoginActivity;
import com.ekalips.instastudy.registration.mvvm.view.RegistrationActivity;
import com.ekalips.instastudy.splash.mvvm.view.SplashScreenActivity;

import javax.inject.Inject;

/**
 * Created by Ekalips on 10/9/17.
 */

public class GlobalNavigatorImpl implements GlobalNavigator {

    private static final String TAG = GlobalNavigatorImpl.class.getSimpleName();

    @Inject
    public GlobalNavigatorImpl() {

    }

    @Override
    public void navigateToSplashActivity(Context context) {
        Intent splashIntent = new Intent(context, SplashScreenActivity.class);
        splashIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(splashIntent);
    }

    @Override
    public void navigateToMainActivity(Context context) {
        Log.d(TAG, "navigateToMainActivity: ");
//        Intent mainIntent = MainActivity.getIntentFor(context);
//        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(mainIntent);
    }

    @Override
    public void navigateToLoginActivity(Context context) {
        context.startActivity(LoginActivity.getIntentFor(context));
    }

    @Override
    public void navigateToRegistrationActivity(Context context) {
        context.startActivity(RegistrationActivity.getIntent(context));
    }

}
