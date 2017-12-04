package com.ekalips.instastudy.main.mvvm.view.notifications;


import android.support.v4.app.Fragment;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.FragmentNotificationsScreenBinding;
import com.ekalips.instastudy.main.contract.NotificationsScreenContract;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsScreenFragment extends BaseBindingFragment<FragmentNotificationsScreenBinding, NotificationsScreenContract.View, NotificationsScreenContract.ViewModel> implements NotificationsScreenContract.View {


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
    public void onBindingReady(FragmentNotificationsScreenBinding binding) {
        super.onBindingReady(binding);

    }
}
