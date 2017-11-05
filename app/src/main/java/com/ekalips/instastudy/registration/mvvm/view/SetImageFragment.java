package com.ekalips.instastudy.registration.mvvm.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.FragmentSetImageBinding;
import com.ekalips.instastudy.registration.contract.SetImageScreenContract;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetImageFragment extends BaseBindingFragment<FragmentSetImageBinding, SetImageScreenContract.View, SetImageScreenContract.ViewModel> implements SetImageScreenContract.View {


    public static SetImageFragment newInstance() {
        Bundle args = new Bundle();
        SetImageFragment fragment = new SetImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutResId() {
        return R.layout.fragment_set_image;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public SetImageScreenContract.View getViewInterface() {
        return this;
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {

    }
}
