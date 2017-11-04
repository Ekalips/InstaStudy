package com.wonderslab.base.data;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ekalips on 9/21/17.
 */

public class BindingObservable<Data> extends ObservableField<Data> implements Disposable {

    private Observable<Data> observable;
    private Disposable disposable;

    public BindingObservable() {
    }

    public BindingObservable(@NonNull Observable<Data> observable) {
        setNewSource(observable, null);
    }

    @SuppressWarnings("all")
    public BindingObservable setNewSource(@NonNull Observable<Data> observable, @Nullable Consumer<? super Throwable> throwableConsumer) {
        dispose();
        this.observable = observable;
        disposable = observable.subscribeOn(Schedulers.computation()).subscribe(this::set, throwableConsumer == null ? Functions.ON_ERROR_MISSING : throwableConsumer);
        return this;
    }

    @Override
    public void dispose() {
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
        if (observable != null) {
            observable = null;
        }
    }

    @Override
    public boolean isDisposed() {
        return disposable == null || disposable.isDisposed();
    }

    public boolean isRequested() {
        return observable != null;
    }
}
