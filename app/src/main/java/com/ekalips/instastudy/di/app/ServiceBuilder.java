package com.ekalips.instastudy.di.app;


import com.ekalips.instastudy.di.scopes.ServiceScope;
import com.ekalips.instastudy.firebase.FirebaseTokenService;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Ekalips on 10/5/17.
 */

@Module
public abstract class ServiceBuilder {

    @ServiceScope
    @ContributesAndroidInjector
    abstract FirebaseTokenService firebaseInstanceService();

}
