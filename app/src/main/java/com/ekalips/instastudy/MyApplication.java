package com.ekalips.instastudy;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.os.StrictMode;

import com.ekalips.instastudy.di.app.DaggerAppComponent;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import io.objectbox.android.AndroidObjectBrowser;

/**
 * Created by Ekalips on 10/2/17.
 */

public class MyApplication extends Application implements HasActivityInjector, HasServiceInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Service> serviceDispatchingAndroidInjector;

    @Inject
    AndroidObjectBrowser androidObjectBrowser;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        if (BuildConfig.DEBUG) {
            initDebugStuff();
            androidObjectBrowser.start(this);
        }
    }

    private void initDebugStuff() {
        setupStetho();
        initStrictMode();
    }

    private void setupStetho() {
        Stetho.initializeWithDefaults(this);
    }

    private void initStrictMode() {
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        .build());

        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        .build());
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceDispatchingAndroidInjector;
    }
}
