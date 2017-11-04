package com.wonderslab.base.veiwmodel;


import android.support.annotation.CallSuper;

import com.wonderslab.base.data.BindingObservable;
import com.wonderslab.base.view.BaseView;

import org.greenrobot.eventbus.EventBus;

import javax.annotation.Nullable;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Created by wl-11 on 9/21/17.
 */

public abstract class BaseViewModel<ViewInterface extends BaseView> {

    private static final String TAG = BaseViewModel.class.getSimpleName();

    @Nullable
    protected
    ViewInterface view;

    @NonNull
    protected final EventBus eventPublishSubject;

    public BaseViewModel() {
        eventPublishSubject = EventBus.getDefault();
    }

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @CallSuper
    public void onAttach(ViewInterface view) {
        this.view = view;
    }

    @CallSuper
    public void onDetach() {
        this.view = null;
    }

    @CallSuper
    public void onDestroy() {
        this.view = null;
        compositeDisposable.clear();
    }

    @CallSuper
    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @CallSuper
    public BindingObservable addObservable(BindingObservable observable) {
        compositeDisposable.add(observable);
        return observable;
    }

    @CallSuper
    public boolean dispose(Disposable observable) {
        return compositeDisposable.remove(observable);
    }

    public EventBus getEventPublishSubject() {
        return eventPublishSubject;
    }
}
