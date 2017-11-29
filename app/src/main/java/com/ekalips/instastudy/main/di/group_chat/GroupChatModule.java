package com.ekalips.instastudy.main.di.group_chat;

import com.ekalips.instastudy.di.scopes.FragmentScope;
import com.ekalips.instastudy.di.scopes.SubFragmentScope;
import com.ekalips.instastudy.main.contract.chat.GroupChatScreenContract;
import com.ekalips.instastudy.main.mvvm.view.group_chat.ChatAttachmentDialog;
import com.ekalips.instastudy.main.mvvm.vm.group_chat.GroupChatViewModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Ekalips on 11/6/17.
 */

@Module
public abstract class GroupChatModule {

    @FragmentScope
    @Binds
    abstract GroupChatScreenContract.ViewModel bindVM(GroupChatViewModule viewModule);

    @SubFragmentScope
    @ContributesAndroidInjector(modules = ChatAttachmentModule.class)
    abstract ChatAttachmentDialog chatAttachmentDialog();

}
