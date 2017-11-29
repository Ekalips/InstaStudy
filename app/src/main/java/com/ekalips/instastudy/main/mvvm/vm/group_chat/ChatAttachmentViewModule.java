package com.ekalips.instastudy.main.mvvm.vm.group_chat;

import com.ekalips.instastudy.main.contract.chat.ChatAttachmentContract;
import com.wonderslab.base.rx.RxRequests;

import javax.inject.Inject;

/**
 * Created by ekalips on 11/29/17.
 */


public class ChatAttachmentViewModule extends ChatAttachmentContract.ViewModel {

    @Inject
    public ChatAttachmentViewModule(RxRequests rxRequests) {
        super(rxRequests);
    }
}
