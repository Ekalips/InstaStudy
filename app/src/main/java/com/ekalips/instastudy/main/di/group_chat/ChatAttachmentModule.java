package com.ekalips.instastudy.main.di.group_chat;

import com.ekalips.instastudy.di.scopes.SubFragmentScope;
import com.ekalips.instastudy.main.contract.chat.ChatAttachmentContract;
import com.ekalips.instastudy.main.mvvm.vm.group_chat.ChatAttachmentViewModule;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ekalips on 11/29/17.
 */

@Module
@SubFragmentScope
public abstract class ChatAttachmentModule {

    @Binds
    @SubFragmentScope
    abstract ChatAttachmentContract.ViewModel viewModel(ChatAttachmentViewModule viewModule);

}
