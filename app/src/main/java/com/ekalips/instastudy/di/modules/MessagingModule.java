package com.ekalips.instastudy.di.modules;

import android.content.Context;

import com.ekalips.instastudy.providers.MessagingProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ekalips on 10/3/17.
 */

@Module
public class MessagingModule {

    @Provides
    @Singleton
    MessagingProvider provideMessager(Context context) {
        return new MessagingProvider(context);
    }

}
