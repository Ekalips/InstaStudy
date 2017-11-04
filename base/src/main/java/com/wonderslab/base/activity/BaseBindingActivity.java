package com.wonderslab.base.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventGoBack;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by wl-11 on 9/21/17.
 */

public abstract class BaseBindingActivity<Binding extends ViewDataBinding, ViewInterface extends BaseView,
        ViewModel extends BaseViewModel<ViewInterface>> extends DaggerAppCompatActivity implements BaseView {

    protected Binding binding;

    @LayoutRes
    public abstract int layoutResId();

    public abstract int getViewModelBRId();

    public abstract ViewInterface getViewInterface();

    @Inject
    ViewModel viewModel;

    public ViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (inflateView()) {
            createAndSetBinding();
        }
    }

    void createAndSetBinding() {
        binding = DataBindingUtil.setContentView(this, layoutResId());
        binding.setVariable(getViewModelBRId(), getViewModel());
    }

    public boolean inflateView() {
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getViewModel().onAttach(getViewInterface());

    }

    @Override
    protected void onStop() {
        super.onStop();
        getViewModel().onDetach();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getViewModel().getEventPublishSubject().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getViewModel().getEventPublishSubject().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventReceived(Event event) {
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
    protected void onDestroy() {
        super.onDestroy();
        getViewModel().onDestroy();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected abstract void handleEvent(Event event);

    protected abstract void handleNavigationEvent(EventNavigate eventNavigate);

    public void handleGoBackEvent(EventGoBack eventGoBack) {
        onBackPressed();
    }


}
