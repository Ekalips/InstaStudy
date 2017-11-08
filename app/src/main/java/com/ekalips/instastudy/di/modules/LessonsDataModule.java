package com.ekalips.instastudy.di.modules;

import com.ekalips.instastudy.data.lessons.sources.local.LocalLessonDataProvider;
import com.ekalips.instastudy.data.lessons.sources.local.LocalLessonDataProviderImpl;
import com.ekalips.instastudy.data.lessons.sources.remote.RemoteLessonDataProvider;
import com.ekalips.instastudy.data.lessons.sources.remote.RemoteLessonDataProviderImpl;
import com.ekalips.instastudy.di.source_qualifier.Local;
import com.ekalips.instastudy.di.source_qualifier.Remote;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 11/8/17.
 */

@Module
public abstract class LessonsDataModule {

    @Singleton
    @Binds
    @Local
    abstract LocalLessonDataProvider localLessonDataProvider(LocalLessonDataProviderImpl lessonDataProvider);

    @Singleton
    @Binds
    @Remote
    abstract RemoteLessonDataProvider remoteLessonDataProvider(RemoteLessonDataProviderImpl remoteLessonDataProvider);

}
