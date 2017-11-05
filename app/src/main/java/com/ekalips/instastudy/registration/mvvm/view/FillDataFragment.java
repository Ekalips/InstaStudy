package com.ekalips.instastudy.registration.mvvm.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.FragmentFillDataBinding;
import com.ekalips.instastudy.registration.contract.FillDataScreenContract;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FillDataFragment extends BaseBindingFragment<FragmentFillDataBinding, FillDataScreenContract.View, FillDataScreenContract.ViewModel> implements FillDataScreenContract.View {


    public static FillDataFragment newInstance() {
        Bundle args = new Bundle();
        FillDataFragment fragment = new FillDataFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutResId() {
        return R.layout.fragment_fill_data;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public FillDataScreenContract.View getViewInterface() {
        return this;
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {

    }
}
