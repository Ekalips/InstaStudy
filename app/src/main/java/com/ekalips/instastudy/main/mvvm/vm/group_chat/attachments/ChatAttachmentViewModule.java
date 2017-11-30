package com.ekalips.instastudy.main.mvvm.vm.group_chat.attachments;

import android.databinding.ObservableField;

import com.ekalips.instastudy.main.contract.chat.attachments.ChatAttachmentContract;
import com.wonderslab.base.rx.RxRequests;

import javax.inject.Inject;

/**
 * Created by ekalips on 11/29/17.
 */


public class ChatAttachmentViewModule extends ChatAttachmentContract.ViewModel {

    private final ObservableField<ChatAttachmentContract.Pages> currentPage = new ObservableField<>();

    @Inject
    public ChatAttachmentViewModule(RxRequests rxRequests) {
        super(rxRequests);
        changePage(ChatAttachmentContract.Pages.TAKE_PHOTO);
    }

    @Override
    public ObservableField<ChatAttachmentContract.Pages> getCurrentPage() {
        return currentPage;
    }

    public void changePage(ChatAttachmentContract.Pages page) {
        if (page != currentPage.get()) {
            currentPage.set(page);
            navigateTo(page, null);
        }
    }
}
