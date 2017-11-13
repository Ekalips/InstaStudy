package com.ekalips.instastudy.providers.firebase_login;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.ekalips.instastudy.providers.ToastProvider;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Collections;

import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * Created by Ekalips on 11/5/17.
 */

public class FirebaseLoginProviderImpl implements FirebaseLoginProvider {

    private static final String TAG = FirebaseLoginProviderImpl.class.getSimpleName();

//    private final int REQUEST_CODE_SIGN_IN = 1337;

    private final FirebaseAuth firebaseAuth;
    private final AuthUI authUI;
    private FragmentActivity parentActivity;

    private final ToastProvider toastProvider;

    @Nullable
    private LoginCallbacks loginCallbacks;

    @Inject
    public FirebaseLoginProviderImpl(FirebaseAuth firebaseAuth, AuthUI authUI, ToastProvider toastProvider, FragmentActivity parentActivity) {
        this.firebaseAuth = firebaseAuth;
        this.authUI = authUI;
        this.toastProvider = toastProvider;
        this.parentActivity = parentActivity;

        firebaseAuth.addAuthStateListener(authStateListener);
        firebaseAuth.addIdTokenListener(idTokenListener);
    }

    @Override
    public void setLoginCallbacks(@Nullable LoginCallbacks loginCallbacks) {
        this.loginCallbacks = loginCallbacks;
    }

    @Override
    public void login() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            if (loginCallbacks != null) {
                loginCallbacks.onUserLoggedIn(firebaseUser);
            }
        } else {
            initLogin();
        }
    }

    @Override
    public void logout() {
        authUI.signOut(parentActivity)
                .addOnCompleteListener(task -> {
                    if (loginCallbacks != null) {
                        loginCallbacks.onUserLogout();
                    }
                });
    }

    @Override
    public void cleanup() {
        firebaseAuth.removeAuthStateListener(authStateListener);
        firebaseAuth.removeIdTokenListener(idTokenListener);
        this.parentActivity = null;
        this.loginCallbacks = null;
    }

    private void initLogin() {
        parentActivity.startActivity(
                authUI.createSignInIntentBuilder()
                        .setAvailableProviders(Collections.singletonList(new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()))
                        .build());
    }

    private final FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            Log.d(TAG, "onAuthStateChanged: ");
            if (firebaseAuth.getCurrentUser() != null && loginCallbacks != null) {
                loginCallbacks.onUserLoggedIn(firebaseAuth.getCurrentUser());
            }
        }
    };

    private final FirebaseAuth.IdTokenListener idTokenListener = firebaseAuth -> Log.d(TAG, "onIdTokenChanged: ");
}
