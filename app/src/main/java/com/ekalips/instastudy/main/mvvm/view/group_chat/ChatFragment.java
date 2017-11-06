package com.ekalips.instastudy.main.mvvm.view.group_chat;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.FragmentChatBinding;
import com.ekalips.instastudy.main.contract.GroupChatScreenContract;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingFragment;

import javax.inject.Inject;

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

    }

    @Inject
    MainActivityContract.FlexibleMainToolbar flexibleMainToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extractAndInit();
    }

    private void extractAndInit() {
        String groupId = getArguments().getString(ARG_GROUP_ID);
        getViewModel().init(groupId);
    }

    private void setupToolbar(){

    }
}
