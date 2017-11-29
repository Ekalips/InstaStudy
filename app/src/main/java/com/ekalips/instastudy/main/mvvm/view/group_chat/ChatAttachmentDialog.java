package com.ekalips.instastudy.main.mvvm.view.group_chat;

import android.os.Bundle;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.BottomDialogChatAttachmentBinding;
import com.ekalips.instastudy.main.contract.chat.ChatAttachmentContract;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingBottomSheetDialogFragment;

/**
 * Created by ekalips on 11/29/17.
 */

public class ChatAttachmentDialog extends BaseBindingBottomSheetDialogFragment<BottomDialogChatAttachmentBinding, ChatAttachmentContract.View, ChatAttachmentContract.ViewModel> implements ChatAttachmentContract.View {

    public static ChatAttachmentDialog newInstance() {
        Bundle args = new Bundle();
        ChatAttachmentDialog fragment = new ChatAttachmentDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutResId() {
        return R.layout.bottom_dialog_chat_attachment;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public ChatAttachmentContract.View getViewInterface() {
        return this;
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {

    }
}
