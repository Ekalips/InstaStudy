package com.ekalips.instastudy.di.modules;

import com.ekalips.instastudy.stuff.Const;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ekalips on 10/23/17.
 */

@Module
public class EventsModule {

    @Singleton
    @Provides
    @Named(Const.NAME_FIREBASE_EVENT_BUS)
    EventBus provideFirebaseBus() {
        return new EventBus();
    }

}
