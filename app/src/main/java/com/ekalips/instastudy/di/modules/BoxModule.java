package com.ekalips.instastudy.di.modules;

import android.content.Context;

import com.ekalips.instastudy.data.groups.source.local.LocalGroup;
import com.ekalips.instastudy.data.stuff.MyObjectBox;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * Created by Ekalips on 10/2/17.
 */

@Module
public class BoxModule {

    @Provides
    @Singleton
    BoxStore provideBoxStore(Context context) {
        return MyObjectBox.builder().androidContext(context).build();
    }

    @Provides
    @Singleton
    Box<LocalGroup> provideLocalGroupBox(BoxStore boxStore) {
        return boxStore.boxFor(LocalGroup.class);
    }

}
