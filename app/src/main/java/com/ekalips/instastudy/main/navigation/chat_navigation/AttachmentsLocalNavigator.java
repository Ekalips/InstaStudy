package com.ekalips.instastudy.main.navigation.chat_navigation;

import android.support.v4.app.FragmentManager;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.di.scopes.SubFragmentScope;
import com.ekalips.instastudy.main.mvvm.view.group_chat.attachments.AttachmentTakeImageFragment;
import com.ekalips.instastudy.main.mvvm.view.group_chat.attachments.ChatAttachmentDialog;

import javax.inject.Inject;

/**
 * Created by ekalips on 11/29/17.
 */

@SubFragmentScope
public class AttachmentsLocalNavigator {

    private final FragmentManager fragmentManager;

    @Inject
    public AttachmentsLocalNavigator(ChatAttachmentDialog chatAttachmentDialog) {
        this.fragmentManager = chatAttachmentDialog.getChildFragmentManager();
    }

    public void showTakeImageFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new AttachmentTakeImageFragment(), AttachmentTakeImageFragment.class.getSimpleName())
                .commit();
    }

    public void showSelectImageFragment() {
        // TODO: 11/29/17 showSelectImageFragment
    }
}
