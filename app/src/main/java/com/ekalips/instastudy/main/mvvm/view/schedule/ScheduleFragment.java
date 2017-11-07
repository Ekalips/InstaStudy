package com.ekalips.instastudy.main.mvvm.view.schedule;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.FragmentScheduleBinding;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.ekalips.instastudy.main.contract.ScheduleScreenContract;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingFragment;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends BaseBindingFragment<FragmentScheduleBinding, ScheduleScreenContract.View, ScheduleScreenContract.ViewModel> implements ScheduleScreenContract.View {


    public static ScheduleFragment newInstance() {
        Bundle args = new Bundle();
        ScheduleFragment fragment = new ScheduleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutResId() {
        return R.layout.fragment_schedule;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public ScheduleScreenContract.View getViewInterface() {
        return this;
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {

    }

    @Inject
    MainActivityContract.FlexibleMainToolbar flexibleMainToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flexibleMainToolbar.onToolbarTitleChange(getString(R.string.schedule_title));
    }
}