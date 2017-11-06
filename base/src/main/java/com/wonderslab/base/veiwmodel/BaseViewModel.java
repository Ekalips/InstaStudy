package com.wonderslab.base.veiwmodel;


import android.support.annotation.CallSuper;

import com.wonderslab.base.data.BindingObservable;
import com.wonderslab.base.event_system.EventGoBack;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.rx.Response;
import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.view.BaseView;

import org.greenrobot.eventbus.EventBus;

import javax.annotation.Nullable;

import io.reactivex.Observable;
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
    private final EventBus eventPublishSubject;
    private final RxRequests rxRequests;

    public BaseViewModel(RxRequests rxRequests) {
        eventPublishSubject = EventBus.getDefault();
        this.rxRequests = rxRequests;
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

    public void navigateTo(Object navigation, Object payload) {
        eventPublishSubject.postSticky(new EventNavigate(navigation, payload));
    }

    public void goBack() {
        eventPublishSubject.postSticky(new EventGoBack());
    }

    public <T> void request(Observable<T> observable) {
        request(observable, null, null);
    }

    public <T> void request(Observable<T> observable, Response.SuccessConsumer<T> successConsumer) {
        request(observable, successConsumer, null);
    }

    public <T> void request(Observable<T> observable, Response.SuccessConsumer<T> successConsumer, Response.UnhandledError unhandledErrorConsumer) {
        request(observable, successConsumer, unhandledErrorConsumer, null);
    }

    public <T> void request(Observable<T> observable, Response.SuccessConsumer<T> successConsumer, Response.UnhandledError unhandledErrorConsumer, Response.Complete completeConsumer) {
        rxRequests.subscribe(observable, successConsumer, unhandledErrorConsumer, completeConsumer);
    }

    public <T> void request(Observable<T> observable, Response.SuccessConsumer<T> successConsumer, Response.ErrorConsumer errorConsumer, Response.UnhandledError unhandledErrorConsumer, Response.Complete completeConsumer) {
        rxRequests.subscribe(observable, successConsumer, errorConsumer, unhandledErrorConsumer, completeConsumer);
    }
}
