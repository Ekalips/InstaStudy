package com.ekalips.instastudy.main.di.group_chat;

import com.ekalips.instastudy.di.scopes.FragmentScope;
import com.ekalips.instastudy.main.contract.GroupChatScreenContract;
import com.ekalips.instastudy.main.mvvm.vm.group_chat.GroupChatViewModule;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 11/6/17.
 */

@Module
public abstract class GroupChatModule {

    @FragmentScope
    @Binds
    abstract GroupChatScreenContract.ViewModel bindVM(GroupChatViewModule viewModule);

}
