package com.ekalips.instastudy.main.di.group_chat;

import com.ekalips.instastudy.di.scopes.SubSubFragmentScope;
import com.ekalips.instastudy.main.contract.chat.attachments.AttachmentTakePhotoContract;
import com.ekalips.instastudy.main.mvvm.vm.group_chat.attachments.AttachmentTakePhotoViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ekalips on 11/29/17.
 */

@Module
@SubSubFragmentScope
public abstract class AttachmentsModule {

    @Binds
    @SubSubFragmentScope
    abstract AttachmentTakePhotoContract.ViewModel takePhotoVM(AttachmentTakePhotoViewModel viewModel);

}
