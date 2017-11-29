package com.ekalips.instastudy.main.mvvm.view.group_chat.attachments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.BottomDialogChatAttachmentBinding;
import com.ekalips.instastudy.main.contract.chat.attachments.ChatAttachmentContract;
import com.ekalips.instastudy.main.navigation.chat_navigation.AttachmentsLocalNavigator;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingBottomSheetDialogFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by ekalips on 11/29/17.
 */

public class ChatAttachmentDialog extends BaseBindingBottomSheetDialogFragment<BottomDialogChatAttachmentBinding, ChatAttachmentContract.View, ChatAttachmentContract.ViewModel>
        implements ChatAttachmentContract.View, HasSupportFragmentInjector {

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

    @Inject
    AttachmentsLocalNavigator attachmentsLocalNavigator;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {
        switch ((ChatAttachmentContract.Pages) eventNavigate.getNavigationPlace()) {
            case TAKE_PHOTO: {
                attachmentsLocalNavigator.showTakeImageFragment();
                break;
            }
            case SELECT_IMAGE: {
                attachmentsLocalNavigator.showSelectImageFragment();
                break;
            }
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
