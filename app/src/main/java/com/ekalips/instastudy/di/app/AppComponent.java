package com.ekalips.instastudy.di.app;

import android.app.Application;

import com.ekalips.instastudy.MyApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Created by Ekalips on 10/2/17.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, AndroidSupportInjectionModule.class, AppModule.class, ActivityBuilder.class, ServiceBuilder.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(MyApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
