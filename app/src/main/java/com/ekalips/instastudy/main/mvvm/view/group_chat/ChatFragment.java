package com.ekalips.instastudy.main.mvvm.view.group_chat;


import android.databinding.Observable;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.FragmentChatBinding;
import com.ekalips.instastudy.main.contract.GroupChatScreenContract;
import com.ekalips.instastudy.main.mvvm.model.MessagesRecyclerViewAdapter;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingFragment;
import com.wonderslab.base.recyclerview.PaginatedRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends BaseBindingFragment<FragmentChatBinding, GroupChatScreenContract.View, GroupChatScreenContract.ViewModel> implements GroupChatScreenContract.View {

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
        binding.recyclerView.setAdapter(messagesRecyclerViewAdapter);
        binding.messageInputContainer.addOnLayoutChangeListener(onLayoutChangeListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getViewModel().getMyUserId().removeOnPropertyChangedCallback(onMyIdChanged);
        binding.messageInputContainer.removeOnLayoutChangeListener(onLayoutChangeListener);
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
}
