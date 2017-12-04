package com.ekalips.instastudy.main.mvvm.view.schedule;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearSnapHelper;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.FragmentScheduleBinding;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.ekalips.instastudy.main.contract.ScheduleScreenContract;
import com.ekalips.instastudy.main.mvvm.model.schedule.LessonCardRecyclerViewAdapter;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingFragment;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends BaseBindingFragment<FragmentScheduleBinding, ScheduleScreenContract.View, ScheduleScreenContract.ViewModel> implements ScheduleScreenContract.View {

    private static final String TAG = ScheduleFragment.class.getSimpleName();

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
        switch (event.getEventType()) {
            case ScrollTo: {
                binding.recyclerView.postDelayed(() -> binding.recyclerView.smoothScrollToPosition((Integer) event.getPayload()), 400);
                break;
            }
        }
    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {

    }

    @Inject
    MainActivityContract.FlexibleMainToolbar flexibleMainToolbar;

    private final LinearSnapHelper snapHelper = new LinearSnapHelper();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flexibleMainToolbar.onChange(getString(R.string.schedule_title), R.menu.menu_schedule);
    }

    @Override
    public void onBindingReady(FragmentScheduleBinding binding) {
        super.onBindingReady(binding);
        binding.recyclerView.setAdapter(new LessonCardRecyclerViewAdapter());
        snapHelper.attachToRecyclerView(binding.recyclerView);
    }
}
