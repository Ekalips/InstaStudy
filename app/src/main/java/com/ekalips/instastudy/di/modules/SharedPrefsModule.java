package com.ekalips.instastudy.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.ekalips.instastudy.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ekalips on 10/2/17.
 */

@Module
@Singleton
public class SharedPrefsModule {


    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs(Context context) {
        return context.getSharedPreferences(BuildConfig.DEFAULT_SHARED_PREFS, Context.MODE_PRIVATE);
    }

}
