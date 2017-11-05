package com.ekalips.instastudy.navigation;

import android.content.Context;

/**
 * Created by Ekalips on 10/9/17.
 */

public interface GlobalNavigator {

    void navigateToSplashActivity(Context context);

    void navigateToMainActivity(Context context);

    void navigateToLoginActivity(Context context);

    void navigateToRegistrationActivity(Context context);
}
