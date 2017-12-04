package com.ekalips.instastudy.di.modules;

import com.ekalips.instastudy.data.files.FilesDataProvider;
import com.ekalips.instastudy.data.files.FilesDataProviderImpl;
import com.ekalips.instastudy.data.groups.GroupDataProvider;
import com.ekalips.instastudy.data.groups.GroupDataProviderImpl;
import com.ekalips.instastudy.data.lessons.LessonDataProviderImpl;
import com.ekalips.instastudy.data.lessons.LessonDataProvider;
import com.ekalips.instastudy.data.messages.MessageDataProvider;
import com.ekalips.instastudy.data.messages.MessagesDataProviderImpl;
import com.ekalips.instastudy.data.notifications.NotificationsDataProvider;
import com.ekalips.instastudy.data.notifications.NotificationsDataProviderImpl;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.data.user.UserDataProviderImpl;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 10/2/17.
 */

@Module(includes = {UserDataModule.class, GroupsDataModule.class, MessagesDataModule.class, LessonsDataModule.class, FilesDataModule.class, NotificationsDataModule.class})
abstract public class DataModules {

    @DataProvider
    @Binds
    @Singleton
    abstract UserDataProvider bindUserDataProvider(UserDataProviderImpl userDataProvider);

    @DataProvider
    @Binds
    @Singleton
    abstract GroupDataProvider bindGroupsProvider(GroupDataProviderImpl groupDataProvider);

    @DataProvider
    @Binds
    @Singleton
    abstract MessageDataProvider messageDataProvider(MessagesDataProviderImpl messagesDataProvider);

    @DataProvider
    @Binds
    @Singleton
    abstract LessonDataProvider lessonsDataProvider(LessonDataProviderImpl lessonDataProvider);

    @DataProvider
    @Binds
    @Singleton
    abstract FilesDataProvider filesDataProvider(FilesDataProviderImpl filesDataProvider);

    @DataProvider
    @Binds
    @Singleton
    abstract NotificationsDataProvider notificationsDataProvider(NotificationsDataProviderImpl notificationsDataProvider);

}
