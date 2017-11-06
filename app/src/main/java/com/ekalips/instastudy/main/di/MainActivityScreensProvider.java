package com.ekalips.instastudy.main.di;

import com.ekalips.instastudy.di.scopes.FragmentScope;
import com.ekalips.instastudy.main.di.group_chat.GroupChatModule;
import com.ekalips.instastudy.main.mvvm.view.group_chat.ChatFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Ekalips on 11/6/17.
 */

@Module
public abstract class MainActivityScreensProvider {

    @ContributesAndroidInjector(modules = GroupChatModule.class)
    @FragmentScope
    abstract ChatFragment chatFragment();

}
