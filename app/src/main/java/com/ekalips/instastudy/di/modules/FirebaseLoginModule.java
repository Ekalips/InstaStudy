package com.ekalips.instastudy.di.modules;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ekalips on 11/5/17.
 */

@Module
public class FirebaseLoginModule {

    @Provides
    @Singleton
    public AuthUI provideAuthUI() {
        return AuthUI.getInstance();
    }

    @Provides
    @Singleton
    public FirebaseAuth provideAuth() {
        return FirebaseAuth.getInstance();
    }

}
