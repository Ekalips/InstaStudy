package com.ekalips.instastudy.main.mvvm.view.notifications;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.FragmentNotificationsScreenBinding;
import com.ekalips.instastudy.main.contract.NotificationsScreenContract;
import com.ekalips.instastudy.main.mvvm.model.notifications.NotificationsRecyclerViewAdapter;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsScreenFragment extends BaseBindingFragment<FragmentNotificationsScreenBinding, NotificationsScreenContract.View, NotificationsScreenContract.ViewModel> implements NotificationsScreenContract.View {

    private static final String ARG_GROUP_ID = "groupId";

    public static NotificationsScreenFragment newInstance(String groupId) {
        Bundle args = new Bundle();
        args.putString(ARG_GROUP_ID, groupId);
        NotificationsScreenFragment fragment = new NotificationsScreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutResId() {
        return R.layout.fragment_notifications_screen;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public NotificationsScreenContract.View getViewInterface() {
        return this;
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extractAndInit();
    }

    @Override
    public void onBindingReady(FragmentNotificationsScreenBinding binding) {
        super.onBindingReady(binding);
        binding.recyclerView.setAdapter(new NotificationsRecyclerViewAdapter());
    }

    private void extractAndInit() {
        String groupId = getArguments().getString(ARG_GROUP_ID);
        getViewModel().init(groupId);
    }
}
