package com.ekalips.instastudy.providers.firebase_login;

import com.google.firebase.auth.FirebaseUser;

import javax.annotation.Nullable;

/**
 * Created by Ekalips on 11/5/17.
 */

public interface FirebaseLoginProvider {


    void setLoginCallbacks(@Nullable LoginCallbacks loginCallbacks);

    void login();

    void logout();

    void cleanup();

    interface LoginCallbacks {
        void onUserLoggedIn(FirebaseUser firebaseUser);

        void onUserLogout();
    }
}
