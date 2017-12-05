package com.ekalips.instastudy.main.di.group_chat;

import com.ekalips.instastudy.di.scopes.SubFragmentScope;
import com.ekalips.instastudy.di.scopes.SubSubFragmentScope;
import com.ekalips.instastudy.main.contract.chat.attachments.ChatAttachmentContract;
import com.ekalips.instastudy.main.mvvm.view.group_chat.attachments.AttachmentGalleryFragment;
import com.ekalips.instastudy.main.mvvm.view.group_chat.attachments.AttachmentTakeImageFragment;
import com.ekalips.instastudy.main.mvvm.vm.group_chat.attachments.ChatAttachmentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ekalips on 11/29/17.
 */

@Module
@SubFragmentScope
public abstract class ChatAttachmentModule {

    @Binds
    @SubFragmentScope
    abstract ChatAttachmentContract.ViewModel viewModel(ChatAttachmentViewModel viewModule);

    @SubSubFragmentScope
    @ContributesAndroidInjector(modules = AttachmentsModule.class)
    abstract AttachmentTakeImageFragment attachemtTakeImageFragment();

    @SubSubFragmentScope
    @ContributesAndroidInjector(modules = AttachmentsModule.class)
    abstract AttachmentGalleryFragment attachmentGalleryFragment();

}
