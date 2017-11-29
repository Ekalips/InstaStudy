package com.wonderslab.base.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.transition.ChangeBounds;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventGoBack;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by ekalips on 11/29/17.
 */

public abstract class BaseBindingBottomSheetDialogFragment<Binding extends ViewDataBinding, ViewInterface extends BaseView,
        ViewModel extends BaseViewModel<ViewInterface>> extends BottomSheetDialogFragment implements BaseView {

    protected Binding binding;

    @LayoutRes
    protected abstract int layoutResId();

    public ViewModel getViewModel() {
        return viewModel;
    }

    public abstract int getViewModelBRId();

    public abstract ViewInterface getViewInterface();

    @Inject
    ViewModel viewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, layoutResId(), container, false);
        binding.setVariable(getViewModelBRId(), getViewModel());

        setSharedElementEnterTransition(new ChangeBounds());
        setSharedElementReturnTransition(new ChangeBounds());
//        setEnterTransition(new Fade());
//        setExitTransition(new Fade());

        onBindingReady(binding);

        return binding.getRoot();
    }

    public void onBindingReady(Binding binding) {

    }

    @Override
    public void onStart() {
        super.onStart();
        getViewModel().onAttach(getViewInterface());
    }

    @Override
    public void onStop() {
        super.onStop();
        getViewModel().onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getViewModel().onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        getViewModel().getEventPublishSubject().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getViewModel().getEventPublishSubject().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(Event event) {
        getViewModel().getEventPublishSubject().removeStickyEvent(event);
        handleEvent(event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onNavigationEventReceived(EventNavigate eventNavigate) {
        getViewModel().getEventPublishSubject().removeStickyEvent(eventNavigate);
        handleNavigationEvent(eventNavigate);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void OnGoBackEvent(EventGoBack eventGoBack) {
        getViewModel().getEventPublishSubject().removeStickyEvent(eventGoBack);
        handleGoBackEvent(eventGoBack);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public abstract void handleEvent(Event event);

    protected abstract void handleNavigationEvent(EventNavigate eventNavigate);

    public void handleGoBackEvent(EventGoBack eventGoBack) {

    }

}
