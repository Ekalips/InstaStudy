package com.ekalips.instastudy.main.navigation.chat_navigation;

import android.support.v4.app.FragmentManager;

import com.ekalips.instastudy.di.scopes.FragmentScope;
import com.ekalips.instastudy.main.mvvm.view.group_chat.attachments.ChatAttachmentDialog;
import com.ekalips.instastudy.main.mvvm.view.group_chat.ChatFragment;

import javax.inject.Inject;

/**
 * Created by ekalips on 11/29/17.
 */

@FragmentScope
public class LocalChatNavigator {

    private final FragmentManager fragmentManager;

    @Inject
    public LocalChatNavigator(ChatFragment chatFragment) {
        this.fragmentManager = chatFragment.getChildFragmentManager();
    }

    public void showChatAttachmentDialog(String chatId) {
        ChatAttachmentDialog.newInstance(chatId).show(fragmentManager, ChatAttachmentDialog.class.getSimpleName());
    }
}
