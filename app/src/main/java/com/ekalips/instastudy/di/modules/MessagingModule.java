package com.ekalips.instastudy.di.modules;

import android.content.Context;

import com.ekalips.instastudy.providers.ToastProvider;

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
    ToastProvider provideMessager(Context context) {
        return new ToastProvider(context);
    }

}
