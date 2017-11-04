package com.ekalips.instastudy.di.app;

import android.app.Application;
import android.content.Context;

import com.ekalips.instastudy.di.modules.BoxModule;
import com.ekalips.instastudy.di.modules.DataModules;
import com.ekalips.instastudy.di.modules.ErrorHandlerModule;
import com.ekalips.instastudy.di.modules.EventsModule;
import com.ekalips.instastudy.di.modules.MessagingModule;
import com.ekalips.instastudy.di.modules.NavigationModule;
import com.ekalips.instastudy.di.modules.NetworkModule;
import com.ekalips.instastudy.di.modules.SharedPrefsModule;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 10/2/17.
 */

@Module(includes = {BoxModule.class, DataModules.class, MessagingModule.class, SharedPrefsModule.class,
        NavigationModule.class, NetworkModule.class, EventsModule.class, ErrorHandlerModule.class})
public abstract class AppModule {

    @Binds
    public abstract Context bindContext(Application application);
}
