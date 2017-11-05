package com.wonderslab.base.rx;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by Ekalips on 11/4/17.
 */

public class RxRequests {

    private final DefaultErrorHandler defaultErrorHandler;

    @Inject
    public RxRequests(DefaultErrorHandler defaultErrorHandler) {
        this.defaultErrorHandler = defaultErrorHandler;
    }

    public <T> Disposable subscribe(Observable<T> observable, Response.SuccessConsumer<T> successConsumer) {
        return subscribe(observable, successConsumer, null, null);
    }

    public <T> Disposable subscribe(Observable<T> observable, Response.Complete completeConsumer) {
        return subscribe(observable, null, null, completeConsumer);
    }

    public <T> Disposable subscribe(Observable<T> observable, Response.UnhandledError errorConsumer, Response.Complete completeConsumer) {
        return subscribe(observable, null, errorConsumer, completeConsumer);
    }

    public <T> Disposable subscribe(Observable<T> observable, @Nullable Response.SuccessConsumer<T> successConsumer,
                                    @Nullable Response.UnhandledError errorConsumer,
                                    @Nullable Response.Complete completeConsumer) {
        return observable.subscribe(t -> {
                    if (successConsumer != null) {
                        successConsumer.accept(t);
                    }
                    if (completeConsumer != null) {
                        completeConsumer.run();
                    }
                },
                throwable -> {
                    if (completeConsumer != null) {
                        completeConsumer.run();
                    }
                    if (!defaultErrorHandler.handleError(throwable)) {
                        if (errorConsumer != null) {
                            errorConsumer.accept(throwable);
                        } else {
                            RxJavaPlugins.onError(new OnErrorNotImplementedException(throwable));
                        }
                    }
                });
    }

}
