package com.ekalips.instastudy.main.mvvm.view.group_chat;


import android.databinding.Observable;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.FragmentChatBinding;
import com.ekalips.instastudy.main.contract.chat.GroupChatScreenContract;
import com.ekalips.instastudy.main.mvvm.model.messages.MessagesRecyclerViewAdapter;
import com.ekalips.instastudy.main.navigation.chat_navigation.LocalChatNavigator;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingFragment;
import com.wonderslab.base.recyclerview.PaginatedRecyclerViewAdapter;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends BaseBindingFragment<FragmentChatBinding, GroupChatScreenContract.View, GroupChatScreenContract.ViewModel> implements GroupChatScreenContract.View,
        HasSupportFragmentInjector {

    private static final String ARG_GROUP_ID = "arg_group_id";

    public static ChatFragment newInstance(String groupId) {
        Bundle args = new Bundle();
        args.putString(ARG_GROUP_ID, groupId);
        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    private MessagesRecyclerViewAdapter messagesRecyclerViewAdapter;

    @Override
    protected int layoutResId() {
        return R.layout.fragment_chat;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public GroupChatScreenContract.View getViewInterface() {
        return this;
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {

    }

    @Override
    public void clearInput() {
        binding.messageEt.setText("");
    }

    @Override
    public void scrollToBottom() {
        binding.recyclerView.scrollToPosition(0);
    }

    @Inject
    LocalChatNavigator localChatNavigator;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messagesRecyclerViewAdapter = new MessagesRecyclerViewAdapter(paginatedListCallbacks);
        messagesRecyclerViewAdapter.setMyUserId(getViewModel().getMyUserId().get());

        getViewModel().getMyUserId().addOnPropertyChangedCallback(onMyIdChanged);

        extractAndInit();
    }

    @Override
    public void onBindingReady(FragmentChatBinding binding) {
        super.onBindingReady(binding);
        binding.messageEt.addTextChangedListener(messageTextWatcher);
        binding.recyclerView.setAdapter(messagesRecyclerViewAdapter);
        binding.messageInputContainer.addOnLayoutChangeListener(onLayoutChangeListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.messageEt.removeTextChangedListener(messageTextWatcher);
        binding.messageInputContainer.removeOnLayoutChangeListener(onLayoutChangeListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getViewModel().getMyUserId().removeOnPropertyChangedCallback(onMyIdChanged);
    }

    @Override
    public void showAttachmentDialog() {
        localChatNavigator.showChatAttachmentDialog();
    }

    private void extractAndInit() {
        String groupId = getArguments().getString(ARG_GROUP_ID);
        getViewModel().init(groupId);
    }

    private final View.OnLayoutChangeListener onLayoutChangeListener = (v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) ->
            binding.recyclerView.setPadding(binding.recyclerView.getPaddingLeft(),
                    binding.recyclerView.getPaddingTop(), binding.recyclerView.getPaddingRight(),
                    binding.messageInputContainer.getHeight());

    private final ObservableField.OnPropertyChangedCallback onMyIdChanged = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable observable, int i) {
            if (messagesRecyclerViewAdapter != null) {
                messagesRecyclerViewAdapter.setMyUserId(getViewModel().getMyUserId().get());
            }
        }
    };

    private final PaginatedRecyclerViewAdapter.PaginatedListCallbacks paginatedListCallbacks = () -> getViewModel().requestNextPage();
    private final TextWatcher messageTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            TransitionManager.beginDelayedTransition(binding.messageInputContainer, new AutoTransition().setDuration(200));
            binding.sendMessageBtn.setVisibility(s.length() != 0 ? View.VISIBLE : View.GONE);
            binding.showAttachmentsBtn.setVisibility(s.length() == 0 ? View.VISIBLE : View.GONE);
        }
    };

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
